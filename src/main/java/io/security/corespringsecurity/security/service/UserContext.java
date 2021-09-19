package io.security.corespringsecurity.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserContext extends User {
    private final io.security.corespringsecurity.domain.User user;

    public UserContext(io.security.corespringsecurity.domain.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
}
