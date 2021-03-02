package ee.taltech.bookstore.book.controller;

import ee.taltech.bookstore.book.dto.ImageDto;
import ee.taltech.bookstore.book.model.Image;
import ee.taltech.bookstore.book.service.ImageService;
import ee.taltech.bookstore.security.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Secured(Roles.ADMIN)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Get bookId from path
    @PostMapping("{bookId}")
    public ImageDto uplaodImage(@PathVariable Long bookId, @RequestParam("imageFile") MultipartFile file) throws IOException {
        return imageService.uploadImage(bookId, file);
    }

//    @GetMapping("{bookId}")
//    public Image getImage(@PathVariable Long bookId) {
//        return imageService.getImage(bookId);
//    }

//    @GetMapping()
//    public List<Image> getAllImages() {
//        return imageService.getAllImages();
//    }

//    @DeleteMapping("{bookId}")
//    public void deleteImage(@PathVariable Long bookId) {
//        imageService.deleteImage(bookId);
//    }

}
