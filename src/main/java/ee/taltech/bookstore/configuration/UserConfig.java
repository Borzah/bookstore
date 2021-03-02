package ee.taltech.bookstore.configuration;

import ee.taltech.bookstore.security.DbRole;
import ee.taltech.bookstore.users.model.User;
import ee.taltech.bookstore.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void addAdminUser() {
        if (userRepository.findAllByEmail("admin@mail.com").size() == 0) {
            User admin = new User("admin", "admin@mail.com", passwordEncoder.encode("superpassword"), DbRole.ADMIN);
            userRepository.save(admin);
        }
    }
}
