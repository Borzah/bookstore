package ee.taltech.bookstore.shopping.service;

import ee.taltech.bookstore.business.model.BookResponse;
import ee.taltech.bookstore.business.service.BookResponseService;
import ee.taltech.bookstore.security.AuthChecker;
import ee.taltech.bookstore.shopping.model.ShoppingList;
import ee.taltech.bookstore.shopping.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ShoppingService {

    private final ShoppingListRepository shoppingListRepository;
    private final BookResponseService bookResponseService;
    private final AuthChecker authChecker;

    public ShoppingService(ShoppingListRepository shoppingListRepository, BookResponseService bookResponseService, AuthChecker authChecker) {
        this.shoppingListRepository = shoppingListRepository;
        this.bookResponseService = bookResponseService;
        this.authChecker = authChecker;
    }

    public List<BookResponse> getUserShoppingListAsBookResponse(Long userId, String authToken) {
        authChecker.checkUserAttachingTheirInfo(userId, authToken);
        List<ShoppingList> source = shoppingListRepository.findAllByUserId(userId);
        List<BookResponse> result = new ArrayList<>();
        source.forEach(shoppingList -> {
            result.add(bookResponseService.getBookResponseByBookId(shoppingList.getBookId()));
        });
        return result;
    }
}
