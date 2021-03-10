package pl.bookstore.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.bookstore.application.entity.Role;
import pl.bookstore.application.entity.User;
import pl.bookstore.application.repository.RoleRepository;
import pl.bookstore.application.repository.UserRepository;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User signUpUser(User user) {

        Assert.isNull(user.getIdUser(), "Cannot sign up given user, it already has set id. User: "+user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> roleOptional = roleRepository.findByName("USER");
        if(roleOptional.isPresent()){
            user.getRoles().add(roleOptional.get());
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User signUpAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> roleOptional = roleRepository.findByName("ADMIN");
        if(roleOptional.isPresent()){
            user.getRoles().add(roleOptional.get());
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
