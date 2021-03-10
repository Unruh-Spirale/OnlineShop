package pl.bookstore.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import pl.bookstore.application.entity.Book;
import pl.bookstore.application.service.BookService;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "books";
    }

    @GetMapping("/books/{idBook}")
    public String getBook(@PathVariable("idBook") long idBook, Model model){
        Book book = bookService.getBook(idBook);
        model.addAttribute("book",book);
        return "book";
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        return "addBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book){
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/updateBookForm/{idBook}")
    public String updateBookForm(@PathVariable("idBook") long idBook, Model model){
        Book book = bookService.getBook(idBook);
        model.addAttribute("book",book);
        return "updateBook";
    }

    @PostMapping("/updateBook/{idBook}")
    public String updateBook(@PathVariable("idBook") long idBook, Book newBook){
        bookService.updateBook(idBook, newBook);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{idBook}")
    public String deleteBook(@PathVariable("idBook") long idBook){
        bookService.deleteBook(idBook);
        return "redirect:/books";
    }
}
