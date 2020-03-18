package edu.lhl.docker.controller;

import edu.lhl.docker.service.container.ContainerCtrlService;
import edu.lhl.docker.service.container.ContainerShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/container")
public class ContainerController {
    @Autowired
    private ContainerCtrlService containerCtrlService;
    @Autowired
    private ContainerShowService containerShowService;

    @ResponseBody
    @GetMapping("start")
    public String startOneContainer(String containerId){
        containerCtrlService.startContainer(containerId);
        return containerId;
    }

    @ResponseBody
    @GetMapping("status")
    public boolean getContainerStatus(String containerId){
        return containerShowService.getOneContainerIsRunning(containerId);
    }
}