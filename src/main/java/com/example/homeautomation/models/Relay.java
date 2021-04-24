package com.example.homeautomation.models;

import org.springframework.lang.Nullable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Relay {
    @EmbeddedId
    RelayComposite relayComposite;
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
}
