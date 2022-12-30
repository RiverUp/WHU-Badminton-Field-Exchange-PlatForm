package org.example.controller;

import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.service.JsonResult;
import org.example.service.UserService;

import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public JsonResult<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping(path = "signup", method = RequestMethod.POST)
    public JsonResult<User> signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public JsonResult<List> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(path = "/block", method = RequestMethod.GET)
    public JsonResult<User> blockUser(int user_id) {
        return userService.blockUser(user_id);
    }
}
