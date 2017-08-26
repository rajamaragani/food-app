package com.foodworld.pojo;

import java.util.Map;

/**
 * 
 * @author raja maragani
 *
 */
public class Item {

    private String type;
    private String itemName;
    private float price;
    private float discount;
    private Map<String, Float> tax;
    private String totalPrice;
    private String whichPriceShow;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Map<String, Float> getTax() {
        return tax;
    }

    public void setTax(Map<String, Float> tax) {
        this.tax = tax;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWhichPriceShow() {
        return whichPriceShow;
    }

    public void setWhichPriceShow(String whichPriceShow) {
        this.whichPriceShow = whichPriceShow;
    }

}
