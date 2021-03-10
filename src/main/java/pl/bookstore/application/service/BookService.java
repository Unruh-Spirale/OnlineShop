package pl.bookstore.application.service;

import pl.bookstore.application.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBook(long id);
    void addBook(Book book);
    void updateBook(long idBook, Book updateBook);
    void deleteBook(long id);

}
