package com.nexthoughts.spring.mvc.demo.web.controller;

import com.nexthoughts.spring.mvc.demo.model.Student;
import com.nexthoughts.spring.mvc.demo.services.WelcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {
    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private WelcomeService welcomeService;


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        logger.info("Index executed");
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("title", welcomeService.getTitle());
        model.addObject("message", welcomeService.getMessage());
        return model;
    }
}
