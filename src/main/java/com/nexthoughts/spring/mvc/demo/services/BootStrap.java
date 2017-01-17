package com.nexthoughts.spring.mvc.demo.services;

import com.nexthoughts.spring.mvc.demo.classes.StudentCommand;
import com.nexthoughts.spring.mvc.demo.classes.UserCommand;
import com.nexthoughts.spring.mvc.demo.model.Role;
import com.nexthoughts.spring.mvc.demo.model.User;
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

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

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

        createAdminUser();
        createUser();

        User user = userService.read(1L);
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("=====================" + user.getRoles().size() + "============================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("===============================================================================");
        System.out.println("...Bootstrapping completed");


    }


    public void createSpringSecurityBootstrapData() {
        //Creating Roles
        /*String admin = "ROLE_ADMIN";
        String user = "ROLE_USER";
        Role roleAdmin = roleService.read(roleService.create(admin));
        Role roleUser = roleService.read(roleService.create(user));
        System.out.println("Role created with role - " + roleAdmin.getRole());
        System.out.println("Role created with role - " + roleUser.getRole());

        createAdminUser(roleAdmin);
        createUser(roleUser);

        System.out.println("Bootstrapping of Users Completed");*/

    }

    public void createAdminUser() {
        UserCommand adminUserCommand = new UserCommand();
        adminUserCommand.setEmail("nakul@nexthoughts.com");
        adminUserCommand.setUsername("nakul@admin");
        adminUserCommand.setPassword("nakul@admin");
        User savedAdmin = userService.read(userService.create(adminUserCommand));
        String admin = "ROLE_ADMIN";
        Role roleAdmin = roleService.read(roleService.create(admin, savedAdmin));
        System.out.println("Admin created with ROLE_ADMIN and username - " + savedAdmin.getUsername() + "  and password -  " + savedAdmin.getPassword());
    }


    public void createUser() {
        UserCommand userCommand = new UserCommand();
        userCommand.setEmail("nakul+21@nexthoughts.com");
        userCommand.setUsername("nakul@user");
        userCommand.setPassword("nakul@user");
        User savedUser = userService.read(userService.create(userCommand));
        String user = "ROLE_USER";
        Role roleUser = roleService.read(roleService.create(user, savedUser));
        System.out.println("User created with ROLE_USER and username - " + savedUser.getUsername() + "  and password -  " + savedUser.getPassword());

    }
}
