package cn.ll.controller;

import cn.ll.domain.Test;
import cn.ll.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

// @Controller
@RestController
public class TestController {

    @Resource
    private TestService testService;


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





    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }

}
