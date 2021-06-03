package com.example.demo.security.application;


import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.account.SpringAccountRepository;
import com.example.demo.security.data.Role;
import com.example.demo.security.data.SpringUserRepository;
import com.example.demo.security.data.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;

/**
 *  Implements UserDetailsService in order to make it usable
 *  as login/registration service for Spring.
 *  (see AuthenticationFilter)
 */

@Service
@Transactional
public class UserService implements UserDetailsService {


    private final SpringUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpringAccountRepository accountRepository;


    public UserService(SpringUserRepository repository, PasswordEncoder passwordEncoder, SpringAccountRepository accountRepository) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }


    public void register(String username, String password, String firstName, String lastName, Date geboortedatum, Role role) {
        String encodedPassword = this.passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, firstName, lastName,role);



        accountRepository.save(new MailAccount(username, encodedPassword, geboortedatum,firstName, lastName,role));

    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
