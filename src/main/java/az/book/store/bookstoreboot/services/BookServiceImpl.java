package az.book.store.bookstoreboot.services;

import az.book.store.bookstoreboot.dao.BookDao;
import az.book.store.bookstoreboot.enums.EnumAvailableStatus;
import az.book.store.bookstoreboot.model.Book;
import az.book.store.bookstoreboot.model.Login;
import az.book.store.bookstoreboot.repository.LoginDao;
import az.book.store.bookstoreboot.request.ReqToken;
import az.book.store.bookstoreboot.response.RespBook;
import az.book.store.bookstoreboot.response.RespBookList;
import az.book.store.bookstoreboot.response.RespStatus;
import az.book.store.bookstoreboot.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private LoginDao loginDao;
    @Override
    public RespBookList getBookList(ReqToken reqToken) {
        RespBookList response = new RespBookList();
        String token = reqToken.getToken();
        List<RespBook> respBookList = new ArrayList<>();
        try {

            if(token == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }
            Login login = loginDao.findLoginByTokenAndActive(token, EnumAvailableStatus.ACTIVE.getValue());
            if(login == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid token"));
                return response;
            }
            List<Book> bookList = bookDao.getBookList();
            if (bookList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.BOOK_NOT_FOUND, "Book not found"));
                return response;
            }
            for (Book book : bookList) {
                RespBook respBook = new RespBook();
                respBook.setId(book.getId());
                respBook.setTitle(book.getTitle());
                respBook.setAuthor(book.getAuthor());
                respBook.setCategory(book.getCategory());
                respBook.setYear(book.getYear());

            }
            response.setBookList(respBookList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));

        }



        return response;
    }
}
