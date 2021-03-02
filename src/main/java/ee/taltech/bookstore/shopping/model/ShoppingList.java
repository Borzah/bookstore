package ee.taltech.bookstore.shopping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Shopping_list")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_list_id")
    private Long shoppingListId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    public ShoppingList(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
