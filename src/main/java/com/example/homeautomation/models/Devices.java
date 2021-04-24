package com.example.homeautomation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Devices {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int id;
    @EmbeddedId
    DeviceComposite deviceComposite;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer parent;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String email;

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

    @Override
    public String toString() {
        return "Devices{" +
                "deviceComposite=" + deviceComposite +
                ", status=" + status +
                ", parent=" + parent +
                ", email='" + email + '\'' +
                '}';
    }
}
