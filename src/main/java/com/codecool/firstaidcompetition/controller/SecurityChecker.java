package com.codecool.firstaidcompetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class SecurityChecker {

    @RequestMapping("/admin/a")
    public String fakeSecur(){
        return "admin";
    }

    @RequestMapping("/user")
    public String fakeSecur2(){
        return "user";
    }

}
