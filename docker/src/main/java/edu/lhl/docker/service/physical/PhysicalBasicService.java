package edu.lhl.docker.service.physical;

import org.springframework.stereotype.Service;
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 物理机基本信息
 */
@Service
public class PhysicalBasicService {
    /**
     * 操作系统版本
     */
    public String getOsName(){
        return System.getProperty("os.name");
    }

    /**
     * CPU核数
     */
    public Integer getCPUNumber(){
        return Runtime.getRuntime().availableProcessors();
    }
}