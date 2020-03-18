package edu.lhl.docker.service.container.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.StatsCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerPort;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.command.EventsResultCallback;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import edu.lhl.docker.core.model.DockerContainer;
import edu.lhl.docker.core.pool.DockerClientPool;
import edu.lhl.docker.service.container.ContainerShowService;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ContainerShowServiceImpl implements ContainerShowService {
    private final DockerClientPool dockerClientPool;

    public ContainerShowServiceImpl(DockerClientPool dockerClientPool) {
        this.dockerClientPool = dockerClientPool;
    }

    @Override
    public List<DockerContainer> getAllRunning() {
        DockerClient dockerClient = dockerClientPool.connectDocker();
        List<Container> containerList = dockerClient.listContainersCmd().exec();
        return convert(containerList);
    }

    @Override
    public List<DockerContainer> getAllStop() {
        DockerClient dockerClient = dockerClientPool.connectDocker();
        List<Container> containerList = dockerClient.listContainersCmd().exec();
        List<Container> containerAllList = dockerClient.listContainersCmd().withShowAll(true).exec();
        Set<Container> containerSet = new HashSet<>(containerList);
        List<Container> exitContainerList = new ArrayList<>();
        for (Container container: containerAllList) {
            if(containerSet.add(container))
                exitContainerList.add(container);
        }
        return convert(exitContainerList);
    }

    @Override
    public Boolean getOneContainerIsRunning(String containerId) {
        DockerClient dockerClient = dockerClientPool.connectDocker();
        return dockerClient.inspectContainerCmd(containerId).exec().getState().getRunning();
    }

    private List<DockerContainer> convert(List<Container> containerList){
        List<DockerContainer> dockerContainerList = new ArrayList<>(containerList.size());
        for(Container container: containerList){
            DockerContainer dockerContainer = new DockerContainer();
            dockerContainer.setContainerId(container.getId());
            String[] names = container.getNames();
            StringBuilder builder = new StringBuilder();
            for(String name: names) builder.append(name).append(" ");
            dockerContainer.setContainerName(builder.toString());
            dockerContainer.setCommand(container.getCommand());
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(container.getCreated() * 1000));
            dockerContainer.setCreated(date);
            String imageId = container.getImageId();
            dockerContainer.setImageId(imageId != null ? imageId.substring(7, imageId.length()) : null);
            dockerContainer.setImageName(container.getImage());
            dockerContainer.setStatus(container.getStatus());
            ContainerPort[] ports = container.getPorts();
            String[] portArray = new String[ports.length];
            for (int i = 0; i < ports.length; i++) {
                Integer publicPort = ports[i].getPublicPort();
                Integer privatePort = ports[i].getPrivatePort();
                portArray[i] = publicPort + " -> 0.0.0.0:" + privatePort;
            }
            dockerContainer.setPorts(portArray);
            dockerContainerList.add(dockerContainer);
        }
        return dockerContainerList;
    }
}