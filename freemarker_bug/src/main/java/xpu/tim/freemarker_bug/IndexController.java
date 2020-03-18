package xpu.tim.freemarker_bug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView index(Map<String, Object> map){
        Person person = new Person("AAA", 1089127L);
        map.put("person", person);
        return new ModelAndView("index", map);
    }

    public static void main(String[] args) {
        Integer integer = 999888777;
        System.out.println(integer.toString());
    }
}