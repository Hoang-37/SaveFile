package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "declaration")
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dec_id;
    private Integer user_id;

    private Integer phonenumber;
    private Date time;
    private Blob schedule;

    public Declaration() {
    }

    public Declaration(Integer dec_id, Integer user_id, Integer phonenumber, Date time, Blob schedule) {
        this.dec_id = dec_id;
        this.user_id = user_id;
        this.phonenumber = phonenumber;
        java.sql.Date sqlDate = new java.sql.Date(time.getTime());
        this.time = sqlDate;
        this.schedule = schedule;
    }

    public Integer getDec_id() {
        return dec_id;
    }

    public void setDec_id(Integer dec_id) {
        this.dec_id = dec_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        java.sql.Date sqlDate = new java.sql.Date(time.getTime());
        this.time = sqlDate;
    }

    public Blob getSchedule() {
        return schedule;
    }

    public void setSchedule(Blob schedule) {
        this.schedule = schedule;
    }
}
