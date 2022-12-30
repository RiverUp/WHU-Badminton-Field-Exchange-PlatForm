package org.example.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.entities.Order;
import org.example.entities.ScreenShot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.service.JsonResult;
import org.example.service.OrderService;

import java.util.Date;
import java.util.List;


@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(path = "availableOrders", method = RequestMethod.GET)
    public JsonResult<List> getOrdersAvailable() {
        return orderService.getOrdersAvailable();
    }

    @RequestMapping(path = "satisfiedOrders", method = RequestMethod.GET)
    public JsonResult<List> getOrdersSatisfied(int gym, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") Date begin_time) {
        return orderService.getOrdersSatisfied(gym, begin_time);
    }

    @RequestMapping(path = "order_shot", method = RequestMethod.GET)
    public JsonResult<ScreenShot> getOrder_Shot(int order_id, int payed_id) {
        return orderService.getOrder_Shot(order_id, payed_id);
    }

    @RequestMapping(path = "historySellerOrders", method = RequestMethod.GET)
    public JsonResult<List> getHistoryOrdersOfSeller(int user_id) {
        return orderService.getHistoryOrdersOfSeller(user_id);
    }
    @RequestMapping(path = "historyPurchaserOrders", method = RequestMethod.GET)
    public JsonResult<List> getHistoryOrdersOfPurchaser(int user_id) {
        return orderService.getHistoryOrdersOfPurchaser(user_id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public JsonResult<List> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public JsonResult<Order> withdrawOrder(int order_id) {
        return orderService.withdrawOrder(order_id);
    }

    @RequestMapping(path = "field_to_exchange", method = RequestMethod.POST)
    public JsonResult<Order> handInField_To_Exchange(@RequestBody Order order) {
        return orderService.handInField_To_Exchange(order);
    }

    @RequestMapping(path = "purchaser", method = RequestMethod.GET)
    public JsonResult<String> updatePurchaser(int purchaser_id, int order_id) {
        return orderService.updatePurchaser(purchaser_id, order_id);
    }

    @RequestMapping(path = "/locked", method = RequestMethod.GET)
    public JsonResult<Boolean> getLocked(int order_id) {
        return orderService.getLocked(order_id);
    }
    @RequestMapping(path = "purchaserNull", method = RequestMethod.GET)
    public JsonResult<String> updatePurchaserNull(int order_id){
        return orderService.updatePurchaserNull(order_id);
    }
}
