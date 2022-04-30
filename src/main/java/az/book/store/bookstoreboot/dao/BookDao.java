package az.book.store.bookstoreboot.dao;

import az.book.store.bookstoreboot.model.Book;
import az.book.store.bookstoreboot.response.RespBookList;

import java.util.List;

public interface BookDao {
    List<Book> getBookList() throws Exception;
    void addBook(Book book) throws Exception;
    List<Book> searchBookData(String keyword) throws Exception;
}
