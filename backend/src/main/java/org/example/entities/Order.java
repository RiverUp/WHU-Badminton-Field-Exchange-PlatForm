package org.example.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    public int id;
    public User seller;
    public User purchaser;
    public int gym;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date begin_time;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date finish_time;
    public int field_no;
    public ScreenShot fieldOrder;
    public ScreenShot payment;
    public ScreenShot payed;

    public Order() {
    }

    public Order(int id, User seller, User purchaser, int gym,Date begin_time, Date finish_time, int field_no, ScreenShot fieldOrder, ScreenShot payment, ScreenShot payed) {
        this.id = id;
        this.seller = seller;
        this.purchaser = purchaser;
        this.gym = gym;
        this.begin_time = begin_time;
        this.finish_time = finish_time;
        this.field_no = field_no;
        this.fieldOrder = fieldOrder;
        this.payment = payment;
        this.payed = payed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }


    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }


    public ScreenShot getPayment() {
        return payment;
    }

    public void setPayment(ScreenShot payment) {
        this.payment = payment;
    }

    public ScreenShot getPayed() {
        return payed;
    }

    public void setPayed(ScreenShot payed) {
        this.payed = payed;
    }
    public int getField_no() {
        return field_no;
    }

    public void setField_no(int field_no) {
        this.field_no = field_no;
    }

    public ScreenShot getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(ScreenShot fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", seller=" + seller +
                ", purchaser=" + purchaser +
                ", gym=" + gym +
                ", begin_time=" + begin_time +
                ", finish_time=" + finish_time +
                ", field_no=" + field_no +
                ", fieldOrder=" + fieldOrder +
                ", payment=" + payment +
                ", payed=" + payed +
                '}';
    }
}
