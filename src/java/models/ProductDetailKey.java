/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
package models;
import java.util.Objects;

public class ProductDetailKey  {
    private int productId;
    private int size;
    private String color;

    public ProductDetailKey(int productId, int size, String color) {
        this.productId = productId;
        this.size = size;
        this.color = color;
    }

    // Getters, setters, equals, and hashCode methods
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailKey that = (ProductDetailKey) o;
        return productId == that.productId && size == that.size && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, size, color);
    }
}
