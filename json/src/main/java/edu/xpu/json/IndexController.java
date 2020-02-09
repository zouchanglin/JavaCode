package edu.xpu.json;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping
@RestController
public class IndexController {
    @RequestMapping
    public String index(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        System.out.println(phone);
        return "phone="+phone;
    }
}
