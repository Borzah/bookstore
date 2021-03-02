package ee.taltech.bookstore.shopping.controller;

import ee.taltech.bookstore.security.Roles;
import ee.taltech.bookstore.shopping.dto.ShoppingListDto;
import ee.taltech.bookstore.shopping.exception.ShoppingListException;
import ee.taltech.bookstore.shopping.service.ShoppingListService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Secured(Roles.USER)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/shopping")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ShoppingListDto addShoppingList(@Valid @RequestBody ShoppingListDto shoppingListDto, @RequestHeader("Authorization") String authToken) throws ShoppingListException {
        return shoppingListService.addShoppingList(shoppingListDto, authToken);
    }

    @DeleteMapping("user/{userId}/book/{bookId}")
    public void deleteBookFromShoppingList(@PathVariable Long userId, @PathVariable Long bookId, @RequestHeader("Authorization") String authToken) {
        shoppingListService.deleteBookFromShoppingList(userId, bookId, authToken);
    }

    @DeleteMapping("{userId}")
    public void deleteShoppingList(@PathVariable Long userId, @RequestHeader("Authorization") String authToken) {
        shoppingListService.deleteShoppingList(userId, authToken);
    }

//    @GetMapping(path = "{userId}")
//    public List<ShoppingList> getAllUsersShoppingLists(@PathVariable Long userId) {
//        return shoppingListService.getShoppingListsByUser(userId);
//    }

//    @Secured(Roles.ADMIN)
//    @GetMapping
//    public List<ShoppingList> getAllShoppingLists() {
//        return shoppingListService.getAllShoppingLists();
//    }
}

