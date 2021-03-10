package pl.bookstore.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.bookstore.application.entity.Book;
import pl.bookstore.application.entity.Role;
import pl.bookstore.application.entity.User;
import pl.bookstore.application.repository.RoleRepository;
import pl.bookstore.application.service.BookService;
import pl.bookstore.application.service.SignUpService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        BookService bookService = ctx.getBean(BookService.class);
        RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
        SignUpService signUpService = ctx.getBean(SignUpService.class);

        Book book1 = new Book("Ogniem i Mieczem","Sienkiewicz Henryk","Literature","soft",69.99);
        Book book2 = new Book("Krzyżacy","Sienkiewicz Henryk","Literature","hard",99.99);
        Book book3 = new Book("Harry Potter and the Order of the Phoenix","Rowling J.K","Children's Fiction","soft",30);
        Book book4 = new Book("The Da Vinci Code","Brown Dan","Thriller","hard",34.99);
        Book book5 = new Book("Fifty Shades of Grey","James E.L.","Romance","soft",29.99);
        Book book6 = new Book("Angels and Demons","Brown Dan","Thriller","hard",39.99);

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        bookService.addBook(book6);

        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User admin = new User("admin","admin");
        User user = new User("user","user");
        signUpService.signUpAdmin(admin);
        signUpService.signUpUser(user);

    }

}
