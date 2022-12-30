package org.example.entities;

import java.util.Date;

public class Message {
    public int id;
    public User receiver;
    public String content;
    public Order order;
    public boolean type;
    public boolean read;

    public Message() {
    }

    public Message(int id, User receiver, String content, Order order, boolean type, boolean read) {
        this.id = id;
        this.receiver = receiver;
        this.content = content;
        this.order = order;
        this.type = type;
        this.read = read;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                '}';
    }


    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
