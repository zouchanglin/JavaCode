package com.example.demo.ctrl;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Students;

@Controller
@RequestMapping("/")
public class HelloController {

	//中文的注释
	@GetMapping
	public String getIndex(Map<String, Object> map) {
		Students students = new Students();
		students.setId(789371891);
		students.setName("Tim and 黎狗子");
		map.put("stu", students);
		return "index";
	}
}
