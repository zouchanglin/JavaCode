package edu.lhl.docker.service.container;

import edu.lhl.docker.core.model.DockerContainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContainerShowService {
    List<DockerContainer> getAllRunning();
    List<DockerContainer> getAllStop();
    Boolean getOneContainerIsRunning(String containerId);
}