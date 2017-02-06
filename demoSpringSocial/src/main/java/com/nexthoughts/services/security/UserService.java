package com.nexthoughts.services.security;

import com.nexthoughts.command.security.UserCommand;
import com.nexthoughts.domain.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


    public User read(Long id) {
        return (User) getSession().get(User.class, id);
    }

    public User getUserbyUsername(String username) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    public List<UserCommand> list() {
        List<User> userList = getSession().createCriteria(User.class).list();
        List<UserCommand> userCommandList = new ArrayList<>();
        UserCommand userCommand = null;
        for (User user : userList) {
            userCommand = new UserCommand(user);
            userCommandList.add(userCommand);
        }
        getSession().close();
        return userCommandList;
    }
}