package org.example.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.example.entities.Message;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageDao  {
     public List<Message> selectMessageByUser(int user_id);
     public int insertMessage(Message message);
     public void updateMessageRead(int message_id);
}
