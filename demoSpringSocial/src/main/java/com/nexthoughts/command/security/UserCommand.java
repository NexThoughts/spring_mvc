package com.nexthoughts.command.security;

import com.nexthoughts.domain.security.Role;
import com.nexthoughts.domain.security.User;
import org.springframework.social.connect.UserProfile;

import java.util.Set;

public class UserCommand {
    private Long id;

    private String username;

    private String password;

    private String email;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserCommand() {
    }

    public UserCommand(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserCommand(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserCommand(Long id, String username, String password, String email, Set roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserCommand fromProviderUser(UserProfile providerUser) {
        UserCommand userCommand = new UserCommand();
        userCommand.setUsername(providerUser.getUsername());
        userCommand.setEmail(providerUser.getEmail());
        return userCommand;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
