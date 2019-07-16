package com.falanadamian.krim.schedule.security;

import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.exception.UserNotActivatedException;
import com.falanadamian.krim.schedule.repository.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ModelUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ModelUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        if (new EmailValidator().isValid(username, null)) {
            User user = userRepository.findOneWithRolesByEmail(username).
                    orElseThrow(() -> new UsernameNotFoundException("User with email: " + username + " not found!"));

            if (!user.isActivated())
                throw new UserNotActivatedException("User" + username + " is not activated!");
            return ModelUserDetails.build(user);
        }

        User user = userRepository.findOneWithRolesByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"));

        if (!user.isActivated())
            throw new UserNotActivatedException("User" + username + " is not activated!");
        return ModelUserDetails.build(user);
    }

}
