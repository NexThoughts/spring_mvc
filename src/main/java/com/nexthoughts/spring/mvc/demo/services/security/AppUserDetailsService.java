package com.nexthoughts.spring.mvc.demo.services.security;

import com.nexthoughts.spring.mvc.demo.model.Role;
import com.nexthoughts.spring.mvc.demo.model.User;
import com.nexthoughts.spring.mvc.demo.services.UserService;
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
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserbyUsername(username);
        if (user != null) {
            Collection<GrantedAuthority> authorities = new HashSet<>(user.getRoles().size());
            Set<Role> userRoles = user.getRoles();
            for (Role role : userRoles) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
                    user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), authorities);
        } else {
            throw new UsernameNotFoundException("User with username  -- " + username + "  could not be found.");
        }
    }
}
