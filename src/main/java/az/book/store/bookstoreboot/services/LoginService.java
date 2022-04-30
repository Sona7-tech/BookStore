package az.book.store.bookstoreboot.services;

import az.book.store.bookstoreboot.request.ReqLogin;
import az.book.store.bookstoreboot.response.RespLogin;
import az.book.store.bookstoreboot.response.RespStatus;

public interface LoginService {
    RespLogin login(ReqLogin reqLogin);
    RespStatus logout(String token);
}
