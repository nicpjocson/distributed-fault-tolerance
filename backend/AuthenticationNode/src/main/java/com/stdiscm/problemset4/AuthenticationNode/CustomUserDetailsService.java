package com.stdiscm.problemset4.AuthenticationNode;

import com.stdiscm.problemset4.AuthenticationNode.model.UserEntity;
import com.stdiscm.problemset4.AuthenticationNode.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("User found: " + userEntity.getUsername());
        System.out.println("Password: " + userEntity.getPassword());
        System.out.println("Role: " + userEntity.getRole());

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) // Password should include {noop} prefix
                .roles(userEntity.getRole()) // Single role: STUDENT or TEACHER
                .build();
    }
}
