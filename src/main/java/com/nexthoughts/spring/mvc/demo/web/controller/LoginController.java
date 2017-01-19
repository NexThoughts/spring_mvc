package com.nexthoughts.spring.mvc.demo.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Value(value = "login.error")
    private String error;

    @Value(value = "logout.success")
    private String logout;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) modelAndView.addObject("error", error);
        if (logout != null) modelAndView.addObject("logout", logout);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = "/loginHandler", method = RequestMethod.POST)
    public ModelAndView successLoginHandler() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/student/list");
        return modelAndView;
    }


}
