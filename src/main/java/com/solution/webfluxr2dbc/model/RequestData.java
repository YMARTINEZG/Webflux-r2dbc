package com.solution.webfluxr2dbc.model;

public class RequestData {
    private Integer id;
    private String message;

    public RequestData() {
    }

    public RequestData(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}
