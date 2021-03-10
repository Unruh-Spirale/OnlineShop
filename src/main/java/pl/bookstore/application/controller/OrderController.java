package pl.bookstore.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.bookstore.application.component.Order;
import pl.bookstore.application.entity.Book;

import java.util.List;

@Controller
public class OrderController {

    private Order order;

    @Autowired
    public OrderController(Order order) {
        this.order = order;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        List<Book> orderList = order.displayOrder();
        model.addAttribute("orders",orderList);
        model.addAttribute("total_price",order.totalPrice());
        return "cart";
    }

    @GetMapping("/add_order/{idBook}")
    public String addOrder(@PathVariable("idBook") long idBook){
        order.addBookToOrder(idBook);
        return "redirect:/books";
    }

    @GetMapping("/delete_order/{orderNumber}")
    public String deleteOrder(@PathVariable("orderNumber") Integer orderNumber){
        order.deleteBookFromOrder(orderNumber);
        return "redirect:/cart";
    }

    @GetMapping("/send_order")
    public String sendOrder(){
        order.clearOrder();
        return "redirect:/cart";
    }

    @GetMapping("/clear_order")
    public void clearOrder(){
        order.clearOrder();
    }
}
