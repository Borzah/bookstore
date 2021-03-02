package ee.taltech.bookstore.users.service;

import ee.taltech.bookstore.security.*;
import ee.taltech.bookstore.users.dto.LoginDto;
import ee.taltech.bookstore.users.dto.LoginResponse;
import ee.taltech.bookstore.users.exception.UserException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenHolder userTokenHolder;
    private final AuthChecker authChecker;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserTokenHolder userTokenHolder, AuthChecker authChecker, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userTokenHolder = userTokenHolder;
        this.authChecker = authChecker;
        this.userService = userService;
    }

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("missing password");
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(myUser);
        userTokenHolder.addToken(myUser.getUsername(), token);
        return LoginResponse.builder()
                .userId(myUser.getId())
                .username(myUser.getUsername())
                .email(myUser.getEmail())
                .token(token)
                .role(myUser.getDbRole())
                .build();
    }

    public void logout(String username, String authToken) {
        authChecker.checkUserAttachingTheirInfo(userService.getUserIdByUsername(username), authToken);
        userTokenHolder.removeToken(username);
    }
}
