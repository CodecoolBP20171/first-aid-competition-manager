package com.codecool.firstaidcompetition;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringController {

    @RequestMapping("/registration")
    public String registrationPage(){
        return "registration_form";
    }

    @RequestMapping("/index")
    public String indexPage(){ return "index"; }
}
