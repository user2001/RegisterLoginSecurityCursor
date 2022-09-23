package com.example.registerloginsecuritycursor.BookProduct;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookProductController {
    private final BookProductService bookService;

    @GetMapping
    public ResponseEntity<List<BookProduct>> getAllBooks() {
        var list = bookService.getBooks();
        String version = String.valueOf(list.hashCode());
        return ResponseEntity.ok()
                .eTag(version)
                .body(list);
    }

    @GetMapping("/{bookId}")
    public Optional<BookProduct> getBook(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);

    }

    @PostMapping
    public BookProduct addBook(@RequestBody BookProduct book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return "Book with id: " + bookId + " was deleted";
    }

    @PutMapping(value = "/{bookId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public BookProduct updateBook(@RequestBody BookProduct book, @PathVariable Long bookId) {
        return bookService.updateBook(book, bookId);
    }
}
