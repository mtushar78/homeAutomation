package com.example.homeautomation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeviceComposite implements Serializable {

    String mac_address;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int user_id;

    public DeviceComposite() {
    }

    public DeviceComposite(String mac_address, int user_id) {

        this.mac_address = mac_address;
        this.user_id = user_id;
    }


    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
