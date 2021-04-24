package com.example.homeautomation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse {
    @JsonProperty
    String Message;
    @JsonProperty
    String Status;

    public JsonResponse() {
    }

    public JsonResponse(String Message, String status) {
        this.Message = Message;
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "message='" + Message + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
