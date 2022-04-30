package az.book.store.bookstoreboot.response;

import lombok.Data;

@Data
public class RespLogin {

    private String username;
    private String name;
    private String token;
    private RespStatus status;


}
