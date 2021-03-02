package ee.taltech.bookstore.shopping.service;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.security.AuthChecker;
import ee.taltech.bookstore.shopping.dto.ShoppingListDto;
import ee.taltech.bookstore.shopping.exception.InvalidShoppingListException;
import ee.taltech.bookstore.shopping.exception.ShoppingListException;
import ee.taltech.bookstore.shopping.model.ShoppingList;
import ee.taltech.bookstore.shopping.repository.ShoppingListRepository;
import ee.taltech.bookstore.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthChecker authChecker;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, BookRepository bookRepository, UserRepository userRepository, AuthChecker authChecker) {
        this.shoppingListRepository = shoppingListRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authChecker = authChecker;
    }

    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public List<ShoppingList> getShoppingListsByUser(Long userId) {
        return shoppingListRepository.findAllByUserId(userId);
    }

    public ShoppingListDto addShoppingList(ShoppingListDto shoppingListDto, String authToken) throws ShoppingListException {
        validateShoppingList(shoppingListDto.getUserId(), shoppingListDto.getBookId());
        authChecker.checkUserAttachingTheirInfo(shoppingListDto.getUserId(), authToken);
        List<ShoppingList> source = getShoppingListsByUser(shoppingListDto.getUserId());
        for (ShoppingList s: source) {
            if (s.getBookId().equals(shoppingListDto.getBookId())) {
                throw new ShoppingListException("Book already in shopping list");
            }
        }
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUserId(shoppingListDto.getUserId());
        shoppingList.setBookId(shoppingListDto.getBookId());
        return createShoppingListDtoFromShoppingList(shoppingListRepository.save(shoppingList));
    }

    public void deleteBookFromShoppingList(Long userId, Long bookId, String authToken) {
        validateShoppingList(userId, bookId);
        authChecker.checkUserAttachingTheirInfo(userId, authToken);
        shoppingListRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    public void deleteShoppingList(Long userId, String authToken) {
        if (userRepository.findUserByUserId(userId) == null) {
            throw new InvalidShoppingListException("User Id must be present!");
        }
        authChecker.checkUserAttachingTheirInfo(userId, authToken);
        shoppingListRepository.deleteByUserId(userId);
    }

    private ShoppingListDto createShoppingListDtoFromShoppingList(ShoppingList shoppingList) {
        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setShoppingListId(shoppingList.getShoppingListId());
        shoppingListDto.setUserId(shoppingList.getUserId());
        shoppingListDto.setBookId(shoppingList.getBookId());
        return shoppingListDto;
    }

    private void validateShoppingList(Long userId, Long bookId) {
        if (bookId == null || bookId < 1) {
            throw new InvalidShoppingListException("Book id must be not null and higher than 0!");
        }
        if (userId == null || userId < 1) {
            throw new InvalidShoppingListException("User id must be not null and higher than 0!");
        }
        if (bookRepository.findByBookId(bookId) == null) {
            throw new InvalidShoppingListException("Book Id must be present!");
        }
        if (userRepository.findUserByUserId(userId) == null) {
            throw new InvalidShoppingListException("User Id must be present!");
        }
    }
}

