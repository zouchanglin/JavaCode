package edu.lhl.docker.service.container.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import edu.lhl.docker.core.pool.DockerClientPool;
import edu.lhl.docker.service.container.ContainerCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContainerCtrlServiceImpl implements ContainerCtrlService {
    @Autowired
    private DockerClientPool dockerClientPool;

    @Override
    public void startContainer(String containerId) {
        DockerClient dockerClient = dockerClientPool.connectDocker();
        InspectContainerResponse response = dockerClient.inspectContainerCmd(containerId).exec();
        if(Objects.equals(response.getState().getRunning(), true)) return;
        dockerClient.startContainerCmd(containerId).exec();
    }
}