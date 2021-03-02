package ee.taltech.bookstore;

import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.model.Genre;
import ee.taltech.bookstore.book.model.Image;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.book.repository.GenreRepository;
import ee.taltech.bookstore.book.repository.ImageRepository;
import ee.taltech.bookstore.business.controller.BookSearchController;
import ee.taltech.bookstore.business.controller.BookSortingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookSearchTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private ImageRepository imageRepository;

    @Autowired
    private BookSearchController bookSearchController;

    @Autowired
    private BookSortingController bookSortingController;

    private final Book testBookOne = new Book(1111, "Heading", "Author 1", (short) 2000,
            "Publisher 1", 10L, "Description 1", 29.99f);
    private final Book testBookTwo = new Book(2222, "Name", "Author 2", (short) 2001,
            "Publisher 2", 20L, "Description 2", 39.99f);
    private final Image testImageOne = new Image(1L, "Image 1", "image/png", new byte[]{});
    private final Image testImageTwo = new Image(2L, "Image 2", "image/png", new byte[]{});
    private final Genre testGenreOne = new Genre(10L, "Genre 1");
    private final Genre testGenreTwo = new Genre(20L, "Genre 2");

    @Test
    void searchForBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(Stream.of(testBookOne, testBookTwo).collect(Collectors.toList()));
        when(imageRepository.findByBookId(0L)).thenReturn(testImageOne);
        when(genreRepository.findGenreByGenreId(10L)).thenReturn(testGenreOne);
        when(imageRepository.findByBookId(1L)).thenReturn(testImageTwo);
        when(genreRepository.findGenreByGenreId(20L)).thenReturn(testGenreTwo);
        assertEquals(1, bookSearchController.searchForBooks("Name").size());
        assertEquals(1, bookSearchController.searchForBooks("2222").size());
        assertEquals(1111, bookSortingController.getBookSortedByCost("ascending").get(0).getIsbn());
        assertEquals(2222, bookSortingController.getBookSortedByCost("descending").get(0).getIsbn());
    }
}
