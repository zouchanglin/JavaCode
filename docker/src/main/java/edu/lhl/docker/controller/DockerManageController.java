package edu.lhl.docker.controller;

import edu.lhl.docker.service.container.ContainerShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/virtual")
public class DockerManageController {
    @Autowired
    private ContainerShowService containerShowService;

    @GetMapping("index")
    public ModelAndView getIndex(Map<String, Object> map){
        map.put("running", containerShowService.getAllRunning());
        map.put("stop", containerShowService.getAllStop());
        return new ModelAndView("virtual", map);
    }
}