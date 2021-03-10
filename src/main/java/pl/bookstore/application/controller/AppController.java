package pl.bookstore.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.bookstore.application.component.Order;
import pl.bookstore.application.entity.Book;

import java.util.List;

@Controller
public class AppController {

    private Order order;

    @Autowired
    public AppController(Order order) {
        this.order = order;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }


}
