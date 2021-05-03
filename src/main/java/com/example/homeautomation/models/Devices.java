package com.example.homeautomation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Devices {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int id;
    @EmbeddedId
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    DeviceComposite deviceComposite;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String device_mac;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer parent;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Relay> relays;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<String> sharedTo;



    public Devices() {
    }

    public Devices(DeviceComposite deviceComposite, int status, Integer parent, String email) {
        this.deviceComposite = deviceComposite;
        this.status = status;
        this.parent = parent;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeviceComposite getDeviceComposite() {
        return deviceComposite;
    }

    public void setDeviceComposite(DeviceComposite deviceComposite) {
        this.deviceComposite = deviceComposite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Relay> getRelays() {
        return relays;
    }

    public void setRelays(List<Relay> relays) {
        this.relays = relays;
    }

    public List<String> getSharedTo() {
        return sharedTo;
    }

    public void setSharedTo(List<String> sharedTo) {
        this.sharedTo = sharedTo;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "id=" + id +
                ", deviceComposite=" + deviceComposite +
                ", status=" + status +
                ", parent=" + parent +
                ", email='" + email + '\'' +
                ", relays=" + relays +
                ", sharedTo='" + sharedTo + '\'' +
                '}';
    }
}
