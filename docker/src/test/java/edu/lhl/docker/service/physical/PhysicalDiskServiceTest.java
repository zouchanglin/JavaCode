package edu.lhl.docker.service.physical;

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
public class PhysicalDiskServiceTest {
    @Autowired
    private PhysicalDiskService physicalDiskService;
    @Test
    public void getAllDiskInfo() {
        List<PhysicalDiskService.PhysicalDisk> diskInfo = physicalDiskService.getAllDiskInfo();
        for (PhysicalDiskService.PhysicalDisk physicalDisk: diskInfo){
            log.info(physicalDisk.toString()+"\n");
        }
    }
}