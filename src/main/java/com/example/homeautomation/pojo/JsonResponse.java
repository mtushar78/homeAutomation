package com.example.homeautomation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse {
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    String message;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    String status;

    public JsonResponse() {
    }

    public JsonResponse(String Message, String status) {
        this.message = Message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "message='" + message + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }
}
