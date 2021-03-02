package ee.taltech.bookstore.book.service;

import ee.taltech.bookstore.book.dto.ImageDto;
import ee.taltech.bookstore.book.exception.InvalidBookException;
import ee.taltech.bookstore.book.model.Image;
import ee.taltech.bookstore.book.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageDto uploadImage(Long bookId, MultipartFile file) throws IOException {
        validateImage(bookId, file.getContentType());
        Image img = new Image(bookId, file.getOriginalFilename(), file.getContentType(), file.getBytes());
        Image image = imageRepository.save(img);
        ImageDto imageDto = new ImageDto();
        imageDto.setBookId(image.getBookId());
        imageDto.setName(image.getName());
        imageDto.setType(image.getType());
        imageDto.setImage(image.getImage());
        return imageDto;
    }

    public Image getImage(Long id) {
        return imageRepository.findByBookId(id);
    }

    private void validateImage(Long id, String filetype) throws InvalidBookException {
        List<String> allowedTypes = List.of("image/jpeg", "image/jpg", "image/png");
        if (id == null) {
            throw new InvalidBookException("Id cannot be null!");
        }
        if (!allowedTypes.contains(filetype)) {
            throw new InvalidBookException("A file must be a image!");
        }
    }

//    public List<Image> getAllImages() {
//        return imageRepository.findAll();
//    }
//
//    public void deleteImage(Long id) {
//        imageRepository.deleteByBookId(id);
//    }
}
