package edu.lhl.docker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView getIndex(){
        return new ModelAndView("redirect:/physical/index");
    }
}
