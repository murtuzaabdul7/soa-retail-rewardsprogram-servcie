package com.assessment.rewardprogram.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class HelloWorldController {


    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World!!";
    }



}
