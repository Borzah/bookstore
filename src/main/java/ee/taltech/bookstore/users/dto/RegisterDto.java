package ee.taltech.bookstore.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "Username cannot be blank!")
    private String username;
    @Email(message = "Must be valid email address!")
    private String email;
    @NotBlank(message = "Password must be not blank!")
    private String password;
}
