package cn.ll.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// @Controller
@RestController
public class TestController {

    @Value("${test.hello}")
    private String testHello;

    //  @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post，" + name;
    }

}
