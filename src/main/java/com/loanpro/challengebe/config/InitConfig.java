package com.loanpro.challengebe.config;

import com.loanpro.challengebe.user.User;
import com.loanpro.challengebe.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitConfig {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public InitConfig(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void initialize() {
        this.initBasicUsers();
    }

    private void initBasicUsers() {
        User user = new User("test@loanpro.com", encoder.encode("challenge"));
        this.userRepository.save(user);
    }
}
