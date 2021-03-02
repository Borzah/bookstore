package ee.taltech.bookstore.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Long bookId;
    @NotNull(message = "ISBN cannot be null")
    @Min(value = 1000000000000L, message = "ISBN must be exactly 13 digits long")
    private long isbn;
    @NotBlank(message = "Heading cannot be null or blank!")
    private String heading;
    @NotBlank(message = "Author cannot be null or blank!")
    private String author;
    @Min(value = 1, message = "Release year must be between 1 and present")
    @NotNull(message = "Release year cannot be null!")
    private short releaseYear;
    @NotBlank(message = "Publisher cannot be null or blank!")
    private String publisher;
    @NotNull(message = "Genre Id cannot be null")
    private Long genre;
    @NotBlank(message = "Description cannot be null or blank")
    private String description;
    @NotNull(message = "Cost cannot be null")
    private float cost;
}
