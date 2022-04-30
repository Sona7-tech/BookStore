package az.book.store.bookstoreboot.response;

import lombok.Data;

import java.util.List;

@Data
public class RespBookList {
    private List<RespBook> bookList;
    private RespStatus status;
}
