package ee.taltech.bookstore.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingListException extends RuntimeException {

    public ShoppingListException() {
    }

    public ShoppingListException(String message) {
        super(message);
    }
}
