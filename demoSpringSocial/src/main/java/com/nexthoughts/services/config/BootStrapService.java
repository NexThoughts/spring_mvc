package com.nexthoughts.services.config;

import com.nexthoughts.command.security.UserCommand;
import com.nexthoughts.domain.security.Role;
import com.nexthoughts.domain.security.User;
import com.nexthoughts.services.security.RoleService;
import com.nexthoughts.services.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class BootStrapService implements InitializingBean {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    private final Logger logger = LoggerFactory.getLogger(BootStrapService.class);

    @Override
    @Transactional
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        logger.info("********************************************");
        logger.info("Bootstrapping data...");
        if (userService.list().isEmpty()) {
            createAdminUser();
            createUser();
        }
        logger.info("********************************************");
    }

    public void createAdminUser() {
        UserCommand adminUserCommand = new UserCommand();
        adminUserCommand.setEmail("admin@admin.com");
        adminUserCommand.setUsername("ali@admin");
        adminUserCommand.setPassword("ali@admin");
        User savedAdmin = userService.read(userService.create(adminUserCommand));
        String admin = "ROLE_ADMIN";
        Role roleAdmin = roleService.read(roleService.create(admin, savedAdmin));
        System.out.println("Admin created with ROLE_ADMIN and username - " + savedAdmin.getUsername() + "  and password -  " + savedAdmin.getPassword());
    }


    public void createUser() {
        UserCommand userCommand = new UserCommand();
        userCommand.setEmail("info@email.com");
        userCommand.setUsername("ali@user");
        userCommand.setPassword("ali@user");
        User savedUser = userService.read(userService.create(userCommand));
        String user = "ROLE_USER";
        Role roleUser = roleService.read(roleService.create(user, savedUser));
        System.out.println("User created with ROLE_USER and username - " + savedUser.getUsername() + "  and password -  " + savedUser.getPassword());

    }
}