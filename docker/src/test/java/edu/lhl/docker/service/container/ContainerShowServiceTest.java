package edu.lhl.docker.service.container;

import edu.lhl.docker.core.model.DockerContainer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContainerShowServiceTest {
    @Autowired
    private ContainerShowService containerShowService;
    @Test
    public void getAllRunning() {
        List<DockerContainer> running = containerShowService.getAllRunning();
    }

    @Test
    public void getOneContainerStatus() {
        containerShowService.getOneContainerIsRunning("4a09968bbddd18445b17d5c4755c356862730fb9afa056028d33084894457b20");
    }
}