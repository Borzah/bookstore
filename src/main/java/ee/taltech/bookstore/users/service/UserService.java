package ee.taltech.bookstore.users.service;

import ee.taltech.bookstore.security.DbRole;
import ee.taltech.bookstore.users.dto.RegisterDto;
import ee.taltech.bookstore.users.exception.UserException;
import ee.taltech.bookstore.users.model.User;
import ee.taltech.bookstore.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterDto registerDto) {
        if (userRepository.findAllByUsername(registerDto.getUsername()).size() > 0) {
            throw new UserException("User with this username already exists!");
        }
        if (userRepository.findAllByEmail(registerDto.getEmail()).size() > 0) {
            throw new UserException("User with this email already exists!");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(DbRole.USER);
        userRepository.save(user);
    }

    public String getUsernameById(Long userId) {
        return userRepository.findUserByUserId(userId).getUsername();
    }

    public Long getUserIdByUsername(String username) {
        return userRepository.findUserByUsername(username).getUserId();
    }

//    public User getUser(Long userId) {
//        return userRepository.findUserByUserId(userId);
//    }

//    public Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public void deleteUser(Long userId) {
//        userRepository.deleteByUserId(userId);
//    }
}
