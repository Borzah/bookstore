package ee.taltech.bookstore.security;

import ee.taltech.bookstore.users.model.User;
import ee.taltech.bookstore.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findAllByUsername(username);
        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException("Username not found!");
        }
        User user = users.get(0);
        return new MyUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getUserId(), user.getEmail(), user.getRole());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(DbRole::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Stream<DbRole> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(DbRole.values());
        }
        return Stream.of(user.getRole());
    }
}
