/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nexthoughts.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login() {
    }*/

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
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        return modelAndView;
    }

    /*@RequestMapping("/login")
    public String login() {
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        logger.info("///////////////GOING TO CALL LOGIN FORM/////////////////////////");
        return "login";
    }*/

    @RequestMapping(value = "/loginHandler", method = RequestMethod.POST)
    public ModelAndView successLoginHandler() {
        logger.info("PARAMSSSSSSSSSSSSSSSSSSSSSSSSS >>>>>>>>>> ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        logger.info("EROOOOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRR");
        model.addAttribute("loginError", true);
        return "login";
    }


    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
}
