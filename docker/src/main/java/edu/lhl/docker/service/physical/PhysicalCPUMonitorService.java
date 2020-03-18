package edu.lhl.docker.service.physical;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * 物理机CPU监控
 */
@Service
public class PhysicalCPUMonitorService {
    //[CPU系统使用率，CPU用户使用率，CPU当前等待率，CPU当前空闲率，CPU平均负载]
    public String[] getAll(){
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        String[] strings = new String[5];
        strings[0] = new DecimalFormat("#.##%").format((cSys>0? cSys:1) * 1.0 / totalCpu);
        strings[1] = new DecimalFormat("#.##%").format(user * 1.0 / totalCpu);
        strings[2] = new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu);
        strings[3] = new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu);
        strings[4] = String.format("%.1f%%", processor.getSystemCpuLoadBetweenTicks() * 100);
        return strings;
    }
}