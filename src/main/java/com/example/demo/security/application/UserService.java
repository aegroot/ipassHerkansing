package com.example.demo.security.application;


import com.example.demo.security.data.SpringUserRepository;
import com.example.demo.security.data.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 *  Implements UserDetailsService in order to make it usable
 *  as login/registration service for Spring.
 *  (see AuthenticationFilter)
 */

@Service
@Transactional
public class UserService implements UserDetailsService {


    //rem
    @Value("${chips.start-amount}")
    //rem
    private Long chipsStartAmount;

    private final SpringUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //rem


    public UserService(SpringUserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;

        //rem
    }


    public void register(String username, String password, String firstName, String lastName) {
        String encodedPassword = this.passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, firstName, lastName);

        //rem

        this.userRepository.save(user);

        //rem
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
