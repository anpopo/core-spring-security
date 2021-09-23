package io.security.corespringsecurity.service.impl;

import io.security.corespringsecurity.domain.dto.UserDto;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.domain.entity.User;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(User user){

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setUserRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void modifyUser(UserDto userDto){

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        if(userDto.getRoles() != null){
            Set<Role> roles = new HashSet<>();
            userDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            user.setUserRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

    }

    @Transactional
    public UserDto getUser(Long id) {

        User account = userRepository.findById(id).orElse(new User());
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(account, UserDto.class);

        List<String> roles = account.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        userDto.setRoles(roles);
        return userDto;
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
