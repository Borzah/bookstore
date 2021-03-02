package ee.taltech.bookstore.users.repository;

import ee.taltech.bookstore.users.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    //Long deleteByUserId(Long userId);

    User findUserByUserId(Long userId);

    User findUserByUsername(String username);

    List<User> findAllByUsername(String username);

    List<User> findAllByEmail(String email);
}
