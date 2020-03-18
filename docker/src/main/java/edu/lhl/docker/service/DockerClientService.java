package edu.lhl.docker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InfoCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import org.springframework.stereotype.Service;

@Service
public class DockerClientService {
    public DockerClient connectDocker(){
        return DockerClientBuilder.getInstance("tcp://192.168.2.2:2375").build();
    }

    public static void main(String[] args) {
        DockerClient dockerClient = new DockerClientService().connectDocker();
        InfoCmd infoCmd = dockerClient.infoCmd();
        Info exec = infoCmd.exec();
        Long memTotal = exec.getMemTotal();
        System.out.println(memTotal);

    }

    /**
     * 创建容器
     */
    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName){
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8088));

        return client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(new HostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
    }

    /**
     * 启动容器
     */
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 启动容器
     */
    public void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     */
    public void removeContainer(DockerClient client,String containerId){
        client.removeContainerCmd(containerId).exec();
    }
}