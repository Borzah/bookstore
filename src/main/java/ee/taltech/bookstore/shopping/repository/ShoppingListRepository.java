package ee.taltech.bookstore.shopping.repository;

import ee.taltech.bookstore.shopping.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

    List<ShoppingList> findAll();

    Long deleteByUserId(Long id);

    Long deleteByUserIdAndBookId(Long userId, Long bookId);

    List<ShoppingList> findAllByUserId(Long userId);
}
