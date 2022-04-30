package az.book.store.bookstoreboot.controller;

import az.book.store.bookstoreboot.request.ReqToken;
import az.book.store.bookstoreboot.response.RespBookList;
import az.book.store.bookstoreboot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookWebService {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getBookList", method = {RequestMethod.GET, RequestMethod.POST})
    public RespBookList getBookList(@RequestBody ReqToken reqToken) {
        return bookService.getBookList(reqToken);
    }
}
