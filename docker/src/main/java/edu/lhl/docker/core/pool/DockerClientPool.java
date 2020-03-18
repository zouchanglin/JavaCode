package edu.lhl.docker.core.pool;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DockerClientPool {
    @Bean
    public DockerClient connectDocker(){
        return DockerClientBuilder.getInstance("tcp://192.168.2.2:2375").build();
    }
}