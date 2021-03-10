package pl.bookstore.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bookstore.application.entity.User;
import pl.bookstore.application.repository.UserRepository;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> usernameOptional = userRepository.findByUsername(username);

        if (!usernameOptional.isPresent()){
            throw new UsernameNotFoundException("No user found with username: "+username);
        }

        User user = usernameOptional.get();

        return user;
    }
}
