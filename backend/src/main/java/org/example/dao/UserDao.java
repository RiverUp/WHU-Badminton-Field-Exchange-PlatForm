package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {
    public User selectUserByName(String name);

    public int insertUser(User user);

    public void updateCreditworthiness(int id);

    public List<User> selectAllUsers();

    public void updateBlocked(int id);

    public User selectUserById(int id);
}
