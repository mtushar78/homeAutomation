package com.example.homeautomation.pojo;

public class RemoveShare {
    String mac_address;
    String email;

    public RemoveShare() {
    }

    public RemoveShare(String mac_address, String email) {
        this.mac_address = mac_address;
        this.email = email;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RemoveShare{" +
                "mac_address='" + mac_address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
