package edu.tim.springbootstart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.tim.Person;


@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private Person person;

    @GetMapping
    public String index(){
        return person.sayName();
    }
}
