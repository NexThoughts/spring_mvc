package com.nexthoughts.spring.mvc.demo.services;

import com.nexthoughts.spring.mvc.demo.classes.StudentCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class BootStrap implements InitializingBean {
    @Autowired
    StudentService studentService;


    private final Logger log = org.slf4j.LoggerFactory.getLogger(BootStrap.class);

    @Override
    @Transactional
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bootstrapping data...");
        if (studentService.list().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                StudentCommand studentCommand = new StudentCommand();
                studentCommand.setFirstName("Student" + i);
                studentCommand.setLastName("Last" + i);
                studentCommand.setEmailAddress("nakul+" + i + "@nexthoughts.com");
                studentService.create(studentCommand);

                log.info("===================STUDENT CREATED with email" + studentCommand.getEmailAddress() + "===================");
            }
        }

    }

}

