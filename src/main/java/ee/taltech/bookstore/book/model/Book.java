package ee.taltech.bookstore.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "isbn")
    private long isbn;

    @Column(name = "heading")
    private String heading;

    @Column(name = "author")
    private String author;

    @Column(name = "release_year")
    private short releaseYear;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "genre")
    private Long genre;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private float cost;

    public Book(long isbn,
                String heading,
                String author,
                short releaseYear,
                String publisher,
                Long genre,
                String description,
                float cost) {
        this.isbn = isbn;
        this.heading = heading;
        this.author = author;
        this.releaseYear = releaseYear;
        this.publisher = publisher;
        this.genre = genre;
        this.description = description;
        this.cost = cost;
    }
}
