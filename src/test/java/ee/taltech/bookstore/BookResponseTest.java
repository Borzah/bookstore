package ee.taltech.bookstore;

import ee.taltech.bookstore.book.controller.BookController;
import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.model.Genre;
import ee.taltech.bookstore.book.model.Image;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.book.repository.GenreRepository;
import ee.taltech.bookstore.book.repository.ImageRepository;
import ee.taltech.bookstore.business.controller.BookResponseController;
import ee.taltech.bookstore.business.service.BookResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookResponseTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private ImageRepository imageRepository;

    @Autowired
    private BookResponseController bookResponseController;

    @Autowired
    private BookController bookController;

    private final Book testBookOne = new Book(1111, "Heading 1", "Author 1", (short) 2000,
            "Publisher 1", 10L, "Description 1", 29.99f);
    private final Book testBookTwo = new Book(2222, "Heading 2", "Author 2", (short) 2001,
            "Publisher 2", 20L, "Description 2", 39.99f);
    private final Image testImageOne = new Image(1L, "Image 1", "image/png", new byte[]{});
    private final Image testImageTwo = new Image(2L, "Image 2", "image/png", new byte[]{});
    private final Genre testGenreOne = new Genre(10L, "Genre 1");
    private final Genre testGenreTwo = new Genre(20L, "Genre 2");

    @Test
    public void getBooks() {
        when(bookRepository.findAll()).thenReturn(Stream.of(testBookOne, testBookTwo).collect(Collectors.toList()));
        when(imageRepository.findByBookId(0L)).thenReturn(testImageOne);
        when(genreRepository.findGenreByGenreId(10L)).thenReturn(testGenreOne);
        when(imageRepository.findByBookId(1L)).thenReturn(testImageTwo);
        when(genreRepository.findGenreByGenreId(20L)).thenReturn(testGenreTwo);
        when(bookRepository.findByBookId(0L)).thenReturn(testBookOne);
        when(bookRepository.findAllByGenre(1L)).thenReturn(Stream.of(testBookOne).collect(Collectors.toList()));
        when(genreRepository.findGenreByGenreName("Genre 1")).thenReturn(new Genre(1L, "Genre 1"));
        when(bookRepository.findBookByIsbn(1111)).thenReturn(testBookOne);
        when(bookRepository.findByBookId(1L)).thenReturn(testBookOne);
        testBookOne.setBookId(0L);
        assertEquals(2, bookResponseController.getAllBookResponses().size());
        assertEquals("Genre 1", bookResponseController.getBooksByGenre("Genre 1").get(0).getGenre());
        assertEquals(1111, bookResponseController.getBookResponseByIsbn(1111).getIsbn());
    }

//    @Test
//    public void updateBook() {
//        when(bookRepository.updateBook(1, 2222, "Heading 2", "Author 2", (short) 2001,
//                "Publisher 2", 20, "Description 2", 39.99f)).thenReturn(1);
//        Integer expected = 1;
//        Integer result = bookController.updateBook(1, testBookTwo);
//        assertEquals(expected, result);
//    }
}
