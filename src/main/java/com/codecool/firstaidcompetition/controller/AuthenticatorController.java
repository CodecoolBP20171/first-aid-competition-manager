package com.codecool.firstaidcompetition.controller;

import com.codecool.firstaidcompetition.model.User;
import com.codecool.firstaidcompetition.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticatorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticatorController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public String loginPage(){
        return "authenticate/login";
    }

    @RequestMapping("/logout")
    public String logoutPage(){
        return "authenticate/logout";
    }

    @GetMapping("/registration")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "authenticate/registration_form";
    }

    @RequestMapping("/registration/{userName}")
    @ResponseBody   // can return with anything
    public boolean checkUsernameIsExists(@PathVariable String userName) {
        return userService.checkUsernameAlreadyExists(userName);
    }

    @PostMapping("/registration")
    public ModelAndView submitUser(@ModelAttribute User user,
                                   @RequestParam("userRole") String role) {
        userService.saveUser(user, role); // save with hashing pass
        logger.info("Save USer to the db, " +
                        "[fullName: {}; userName: {}; email: {}, password: {}]",
                user.getFullName(), user.getUserName(), user.getEmail(),
                user.getPassword());
        return new ModelAndView("redirect:/index");
    }

}
