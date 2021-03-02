package ee.taltech.bookstore.business.service;

import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.business.model.BookResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookSearchService {

    private final BookRepository bookRepository;
    private final BookResponseService bookResponseService;

    public BookSearchService(BookRepository bookRepository, BookResponseService bookResponseService) {
        this.bookRepository = bookRepository;
        this.bookResponseService = bookResponseService;
    }

    public List<BookResponse> searchForBooks(String input) {
        String replacedInput = input.replace("_", " ");
        List<BookResponse> result = new ArrayList<>();
        List<Book> source = bookRepository.findAll();
        source.forEach(book -> {
            if (isBookMatchedWithSearchCriteria(replacedInput, book)) {
                result.add(bookResponseService.createBookResponseFromBookImageAndGenre(book));
            }
        });
        return result;
    }

    private boolean isBookMatchedWithSearchCriteria(String replacedInput, Book book) {
        return book.getHeading().toLowerCase().contains(replacedInput.toLowerCase())
                || book.getAuthor().toLowerCase().contains(replacedInput.toLowerCase())
                || String.valueOf(book.getIsbn()).equals(replacedInput.toLowerCase());
    }
}
