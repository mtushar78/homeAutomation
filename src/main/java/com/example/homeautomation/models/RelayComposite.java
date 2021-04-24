package com.example.homeautomation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RelayComposite implements Serializable {
    String relay_name;
    String device_mac;

    public RelayComposite() {
    }

    public RelayComposite(String relay_name, String device_mac) {
        this.relay_name = relay_name;
        this.device_mac = device_mac;
    }

    public String getRelay_name() {
        return relay_name;
    }

    public void setRelay_name(String relay_name) {
        this.relay_name = relay_name;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }
}
