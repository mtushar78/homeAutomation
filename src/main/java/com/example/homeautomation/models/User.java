package com.example.homeautomation.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "email",unique = true)
    String email;
    String disp_name;
    @Column(name = "sso_id",unique = true)
    String sso_id;

    public User() {
    }

    public User(int id, String email, String disp_name, String sso_id) {
        this.id = id;
        this.email = email;
        this.disp_name = disp_name;
        this.sso_id = sso_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisp_name() {
        return disp_name;
    }

    public void setDisp_name(String disp_name) {
        this.disp_name = disp_name;
    }

    public String getSso_id() {
        return sso_id;
    }

    public void setSso_id(String sso_id) {
        this.sso_id = sso_id;
    }
}

