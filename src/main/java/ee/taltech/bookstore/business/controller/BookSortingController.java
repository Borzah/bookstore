package ee.taltech.bookstore.business.controller;

import ee.taltech.bookstore.business.model.BookResponse;
import ee.taltech.bookstore.business.service.BookSortingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/data/sort")
public class BookSortingController {

    private final BookSortingService bookSortingService;

    public BookSortingController(BookSortingService bookSortingService) {
        this.bookSortingService = bookSortingService;
    }

    @GetMapping("price")
    public List<BookResponse> getBookSortedByCost(@RequestParam(value = "parameter") String parameter)
            throws Exception {
        return bookSortingService.sortByCost(parameter);
    }
}
