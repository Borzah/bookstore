package ee.taltech.bookstore;

import ee.taltech.bookstore.security.DbRole;
import ee.taltech.bookstore.users.controller.UserController;
import ee.taltech.bookstore.users.model.User;
import ee.taltech.bookstore.users.repository.UserRepository;
import ee.taltech.bookstore.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final User testUser = new User("test", "test", "test", DbRole.USER);

    @Test
    void userTest() {
        when(userRepository.findUserByUserId(0L)).thenReturn(testUser);
        assertEquals(userService.getUsernameById(0L), ("test"));
    }
}
