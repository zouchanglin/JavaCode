package edu.lhl.docker.controller;

import edu.lhl.docker.service.physical.PhysicalBasicService;
import edu.lhl.docker.service.physical.PhysicalCPUMonitorService;
import edu.lhl.docker.service.physical.PhysicalDiskService;
import edu.lhl.docker.service.physical.PhysicalMemoryService;
import edu.lhl.docker.vo.MemoryOrDiskShowVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/physical")
public class PhysicalMonitorController {
    private final PhysicalMemoryService physicalMemoryService;
    private final PhysicalBasicService physicalBasicService;
    private final PhysicalDiskService physicalDiskService;
    private final PhysicalCPUMonitorService physicalCPUMonitorService;

    public PhysicalMonitorController(PhysicalMemoryService physicalMemoryService, PhysicalBasicService physicalBasicService, PhysicalDiskService physicalDiskService, PhysicalCPUMonitorService physicalCPUMonitorService) {
        this.physicalMemoryService = physicalMemoryService;
        this.physicalBasicService = physicalBasicService;
        this.physicalDiskService = physicalDiskService;
        this.physicalCPUMonitorService = physicalCPUMonitorService;
    }

    @GetMapping("index")
    public ModelAndView index(Map<String, Object> map){
        map.put("osName", physicalBasicService.getOsName());
        map.put("osCPUNumber", physicalBasicService.getCPUNumber());
        return new ModelAndView("physical", map);
    }

    @ResponseBody
    @GetMapping("cpu")
    public String[] getCPUMonitor(){
        return physicalCPUMonitorService.getAll();
    }

    @ResponseBody
    @GetMapping("memory")
    public MemoryOrDiskShowVO getMemoryMonitor(){
        String freePhysicalMemory = physicalMemoryService.getFreePhysicalMemory();
        String usedPhysicalMemory = physicalMemoryService.getUsedPhysicalMemory();
        return new MemoryOrDiskShowVO(Double.parseDouble(usedPhysicalMemory), Double.parseDouble(freePhysicalMemory));
    }

    @ResponseBody
    @GetMapping("disk")
    public MemoryOrDiskShowVO getDeskMonitor(){
        List<PhysicalDiskService.PhysicalDisk> allDiskInfo = physicalDiskService.getAllDiskInfo();
        double total = 0.0;
        double free = 0.0;
        for (PhysicalDiskService.PhysicalDisk physicalDisk: allDiskInfo){
            total += physicalDisk.getTotalSpace();
            free += physicalDisk.getFreeSpace();
        }
        return new MemoryOrDiskShowVO(Double.parseDouble(new DecimalFormat("#.##").format(total-free)),
                Double.parseDouble(new DecimalFormat("#.##").format(free)));
    }
}