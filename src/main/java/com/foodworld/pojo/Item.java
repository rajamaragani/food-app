/*******************************************************************************
 *   * Copyright (C) 2017   Raja Maragani  rajamaragani@gmail.com
 *   * 
 *   * This file is part of foodapplication
 *   * 
 *   * foodapplication can not be copied and/or distributed without the express
 *   * permission of Raja Maragani
 ******************************************************************************/

package com.foodworld.pojo;

/**
 * 
 * @author raja maragani
 *
 */
public class Item {
    private int id;
    private String type;
    private String itemName;
    private int price;
    private int discount;
    private int totalPrice;
    private String whichPriceShow;
    private String itemLogo;
    private int status;
    private int itemQuantity;

    public Item() {
        super();
    }

    public Item(int id, String type, String itemName, int price, int discount, int totalPrice, String whichPriceShow,
            String itemLogo, int status) {
        super();
        this.id = id;
        this.type = type;
        this.itemName = itemName;
        this.price = price;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.whichPriceShow = whichPriceShow;
        this.itemLogo = itemLogo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWhichPriceShow() {
        return whichPriceShow;
    }

    public void setWhichPriceShow(String whichPriceShow) {
        this.whichPriceShow = whichPriceShow;
    }

    public String getItemLogo() {
        return itemLogo;
    }

    public void setItemLogo(String itemLogo) {
        this.itemLogo = itemLogo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

}
