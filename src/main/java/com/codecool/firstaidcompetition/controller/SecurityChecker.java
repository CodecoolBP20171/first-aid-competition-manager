package com.codecool.firstaidcompetition.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by keli on 2017.09.01..
 */
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
