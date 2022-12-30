package org.example.controller;

import org.apache.ibatis.annotations.Param;
import org.example.entities.Message;
import org.example.service.JsonResult;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @RequestMapping(path = "",method = RequestMethod.GET)
    public JsonResult<List> getMessagesByUser(int user_id){
        return messageService.getMessagesByUser(user_id);
    }
    @RequestMapping(path = "read",method = RequestMethod.GET)
    public JsonResult<String> updateRead(int message_id) {
        return messageService.updateRead(message_id);
    }
}
