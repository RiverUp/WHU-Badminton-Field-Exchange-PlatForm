package org.example.service;

import org.example.dao.MessageDao;
import org.example.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    public JsonResult<List> getMessagesByUser(int user_id) {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Message> messages = messageDao.selectMessageByUser(user_id);
            result.setData(messages);
            result.setMsg("success");
            result.setCode("200");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<String> updateRead(int message_id) {
        JsonResult<String> result = new JsonResult<>(null, "400", "");
        try {
            messageDao.updateMessageRead(message_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData("has been read");
        }catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
}
