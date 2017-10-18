package com.codecool.firstaidcompetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPController {

    @RequestMapping(value = {"/", "/index"})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/about")
    public String aboutCompetition() {
        return "about_competition";
    }

}
