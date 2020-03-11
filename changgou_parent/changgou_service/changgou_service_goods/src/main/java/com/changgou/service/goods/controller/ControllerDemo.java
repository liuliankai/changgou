package com.changgou.service.goods.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class ControllerDemo {
    @RequestMapping("/test")
    public String test(){
        return "demo test" ;
    }
}
