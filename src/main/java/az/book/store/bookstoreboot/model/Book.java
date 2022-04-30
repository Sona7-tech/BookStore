package az.book.store.bookstoreboot.model;

import lombok.Data;

@Data
public class Book {

    private Long id;
    private String title;
    private String author;
    private String category;
    private Data dataDate;
    private Data year;
    private Integer active;

}
