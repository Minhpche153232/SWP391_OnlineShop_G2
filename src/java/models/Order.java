/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class Order {

    private int orderId;
    private User user;
    private float totalPrice;
    private String orderDate;
    private String shipDate;
    private String toAddress;
    private User staff;
    private String status;

    public Order() {
    }

    public Order(int orderId,  User user, float totalPrice, String orderDate, String shipDate, String toAddress, User staff, String status) {
        this.orderId = orderId;
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.toAddress = toAddress;
        this.staff = staff;
        this.status = status;
    }

    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", user=" + user + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", toAddress=" + toAddress + ", staff=" + staff + ", status=" + status + '}';
    }



}
