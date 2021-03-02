package ee.taltech.bookstore.book.controller;

import com.sun.istack.NotNull;
import ee.taltech.bookstore.book.dto.BookDto;
import ee.taltech.bookstore.book.service.BookService;
import ee.taltech.bookstore.security.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Secured(Roles.ADMIN)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto addBook(@NotNull @Valid @RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping(path = "{bookId}")
    public Integer updateBook(@PathVariable Long bookId, @NotNull @Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookId, bookDto);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@NotNull @PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    //    @GetMapping
//    public List<Book> getAllBooks() {
//        return this.bookService.getAllBooks();
//    }

//    @GetMapping(path = "{bookId}")
//    public Book getBookById(@NotNull @PathVariable Long bookId) {
//        return this.bookService.getBookById(bookId);
//    }
}
