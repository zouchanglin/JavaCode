package xpu.edu.elong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xpu.edu.elong.entity.MemberInfo;
import xpu.edu.elong.service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@RestController --> JSON
@Controller
@RequestMapping("/")
public class GetDataController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/getData")
    public String getData(@RequestParam("mobile") String mobile, Map<String, Object> map){
        List<MemberInfo> memberData = memberService.getMamberData(mobile);
        map.put("memberData", memberData);
        return "index";
    }
}