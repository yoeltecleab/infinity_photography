package org.yoeltecleab.infinity.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.yoeltecleab.infinity.model.User;
import org.yoeltecleab.infinity.transfer.UserDto;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    void save(UserDto registration);
}
