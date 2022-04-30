package az.book.store.bookstoreboot.repository;

import az.book.store.bookstoreboot.model.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginDao extends CrudRepository<Login,Long> {
    Login findLoginByUsernameAndPassword(String username, String password, Integer active);

    Login findLoginByTokenAndActive(String token, Integer active);
}
