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
package com.nexthoughts.controller.signup;

import com.nexthoughts.command.security.UserCommand;
import com.nexthoughts.domain.security.User;
import com.nexthoughts.classes.SigninUtils;
import com.nexthoughts.services.signup.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    private final Logger logger = LoggerFactory.getLogger(SignupController.class);
    private final SignupService signupService;
    private final ProviderSignInUtils providerSignInUtils;


    @Autowired
    public SignupController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository, SignupService signupService) {
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
        this.signupService = signupService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        ModelAndView modelAndView = new ModelAndView();
        UserCommand userCommand = new UserCommand();
        if (connection != null) {
            request.setAttribute("message", "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up.", WebRequest.SCOPE_REQUEST);
            modelAndView.addObject(userCommand.fromProviderUser(connection.fetchUserProfile()));
        } else {
            modelAndView.addObject(userCommand);
        }
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(UserCommand userCommand, WebRequest request) {
        logger.info("Executing POST method for /user/signup");
        logger.info("==========================================");
        User user = signupService.create(userCommand);
        if (user != null) {
            logger.info("================User Saved with Id - " + user.getId() + " ==========================");
            SigninUtils.signin(user.getUsername());
            providerSignInUtils.doPostSignUp(user.getUsername(), request);
        }
        logger.info("==========================================");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userCommand", userCommand);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
