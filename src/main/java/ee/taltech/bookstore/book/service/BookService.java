package ee.taltech.bookstore.book.service;

import ee.taltech.bookstore.book.dto.BookDto;
import ee.taltech.bookstore.book.exception.InvalidBookException;
import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookDto bookDto) {
        if (bookRepository.findBookByIsbn(bookDto.getIsbn()) != null) {
            throw new InvalidBookException("Book with that ISBN already exists!");
        }
        validateBook(bookDto);
        return createBookDtoFromBook(bookRepository.save(createBookFromBookDto(bookDto)));
    }

    public Integer updateBook(Long bookId, BookDto bookDto) {
        validateBook(bookDto);
        return bookRepository.updateBook(
                bookId, bookDto.getIsbn(),
                bookDto.getHeading(),
                bookDto.getAuthor(),
                bookDto.getReleaseYear(),
                bookDto.getPublisher(),
                bookDto.getGenre(),
                bookDto.getDescription(),
                bookDto.getCost());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteByBookId(id);
    }

    private Book createBookFromBookDto(BookDto bookDto) {
        return new Book(bookDto.getIsbn(),
                bookDto.getHeading(),
                bookDto.getAuthor(),
                bookDto.getReleaseYear(),
                bookDto.getPublisher(),
                bookDto.getGenre(),
                bookDto.getDescription(),
                bookDto.getCost());
    }

    private BookDto createBookDtoFromBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setHeading(book.getHeading());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setReleaseYear(book.getReleaseYear());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setGenre(book.getGenre());
        bookDto.setDescription(book.getDescription());
        bookDto.setCost(book.getCost());
        return bookDto;
    }

    private void validateBook(BookDto bookDto) throws InvalidBookException {
        if (bookDto.getReleaseYear() < 0 || bookDto.getReleaseYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new InvalidBookException("Space-time continuum error!");
        }
        if (bookDto.getCost() < 0) {
            throw new InvalidBookException("Cost cannot be null!");
        }
    }

//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    public Book getBookById(Long id) {
//        return this.bookRepository.findByBookId(id);
//    }
}
