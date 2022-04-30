package az.book.store.bookstoreboot.dao;

import az.book.store.bookstoreboot.model.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {
@Autowired
private DataSource dataSource;

    @Override
    public List<Book> getBookList() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql ="SELECT * FROM BOOK WHERE ACTIVE=1";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Book.class));
        return bookList;
    }

    @Override
    public void addBook(Book book) throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "insert into bookstore.book(title,author,year,category)\n" +
                "values (?,?,?,?)";

        jdbcTemplate.update(sql, new Object[] {book.getTitle(),book.getAuthor(),book.getYear(),book.getCategory()});

    }

    @Override
    public List<Book> searchBookData(String keyword) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return null;
    }


}
