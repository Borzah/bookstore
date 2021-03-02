package ee.taltech.bookstore.business.controller;

import ee.taltech.bookstore.business.model.BookResponse;
import ee.taltech.bookstore.business.service.BookResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/data/books")
public class BookResponseController {

    private final BookResponseService bookResponseService;

    public BookResponseController(BookResponseService bookResponseService) {
        this.bookResponseService = bookResponseService;
    }

    @GetMapping("isbn/{isbn}")
    public BookResponse getBookResponseByIsbn(@PathVariable long isbn) {
        return bookResponseService.getBookResponseByIsbn(isbn);
    }

    @GetMapping
    public List<BookResponse> getAllBookResponses() {
        return bookResponseService.getAllBooks();
    }

    @GetMapping({"genre/{genreName}", "genre2/{genreName}"})
    public List<BookResponse> getBooksByGenre(@PathVariable String genreName) {
        return bookResponseService.getBooksByGenre(genreName);
    }

//    @GetMapping("{bookId}")
//    public BookResponse getBookResponseByBookId(@PathVariable Long bookId) {
//        return bookResponseService.getBookResponseByBookId(bookId);
//    }
}
