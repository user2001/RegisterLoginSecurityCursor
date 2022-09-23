package com.example.registerloginsecuritycursor.BookProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class BookProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author")
    private String author;

    @Column(name="price")
    private BigDecimal price;

    public BookProduct(String bookName, String author,BigDecimal price) {
        this.bookName = bookName;
        this.author = author;
        this.price=price;
    }
}
