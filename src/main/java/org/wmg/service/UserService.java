package org.wmg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wmg.mapper.UserMapper;
import org.wmg.security.SecurityUtils;
import org.wmg.security.model.User;

import java.util.Optional;

/**
 * @author wmg
 * @date 2024/6/19
 */

@Service
@Transactional
public class UserService {
    private final UserMapper userMapper;
//    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    public UserService(UserMapper userMapper) {
        this.userMapper  = userMapper;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userMapper::findOneWithAuthoritiesByUsername);
    }

}
