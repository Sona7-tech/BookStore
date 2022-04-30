package az.book.store.bookstoreboot.controller;

import az.book.store.bookstoreboot.request.ReqLogin;
import az.book.store.bookstoreboot.response.RespLogin;
import az.book.store.bookstoreboot.response.RespStatus;
import az.book.store.bookstoreboot.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginWebService {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public RespLogin login(@RequestBody ReqLogin reqLogin){
        return loginService.login(reqLogin);
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public RespStatus logout(@RequestParam("token") String token) {

        return loginService.logout(token);
    }
}
