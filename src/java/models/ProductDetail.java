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
    private String image;
    private int productId;

    public ProductDetail() {
    }

    public ProductDetail(Product product, int size, String color, int unitInStock, String image, int productId ) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.unitInStock = unitInStock;
        this.image = image;
        this.productId = productId;
    }

     public ProductDetail(int productId ,int size, String color, int unitInStock, String image) {
        this.size = size;
        this.color = color;
        this.unitInStock = unitInStock;
        this.image = image;
        this.productId = productId;
    }

    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "ProductDetail{" + "product=" + product + ", size=" + size + ", color=" + color + ", unitInStock=" + unitInStock + ", image=" + image + '}';
    }

    

}
