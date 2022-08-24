package org.yoeltecleab.infinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yoeltecleab.infinity.model.User;
import org.yoeltecleab.infinity.repository.UserRepository;
import org.yoeltecleab.infinity.transfer.UserDto;

import java.util.Collection;
import java.util.Collections;

/**
 * <pre>
 *     This class "UserServiceImpl" Autowired the UserRepository class and BCryptPasswordEncoder
 *     implements the UserService interface
 *     contains the method "save" to save the information to the database
 * </pre>
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * Injects the dependency for the UserRepository Interface,
     * and the BcryptPasswordEncoder for encoding the password
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * @param email must match be registered in database to login
     * @return the UserDetails for authentication
     * @throws UsernameNotFoundException if an unrecognized email address is entered
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities());
    }

    /**
     * This method automatically assigns the client as a user
     *
     * @return granted authority for the client
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
        return Collections.singleton((new SimpleGrantedAuthority("USER")));
    }

    /**
     * @param email receives an email as an argument to find the User
     * @return the user matching the email entered
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * This method saves the user to the database
     *
     * @param registration is the data transfer object used to obtain data from the user
     */
    public void save(UserDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        userRepository.save(user);

    }

    /**
     * This method is used to obtain the First Name of user
     * for displaying at the top of the page
     *
     * @param userEmail is passed to get the user's first name from database
     * @return the First Name of hte user
     */
    public String name(String userEmail) {
        return userRepository.name(userEmail);
    }
}