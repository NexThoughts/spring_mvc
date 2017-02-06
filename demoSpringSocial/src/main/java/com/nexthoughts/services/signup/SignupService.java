package com.nexthoughts.services.signup;

import com.nexthoughts.command.security.UserCommand;
import com.nexthoughts.domain.security.Role;
import com.nexthoughts.domain.security.User;
import com.nexthoughts.services.security.RoleService;
import com.nexthoughts.services.security.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignupService {
    private final Logger logger = LoggerFactory.getLogger(SignupService.class);
    private SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    public SignupService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public User create(UserCommand userCommand) {
        logger.info("#################################");
        logger.info("#################################");
        logger.info("###########INSIDE USER CREATE##########");
        logger.info("#################################");
        logger.info("#################################");
        User savedUser = userService.read(userService.create(userCommand));
        String user = "ROLE_USER";
        Role roleUser = roleService.read(roleService.create(user, savedUser));
        logger.info("User created with ROLE_USER and username - " + savedUser.getUsername() + "  and password -  " + savedUser.getPassword());
        return savedUser;
    }
}

