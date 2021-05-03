package com.example.homeautomation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Relay {
    @EmbeddedId
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    RelayComposite relayComposite;
    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String relay_name;
    int status;
    @Nullable
    String mod_time;


    public Relay() {
    }

    public Relay(RelayComposite relayComposite, int status, String mod_time) {
        this.relayComposite = relayComposite;
        this.status = status;
        this.mod_time = mod_time;
    }

    public RelayComposite getRelayComposite() {
        return relayComposite;
    }

    public void setRelayComposite(RelayComposite relayComposite) {
        this.relayComposite = relayComposite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMod_time() {
        return mod_time;
    }

    public void setMod_time(String mod_time) {
        this.mod_time = mod_time;
    }

    public String getRelay_name() {
        return relay_name;
    }

    public void setRelay_name(String relay_name) {
        this.relay_name = relay_name;
    }
}
