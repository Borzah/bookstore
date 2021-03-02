package ee.taltech.bookstore.book.service;

import ee.taltech.bookstore.book.dto.GenreDto;
import ee.taltech.bookstore.book.model.Genre;
import ee.taltech.bookstore.book.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findGenreByGenreId(id);
    }

    public Genre getGenreByName(String genreName) {
        return genreRepository.findGenreByGenreName(genreName);
    }

    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::createGenreDtoFromGenre)
                .collect(Collectors.toList());

    }

    private GenreDto createGenreDtoFromGenre(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreId(genre.getGenreId());
        genreDto.setGenreName(genre.getGenreName());
        return genreDto;
    }

//    public GenreDto addGenre(GenreDto genreDto) {
//        Genre genre = genreRepository.save(new Genre(genreDto.getGenreId(), genreDto.getGenreName()));
//        GenreDto result = new GenreDto();
//        result.setGenreId(genre.getGenreId());
//        result.setGenreName(genre.getGenreName());
//        return result;
//    }
//
//    public void deleteGenreById(Long id) {
//        genreRepository.deleteGenreByGenreId(id);
//    }
}
