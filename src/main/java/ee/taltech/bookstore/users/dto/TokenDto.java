package ee.taltech.bookstore.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    @NotNull(message = "Token cannot be null")
    @NotBlank(message = "Token cannot be blank")
    private String token;
}
