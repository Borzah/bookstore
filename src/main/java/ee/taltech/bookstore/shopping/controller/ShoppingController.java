package ee.taltech.bookstore.shopping.controller;

import ee.taltech.bookstore.business.model.BookResponse;
import ee.taltech.bookstore.security.Roles;
import ee.taltech.bookstore.shopping.service.ShoppingService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured(Roles.USER)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/user")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("{userId}/shopping")
    public List<BookResponse> getUserShoppingList(@PathVariable Long userId, @RequestHeader("Authorization") String authToken) {
        return shoppingService.getUserShoppingListAsBookResponse(userId, authToken);
    }
}
