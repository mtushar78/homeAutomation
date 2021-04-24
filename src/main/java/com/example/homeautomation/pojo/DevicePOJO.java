package com.example.homeautomation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DevicePOJO {
    @JsonProperty
    String mac;

    public DevicePOJO() {
    }

    public DevicePOJO(String mac) {
        this.mac = mac;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "DevicePOJO{" +
                "mac='" + mac + '\'' +
                '}';
    }
}
