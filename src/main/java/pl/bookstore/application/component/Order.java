package pl.bookstore.application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bookstore.application.entity.Book;
import pl.bookstore.application.repository.BookRepository;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class Order {

    private static List<Book>orders = new ArrayList<>();

    private int orderNumber = 0;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private BookRepository bookRepository;

    @Autowired
    public Order(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBookToOrder(long idBook){
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setOrderNumber(orderNumber);
            orders.add(book);
            orderNumber++;
        }
    }

    public void deleteBookFromOrder(int key){
        orders.remove(key);
        for (int i =0;i<orders.size();i++){
            Book book = orders.get(i);
            book.setOrderNumber(i);
        }
        orderNumber--;
    }

    public void clearOrder(){
        orders.clear();
        orderNumber = 0;
    }

    public List<Book>clearAfterLogout(){
        List<Book> clearList = this.orders;
        clearList.clear();
        orderNumber = 0;
        return clearList;
    }

    public List<Book> displayOrder(){
        return orders;
    }

    public String totalPrice(){
        double sum = orders.stream().mapToDouble(x -> x.getPrice()).sum();
        return df2.format(sum);
    }
}
