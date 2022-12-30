package org.example.service;

import org.example.dao.MessageDao;
import org.example.dao.OrderDao;
import org.example.dao.ScreenShotDao;
import org.example.dao.UserDao;
import org.example.entities.Message;
import org.example.entities.Order;
import org.example.entities.ScreenShot;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ScreenShotDao screenShotDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    UserDao userDao;

    public JsonResult<List> getOrdersAvailable() {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            Date date = new Date();
            List<Order> orders;
            orders = orderDao.selectOrdersByDate(date);
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                if (order.purchaser != null) {
                    iterator.remove();
                }
            }
            result.setData(orders);
            result.setMsg("success");
            result.setCode("200");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }

    }

    public JsonResult<List> getOrdersSatisfied(int gym, Date begin_time) {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Order> orders;
            orders = orderDao.selectOrdersByDateandGym(begin_time, gym);
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                if (order.purchaser != null) {
                    iterator.remove();
                }
            }
            result.setData(orders);
            result.setMsg("success");
            result.setCode("200");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<ScreenShot> getOrder_Shot(int order_id, int payed_id) {
        JsonResult<ScreenShot> result = new JsonResult<>();
        try {
            orderDao.updatePayedShot(payed_id, order_id);
            ScreenShot screenShot = orderDao.selecetOrderShotById(order_id);
            Order order = orderDao.selectOrderById(order_id);
            Message message = new Message();
            message.setReceiver(order.purchaser);
            message.setOrder(order);
            String gym;
            switch (order.gym) {
                case 0:
                    gym = "卓尔体育馆";
                    break;
                case 1:
                    gym = "风雨体育馆";
                    break;
                case 2:
                    gym = "工学部体育馆";
                    break;
                case 3:
                    gym = "信息学部体育馆";
                    break;
                case 4:
                    gym = "网安体育馆";
                    break;
                case 5:
                    gym = "医学部体育馆";
                    break;
                default:
                    gym = "";
                    break;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = simpleDateFormat.format(order.begin_time);
            message.setContent("订单编号：" + order.id + "///" + gym + order.field_no + "号场地" + "///" + " 开始于：" + date);
            message.setType(false);
            messageDao.insertMessage(message);
            result.setData(screenShot);
            result.setCode("200");
            result.setMsg("success");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setCode("400");
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<List> getHistoryOrdersOfSeller(int user_id) {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Order> orders = orderDao.selectHistoryOrdersBySellerId(user_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(orders);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
    public JsonResult<List> getHistoryOrdersOfPurchaser(int user_id) {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Order> orders = orderDao.selectHistoryOrdersByPurchaserId(user_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(orders);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<List> getAllOrders() {
        JsonResult<List> result = new JsonResult<>(null, "400", "");
        try {
            List<Order> orders = orderDao.selectAllOrders();
            result.setMsg("success");
            result.setCode("200");
            result.setData(orders);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<Order> withdrawOrder(int order_id) {
        JsonResult<Order> result = new JsonResult<>(null, "400", "");
        try {
            orderDao.deleteOrderById(order_id);
            result.setMsg("success");
            result.setCode("200");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<Order> handInField_To_Exchange(Order order) {
        JsonResult<Order> result = new JsonResult<>(null, "400", "");
        try {
            orderDao.insertOrder(order);
            result.setMsg("success");
            result.setCode("200");
            result.setData(order);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<String> updatePurchaser(int purchaser_id, int order_id) {
        JsonResult<String> result = new JsonResult<>(null, "400", "");
        try {
            orderDao.updatePurchaser(purchaser_id, order_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData("locked");
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<String> updatePurchaserNull(int order_id) {
        JsonResult<String> result = new JsonResult<>(null, "400", "");
        try {
            orderDao.updatePurchaserNull(order_id);
            result.setMsg("success");
            result.setCode("200");
            result.setData("unlocked");
        }catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }

    public JsonResult<Boolean> getLocked(int order_id) {
        JsonResult<Boolean> result = new JsonResult<>(null, "400", "");
        try {
            Order order = orderDao.selectOrderById(order_id);
            result.setMsg("success");
            result.setCode("200");
            if (order.purchaser == null) {
                result.setData(false);
            } else {
                result.setData(true);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
}
