package ee.taltech.bookstore.book.repository;

import ee.taltech.bookstore.book.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Genre findGenreByGenreId(Long id);

    Genre findGenreByGenreName(String name);

    //Long deleteGenreByGenreId(Long id);
}
