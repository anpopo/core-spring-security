package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.dto.UserDto;
import io.security.corespringsecurity.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    void createUser(User account);

    @Transactional
    void modifyUser(UserDto accountDto);

    void deleteUser(Long id);

    List<User> getUsers();

    UserDto getUser(Long id);
}
