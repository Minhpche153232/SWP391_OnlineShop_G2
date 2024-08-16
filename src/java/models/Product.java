/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;


public class Product {
    private int productId;
    private String image;
    private String productName;
    private String description;
    private float price;
    private Category category;
    private Brand brand;
    private Type type;
    private boolean active;
    private String productCode;
    private List<ProductDetail> productDetails;
    

    public Product() {
    }

    public Product(int productId, String image, String productName, String description, float price, Category category, Brand brand, Type type, boolean active, String productCode, List<ProductDetail> productDetails) {
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.type = type;
        this.active = active;
        this.productCode = productCode;
        this.productDetails = productDetails;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

   
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", image=" + image + ", productName=" + productName + ", description=" + description + ", price=" + price + ", category=" + category + ", brand=" + brand + ", type=" + type + ", active=" + active + ", productCode=" + productCode + ", productDetails=" + productDetails + '}';
    }

}
