package ee.taltech.bookstore.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {

    private Long bookId;
    private String name;
    private String type;
    private byte[] image;
}
