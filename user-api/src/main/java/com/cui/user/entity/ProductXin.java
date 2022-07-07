package com.cui.user.entity;

import java.io.Serializable;
import java.util.Objects;


public class ProductXin implements Serializable {
    private String productId;
    private String productName;
    private double productPrice;



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.cui.user.entity.ProductXin that = (com.cui.user.entity.ProductXin) o;
        return productPrice == that.productPrice &&
                productId.equals(that.productId) &&
                productName.equals(that.productName);
    }

    public ProductXin() {
    }

    public ProductXin(String productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice) ;
    }

    @Override
    public String toString() {
        return "ProductXin{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
