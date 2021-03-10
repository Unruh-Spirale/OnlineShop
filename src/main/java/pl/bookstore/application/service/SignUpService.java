package pl.bookstore.application.service;

import pl.bookstore.application.entity.User;

public interface SignUpService {

    User signUpUser(User user);
    User signUpAdmin(User user);

}
