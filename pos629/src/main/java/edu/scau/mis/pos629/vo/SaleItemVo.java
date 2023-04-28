package edu.scau.mis.pos629.vo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 数据表格模型
 * 为了方便前后端传递消息，需要根据前端展示需求设计传值对象类Value Object
 * 前端数据表格需要展示商品编码、商品名称、单价、数量等信息，所以封装了SaleItemVo
 */

public class SaleItemVo implements Serializable {
    private String itemSn;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public SaleItemVo() {
    }

    public SaleItemVo(String itemSn, String productName, BigDecimal price, int quantity) {
        this.itemSn = itemSn;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemSn() {
        return itemSn;
    }

    public void setItemSn(String itemSn) {
        this.itemSn = itemSn;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
