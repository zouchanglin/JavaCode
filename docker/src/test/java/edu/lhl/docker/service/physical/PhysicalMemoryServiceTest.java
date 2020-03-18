package edu.lhl.docker.service.physical;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PhysicalMemoryServiceTest {
    @Autowired
    private PhysicalMemoryService physicalMemoryService;

    @Test
    public void getTotalPhysicalMemory() {
        log.info("总内存 {}G", physicalMemoryService.getTotalPhysicalMemory());
    }

    @Test
    public void getFreePhysicalMemory() {
        log.info("可用内存 {}G", physicalMemoryService.getFreePhysicalMemory());
    }

    @Test
    public void getUsedPhysicalMemory() {
        log.info("已使用内存 {}G", physicalMemoryService.getUsedPhysicalMemory());
    }
}