package com.mysite.zzzz7037;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }

    @GetMapping("/test")
    public String test(){
        return "index";
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }
}
