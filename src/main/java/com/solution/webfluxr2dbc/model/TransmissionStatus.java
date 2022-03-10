package com.solution.webfluxr2dbc.model;

public class TransmissionStatus {
    private String status;
    private String message;

    public TransmissionStatus() {
    }

    public TransmissionStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransmisionStatus{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
