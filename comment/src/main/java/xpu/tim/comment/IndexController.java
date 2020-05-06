package xpu.tim.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("login")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("call_back")
    public ModelAndView callBack(Map<String, String> map){
        for(String k: map.keySet()){
            System.out.println(k+"="+map.get(k));
        }
        return new ModelAndView("index");
    }
}
