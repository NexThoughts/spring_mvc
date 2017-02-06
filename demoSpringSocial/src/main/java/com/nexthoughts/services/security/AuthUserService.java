package com.nexthoughts.services.security;

import com.nexthoughts.domain.security.Role;
import com.nexthoughts.domain.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@ComponentScan(basePackageClasses = UserService.class)
public class AuthUserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(AuthUserService.class);
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserbyUsername(username);
        logger.info("////////IIIIINNNNNNNSIIIDEEEEE loadUserByUsername////////" + user);
        if (user != null) {
            Collection<GrantedAuthority> authorities = new HashSet<>(user.getRoles().size());
            Set<Role> userRoles = user.getRoles();
            logger.info("//////// loadUserByUsername userRoles////////" + userRoles);
            for (Role role : userRoles) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            logger.info("//////// loadUserByUsername authorities////////" + authorities);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
                    user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), authorities);
        } else {
            throw new UsernameNotFoundException("User with username  -- " + username + "  could not be found.");
        }
    }
}