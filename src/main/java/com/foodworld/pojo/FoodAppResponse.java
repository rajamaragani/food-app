package com.foodworld.pojo;

public class FoodAppResponse {

    private String msg;
    private Object response;

    public FoodAppResponse(String msg, Object response) {
        super();
        this.msg = msg;
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
