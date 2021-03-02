package ee.taltech.bookstore.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class GenreDto {

    @Positive(message = "Genre Id must be positive number")
    @NotNull(message = "Genre Id cannot be null")
    private Long genreId;
    @NotBlank(message = "Genre name cannot be blank")
    private String genreName;
}
