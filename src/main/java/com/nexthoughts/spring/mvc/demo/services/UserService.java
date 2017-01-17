package com.nexthoughts.spring.mvc.demo.services;

import com.nexthoughts.spring.mvc.demo.classes.UserCommand;
import com.nexthoughts.spring.mvc.demo.model.User;
import com.nexthoughts.spring.mvc.demo.services.security.PasswordEncoderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    PasswordEncoderService encoderService;

    private SessionFactory sessionFactory;

    @Autowired
    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }


    public long create(UserCommand userCommand) {
        User user = new User(userCommand);
        user.setPassword(encoderService.encode(userCommand.getPassword()));
        getSession().save(user);
        getSession().close();
        return user.getId();
    }


    public User read(Long id){
        return (User) getSession().get(User.class,id);
    }

    public User getUserbyUsername(String username) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }
}
