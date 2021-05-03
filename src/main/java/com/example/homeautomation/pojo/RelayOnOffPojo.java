package com.example.homeautomation.pojo;

public class RelayOnOffPojo {
    String mac;
    String relay_name;
    int status;

    public RelayOnOffPojo() {
    }

    public RelayOnOffPojo(String mac, String relayName, int status) {
        this.mac = mac;
        this.relay_name = relayName;
        this.status = status;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRelay_name() {
        return relay_name;
    }

    public void setRelay_name(String relay_name) {
        this.relay_name = relay_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RelayOnOffPojo{" +
                "mac='" + mac + '\'' +
                ", relay_name='" + relay_name + '\'' +
                ", status=" + status +
                '}';
    }
}
