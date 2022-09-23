package com.example.registerloginsecuritycursor.BookProduct;

import com.example.registerloginsecuritycursor.exeption.BookNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookProductService {
    private final BookProductRepo bookProductRepo;

    @PostConstruct
    void init() {
        BookProduct book1 = new BookProduct("Amadoka", "Andruchovych", BigDecimal.valueOf(350.0));
        BookProduct book2 = new BookProduct("Harry Potter", "Rowling", BigDecimal.valueOf(240.0));
        BookProduct book3 = new BookProduct("Tak ale", "Prohasko", BigDecimal.valueOf(280.0));
        bookProductRepo.saveAll(Arrays.asList(book2, book1, book3));
    }

    public List<BookProduct> getBooks() {
        return bookProductRepo.findAll();
    }

    public Optional<BookProduct> getBookById(Long bookId) {

        return bookProductRepo.findById(bookId);
    }

    public BookProduct addBook(BookProduct book) {
        bookProductRepo.save(book);
        return book;
    }

    public BookProduct updateBook(BookProduct book, Long bookId) {
        var temp = bookProductRepo.findById(bookId).orElse(null);
        temp.setBookName(book.getBookName());
        temp.setAuthor(book.getAuthor());
        temp.setPrice(book.getPrice());
        bookProductRepo.save(temp);
        return temp;
    }


    public String deleteBook(Long bookId) {
        isFound(bookId);
        bookProductRepo.deleteById(bookId);
        return "Book with id: " + bookId + " was deleted";
    }

    private void isFound(Long bookId) {
        boolean isExist = bookProductRepo.existsById(bookId);
        if (!isExist) {
            throw new BookNotFoundException(
                    "Shop with id: " + bookId + " not found, try to put correct id");
        }
    }

}
