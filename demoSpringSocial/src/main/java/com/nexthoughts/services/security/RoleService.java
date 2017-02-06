package com.nexthoughts.services.security;

import com.nexthoughts.domain.security.Role;
import com.nexthoughts.domain.security.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleService {
    private SessionFactory sessionFactory;

    @Autowired
    public RoleService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public long create(String role, User user) {
        Role persistentRole = new Role(role, user);
        getSession().save(persistentRole);
        getSession().close();
        return persistentRole.getId();
    }


    public Role read(Long id) {
        Session session = getSession();
        Role role = (Role) session.get(Role.class, id);
        session.close();
        return role;
    }
}
