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
public class OrderItem {
     private Product product;
    private int size;
    private String color;
    private String image;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Product product, int size, String color, String image, Order order) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.image = image;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

   

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product=" + product + ", size=" + size + ", color=" + color + ", image=" + image + '}';
    }
    
}
