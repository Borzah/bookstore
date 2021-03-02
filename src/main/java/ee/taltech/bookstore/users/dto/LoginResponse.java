package ee.taltech.bookstore.users.dto;

import ee.taltech.bookstore.security.DbRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private Long userId;
    private String username;
    private String email;
    private String token;
    private DbRole role;
}
