package io.security.corespringsecurity.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * CustomUserDetailsService 에서 UserDetails 타입으로 넘겨주기 위한 객체
 */
@Getter
public class UserContext extends User {
    private final io.security.corespringsecurity.domain.entity.User user;

    public UserContext(io.security.corespringsecurity.domain.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
}
