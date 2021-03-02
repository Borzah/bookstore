package ee.taltech.bookstore.book.repository;

import ee.taltech.bookstore.book.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    Image findByBookId(Long id);

    long deleteByBookId(Long id);

    List<Image> findAll();
}
