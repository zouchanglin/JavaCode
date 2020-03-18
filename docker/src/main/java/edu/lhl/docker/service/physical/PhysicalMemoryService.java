package edu.lhl.docker.service.physical;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

/**
 * 物理机内存监控
 */
@Service
public class PhysicalMemoryService {
    /**
     * 总内存 G为单位
     */
    public String getTotalPhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return new DecimalFormat("#.#").format(omb.getTotalPhysicalMemorySize() / 1024.0 / 1024 / 1024);
    }

    /**
     * 可用内存 G为单位
     */
    public String getFreePhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return new DecimalFormat("#.#").format(omb.getFreePhysicalMemorySize() / 1024.0 / 1024 / 1024);
    }

    /**
     * 已使用内存 G为单位
     */
    public String getUsedPhysicalMemory(){
        OperatingSystemMXBean omb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存
        return new DecimalFormat("#.#").format(((omb.getTotalPhysicalMemorySize() - omb.getFreePhysicalMemorySize()) / 1024.0 / 1024 / 1024));
    }
}