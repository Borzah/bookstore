package ee.taltech.bookstore.users.model;

import ee.taltech.bookstore.security.DbRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Store_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private DbRole role;

    public User(String username, String email, String password, DbRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
