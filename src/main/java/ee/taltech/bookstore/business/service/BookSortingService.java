package ee.taltech.bookstore.business.service;

import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.business.model.BookResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class BookSortingService {

    private final BookRepository bookRepository;
    private final BookResponseService bookResponseService;

    public BookSortingService(BookRepository bookRepository, BookResponseService bookResponseService) {
        this.bookRepository = bookRepository;
        this.bookResponseService = bookResponseService;
    }

    // Ascending or Descending is passed as path variable from Controller
    public List<BookResponse> sortByCost(String parameter) throws Exception {
        List<Book> source = bookRepository.findAll();
        List<BookResponse> result = new ArrayList<>();
        if (parameter.equals("ascending")) {
            source.sort(Comparator.comparing(Book::getCost));
        } else if (parameter.equals("descending")) {
            source.sort(Comparator.comparing(Book::getCost).reversed());
        } else throw new Exception();
        source.forEach(book -> result.add(bookResponseService.createBookResponseFromBookImageAndGenre(book)));
        return result;
    }
}
