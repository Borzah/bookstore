package ee.taltech.bookstore.shopping.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class ShoppingListDto {

    private Long shoppingListId;
    @NotNull(message = "Id cannot be null!")
    private Long userId;
    @NotNull(message = "Id cannot be null!")
    private Long bookId;
}
