package ee.taltech.bookstore.business.controller;

import ee.taltech.bookstore.business.model.BookResponse;
import ee.taltech.bookstore.business.service.BookSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/data/search")
public class BookSearchController {

    private final BookSearchService bookSearchService;

    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping
    public List<BookResponse> searchForBooks(@RequestParam(value = "input") String input) {
        return bookSearchService.searchForBooks(input);
    }
}
