package pl.bookstore.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookstore.application.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
