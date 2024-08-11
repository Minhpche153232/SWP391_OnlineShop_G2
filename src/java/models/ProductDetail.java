/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class ProductDetail {

    private Product product;
    private int size;
    private String color;
    private int unitInStock;

    public ProductDetail() {
    }

    public ProductDetail(Product product, int size, String color, int unitInStock) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.unitInStock = unitInStock;
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

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "product=" + product + ", size=" + size + ", color=" + color + ", unitInStock=" + unitInStock + '}';
    }

}
