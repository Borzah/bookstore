package ee.taltech.bookstore.business.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long bookId;
    private long isbn;
    private String heading;
    private String author;
    private short releaseYear;
    private String publisher;
    private String genre;
    private String description;
    private float cost;
    private byte[] image;
}
