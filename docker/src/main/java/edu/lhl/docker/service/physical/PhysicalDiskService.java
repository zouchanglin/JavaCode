package edu.lhl.docker.service.physical;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 物理机磁盘监控
 */
@Service
public class PhysicalDiskService {

    /**
     * 物理磁盘监控
     */
    public List<PhysicalDisk> getAllDiskInfo(){
        List<PhysicalDisk> physicalDiskList= new ArrayList<>();
        // 磁盘使用情况
        File[] files = File.listRoots();
        for (File file : files) {
            String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024);
            String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024);
            String path = file.getPath();
            physicalDiskList.add(new PhysicalDisk(path, Double.parseDouble(total), Double.parseDouble(free)));
        }
        return physicalDiskList;
    }

    @Data
    @AllArgsConstructor
    public static class PhysicalDisk{
        String diskName;
        Double totalSpace;
        Double freeSpace;
    }
}