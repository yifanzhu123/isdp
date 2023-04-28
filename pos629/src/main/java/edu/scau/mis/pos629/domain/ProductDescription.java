package edu.scau.mis.pos629.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDescription extends BaseEntity {
    private Long productId;
    private String productSn;
    private Category category;
    private String productName;
    private BigDecimal price;
    private Long categoryId;

    public ProductDescription() {
    }

    public ProductDescription(Long productId, String productSn, Category category, String productName, BigDecimal price) {
        this.productId = productId;
        this.productSn = productSn;
        this.category = category;
        this.productName = productName;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "productId=" + productId +
                ", productSn='" + productSn + '\'' +
                ", category=" + category +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
