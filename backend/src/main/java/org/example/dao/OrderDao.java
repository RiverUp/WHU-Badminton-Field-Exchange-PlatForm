package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.entities.Order;
import org.example.entities.ScreenShot;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDao {

    public List<Order> selectOrdersByDate(@Param("begin_time") Date begin_time);

    public List<Order> selectOrdersByDateandGym(@Param("begin_time") Date begin_time, @Param("gym") int gym);

    public List<Order> selectHistoryOrdersByPurchaserId(@Param("user_id") int userId);
    public List<Order> selectHistoryOrdersBySellerId(@Param("user_id") int userId);

    public List<Order> selectAllOrders();

    public int insertOrder(Order order);

    public ScreenShot selecetOrderShotById(int id);

    public void updatePayedShot(@Param("payed_shot_id") int payed_shot_id, @Param("order_id") int order_id);

    public void deleteOrderById(int id);

    public void updatePurchaser(@Param("purchaser_id") int purchaser_id, @Param("order_id") int order_id);

    public Order selectOrderById(int id);
    public Order selectFullOrderById(int id);
    public void updatePurchaserNull(int order_id);
}
