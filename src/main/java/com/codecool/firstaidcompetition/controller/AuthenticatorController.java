package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.repository.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
public class AuthenticatorController {

    private static final Logger logger = LoggerFactory.getLogger(HTTPController.class.getName());

    @Autowired
    private DBHandler dbHandler;

    @RequestMapping("/login")
    public String loginPage(){
        return "authenticate/login";
    }

    @RequestMapping("/logout")
    public String logoutPage(){
        return "authenticate/logout";
    }

}
