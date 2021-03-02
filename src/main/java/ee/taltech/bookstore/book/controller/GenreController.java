package ee.taltech.bookstore.book.controller;

import ee.taltech.bookstore.book.dto.GenreDto;
import ee.taltech.bookstore.book.model.Genre;
import ee.taltech.bookstore.book.service.GenreService;
import ee.taltech.bookstore.security.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"api/genres", "api/genres2"})
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Available for all
    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

//    @DeleteMapping(path = "{genreId}")
//    public void deleteGenre(@PathVariable Long genreId) {
//        genreService.deleteGenreById(genreId);
//    }
//
//
//    @GetMapping(path = "{genreId}")
//    public Genre getGenre(@PathVariable Long genreId) {
//        return genreService.getGenreById(genreId);
//    }

    // Available for admin
//    @Secured(Roles.ADMIN)
//    @PostMapping
//    public GenreDto addGenre(@Valid @RequestBody GenreDto genreDto) {
//        return genreService.addGenre(genreDto);
//    }

}
