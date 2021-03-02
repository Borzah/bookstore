package ee.taltech.bookstore.business.service;

import ee.taltech.bookstore.book.model.Book;
import ee.taltech.bookstore.book.model.Genre;
import ee.taltech.bookstore.book.model.Image;
import ee.taltech.bookstore.book.repository.BookRepository;
import ee.taltech.bookstore.book.service.GenreService;
import ee.taltech.bookstore.book.service.ImageService;
import ee.taltech.bookstore.business.model.BookResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookResponseService {

    private final BookRepository bookRepository;
    private final ImageService imageService;
    private final GenreService genreService;

    public BookResponseService(BookRepository bookRepository, ImageService imageService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.imageService = imageService;
        this.genreService = genreService;
    }

    public BookResponse getBookResponseByBookId(Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        return createBookResponseFromBookImageAndGenre(book);
    }

    public BookResponse getBookResponseByIsbn(long isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        return createBookResponseFromBookImageAndGenre(book);
    }

    public List<BookResponse> getAllBooks() {
        List<BookResponse> result = new ArrayList<>();
        List<Book> source = bookRepository.findAll();
        source.forEach(book -> result.add(createBookResponseFromBookImageAndGenre(book)));
        return result;
    }

    public List<BookResponse> getBooksByGenre(String genreName) {
        String replacedGenreName = genreName.replace("_", " ");
        List<BookResponse> result = new ArrayList<>();
        List<Book> source = bookRepository.findAllByGenre(genreService.getGenreByName(replacedGenreName).getGenreId());
        source.forEach(book -> result.add(createBookResponseFromBookImageAndGenre(book)));
        return result;
    }

    public BookResponse createBookResponse(Book book, Genre genre, Image image) {
        Image bookImage = image;
        byte[] imageBytes = new byte[]{};
        if (bookImage == null) {
            try {
                imageBytes = Files.readAllBytes(Paths.get("src/main/resources/static/defaultimage.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            imageBytes = bookImage.getImage();
        }
        return BookResponse.builder().bookId(book.getBookId()).isbn(book.getIsbn()).heading(book.getHeading()).
                author(book.getAuthor()).releaseYear(book.getReleaseYear()).publisher(book.getPublisher()).
                genre(genre.getGenreName()).description(book.getDescription()).cost(book.getCost()).
                image(imageBytes).build();
    }

    public BookResponse createBookResponseFromBookImageAndGenre(Book book) {
        Genre genre = genreService.getGenreById(book.getGenre());
        Image image = imageService.getImage(book.getBookId());
        return createBookResponse(book, genre, image);
    }
}
