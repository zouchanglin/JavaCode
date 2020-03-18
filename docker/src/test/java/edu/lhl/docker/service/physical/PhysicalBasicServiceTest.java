package edu.lhl.docker.service.physical;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PhysicalBasicServiceTest {
    @Autowired
    private PhysicalBasicService physicalBasicService;

    @Test
    public void getOsName() {
        String osName = physicalBasicService.getOsName();
        log.info("osName={}", osName);
    }

    @Test
    public void getCPUNumber() {
        Integer cpuNumber = physicalBasicService.getCPUNumber();
        log.info("cpuNumber={}", cpuNumber);
    }

}