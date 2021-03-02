package ee.taltech.bookstore.users.controller;

import ee.taltech.bookstore.security.Roles;
import ee.taltech.bookstore.users.dto.LoginDto;
import ee.taltech.bookstore.users.dto.LoginResponse;
import ee.taltech.bookstore.users.dto.RegisterDto;
import ee.taltech.bookstore.users.service.AuthService;
import ee.taltech.bookstore.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDto registerDto){
        userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }

    @Secured(Roles.USER)
    @PostMapping("logout/{username}")
    public ResponseEntity<Void> logout(@PathVariable String username, @RequestHeader("Authorization") String authToken) {
        authService.logout(username, authToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @GetMapping(path = "{userId}")
//    public User getUser(@PathVariable Long userId) {
//        return userService.getUser(userId);
//    }
//
//    @GetMapping
//    public Iterable<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//
//    @DeleteMapping(path = "{userId}")
//    public void deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//    }
}
