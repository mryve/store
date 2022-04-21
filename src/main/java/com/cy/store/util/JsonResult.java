package com.cy.store.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    private String message;
    private Integer state;
    private E data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public JsonResult(String message, Integer state) {
        this.message = message;
        this.state = state;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(E data) {
        this.data = data;
    }

    public JsonResult(String message, Integer state, E data) {
        this.message = message;
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonResult() {
    }
}
