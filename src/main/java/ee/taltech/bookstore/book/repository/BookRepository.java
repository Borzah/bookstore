package ee.taltech.bookstore.book.repository;

import ee.taltech.bookstore.book.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByBookId(Long id);

    Book findBookByIsbn(long isbn);

    Long deleteByBookId(Long id);

    List<Book> findAll();

    List<Book> findAllByGenre(Long genreId);

    @Modifying
    @Query("UPDATE Book b SET b.isbn = :isbn, " +
            "b.heading = :heading, " +
            "b.author = :author," +
            "b.releaseYear = :releaseYear, " +
            "b.publisher = :publisher, " +
            "b.genre = :genre, " +
            "b.description = :description, " +
            "b.cost = :cost " +
            "WHERE b.bookId = :bookId")
    Integer updateBook(@Param("bookId") Long bookId,
                       @Param("isbn") long isbn,
                       @Param("heading") String heading,
                       @Param("author") String author,
                       @Param("releaseYear") short releaseYear,
                       @Param("publisher") String publisher,
                       @Param("genre") Long genre,
                       @Param("description") String description,
                       @Param("cost") float cost);
}
