package az.book.store.bookstoreboot.services;

import az.book.store.bookstoreboot.request.ReqToken;
import az.book.store.bookstoreboot.response.RespBookList;

public interface BookService {
    RespBookList getBookList(ReqToken reqToken);
}
