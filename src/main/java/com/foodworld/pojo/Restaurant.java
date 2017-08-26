package com.foodworld.pojo;

import java.util.List;

/**
 * 
 * @author raja maragani
 *
 */
public class Restaurant {

    private String _id;
    private String type;
    private String name;
    private String restLogo;
    private Address adress;
    private String rating;
    private int dliveryTimeInMin;
    private List<Item> items;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestLogo() {
        return restLogo;
    }

    public void setRestLogo(String restLogo) {
        this.restLogo = restLogo;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getDliveryTimeInMin() {
        return dliveryTimeInMin;
    }

    public void setDliveryTimeInMin(int dliveryTimeInMin) {
        this.dliveryTimeInMin = dliveryTimeInMin;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
