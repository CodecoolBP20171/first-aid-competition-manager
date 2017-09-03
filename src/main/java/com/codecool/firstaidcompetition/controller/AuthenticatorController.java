package com.codecool.firstaidcompetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Authenticator {

    @RequestMapping("/login")
    public String loginPage(){
        return "authenticate/login";
    }

}
