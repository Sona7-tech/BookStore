package az.book.store.bookstoreboot.response;

import lombok.Data;

@Data
public class RespBook {
    private Long id;
    private String title;
    private String author;
    private String category;
    private Data dataDate;
    private Data year;
    private Integer active;
}
