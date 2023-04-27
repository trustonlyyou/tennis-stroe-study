package com.tnc.study.tennisstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/hello")
    public String helloSpring() {
        String test = "test";
        return test;
    }
}
