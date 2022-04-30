package az.book.store.bookstoreboot.services;

import az.book.store.bookstoreboot.enums.EnumAvailableStatus;
import az.book.store.bookstoreboot.model.Login;
import az.book.store.bookstoreboot.repository.LoginDao;
import az.book.store.bookstoreboot.request.ReqLogin;
import az.book.store.bookstoreboot.response.RespLogin;
import az.book.store.bookstoreboot.response.RespStatus;
import az.book.store.bookstoreboot.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
@Autowired
private LoginDao loginDao;

    @Override
    public RespLogin login(ReqLogin reqLogin) {
        RespLogin response = new RespLogin();
        String username = reqLogin.getUsername();
        String password = reqLogin.getPassword();
        try {
            if(username == null || password == null){

                response.setStatus(new RespStatus(ExceptionConstants.USERNAME_OR_PASSWORD_IS_EMPTY,"username or password is empty"));
               return response;
            }
           Login login = loginDao.findLoginByUsernameAndPassword(username,password, EnumAvailableStatus.ACTIVE.getValue());
            if(login == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_USERNAME_OR_PASSWORD,"invalid username or password"));
                return response;
            }
            if(login.getToken() != null && !login.getToken().isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.SESSION_IS_ALREADY_EXIST,"session is already exist"));
                response.setToken(login.getToken());
                return response;
            }
            String token = UUID.randomUUID().toString();
            login.setToken(token);
            loginDao.save(login);
            response.setToken(token);
            response.setUsername(login.getUsername());
            response.setName(login.getName());
            response.setStatus(RespStatus.getSuccessMessage());
        }
        catch (Exception ex){
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return null;
    }

    @Override
    public RespStatus logout(String token) {
        RespStatus response = null;
        try{
            if(token == null){
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA,"username or password is empty");
                return response;
            }
           Login login = loginDao.findLoginByTokenAndActive(token, EnumAvailableStatus.ACTIVE.getValue());
            if(login == null){
                response = new RespStatus(ExceptionConstants.INVALID_TOKEN,"invalid token");
                return response;
            }
            login.setToken(null);
            loginDao.save(login);
            response = RespStatus.getSuccessMessage();

        }catch(Exception ex){
            ex.printStackTrace();
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
        }
        return response;
    }
}
