package org.example.service;

import org.example.dao.UserDao;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public JsonResult<User> login(User user) {
        JsonResult<User> result;
        User userToLog = userDao.selectUserByName(user.name);
        if (userToLog == null) {
            result = new JsonResult<>(null, "404", "user not found");
            return result;
        } else {
            if (userToLog.identity == user.identity && userToLog.passwords.equals(user.passwords)  && userToLog.blocked == false) {
                result = new JsonResult<>(userToLog, "200", "success");
            } else if (!userToLog.passwords.equals(user.passwords)) {
                result = new JsonResult<>(null, "401", "passwords wrong");
            } else if (userToLog.blocked == true) {
                result = new JsonResult<>(null, "423", "user account is blocked");
            } else {
                result = new JsonResult<>(null, "401", "identity wrong");
            }
            return result;
        }
    }

    public JsonResult<User> signup(User user) {
        JsonResult<User> result;
        User userToSignup = userDao.selectUserByName(user.name);
        if (userToSignup != null) {
            result = new JsonResult<>(null, "409", "user name has existed");
        } else {
            userDao.insertUser(user);
            result = new JsonResult<>(user, "200", "success");
        }
        return result;
    }

    public JsonResult<List> getAllUsers() {
        JsonResult<List> result=new JsonResult<>(null,"400","");
        try {
            List<User> list = userDao.selectAllUsers();
            result.setMsg("success");
            result.setCode("200");
            result.setData(list);
        }catch (Exception exception){
            System.out.println(exception);
            result.setMsg(exception.toString());
        }finally {
            return result;
        }
    }

    public JsonResult<User> blockUser(int user_id) {
        JsonResult<User> result = new JsonResult<>(null,"400","");
        try {
            userDao.updateBlocked(user_id); result.setMsg("success");
            result.setCode("200");
        }catch (Exception exception){
            System.out.println(exception);
            result.setMsg(exception.toString());
        }finally {
            return result;
        }
    }
}
