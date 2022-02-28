package com.example.demo.entity;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(name = "fullname", nullable = false, length = 20)
    private String fullname;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "address")
    private String address;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(unique = true, nullable = false, length = 64)
    private Integer cmnd;
    private String username;
    private Boolean status;

    public User() {
    }

    public User(Integer user_id, String fullname, String birthday, String address, String email, Integer cmnd, Boolean status) throws ParseException {
        this.user_id = user_id;

        this.fullname = fullname;
        java.util.Date birth = (java.util.Date) new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        java.sql.Date sqlDate = new java.sql.Date(birth.getTime());
        this.birthday = sqlDate;
        this.address = address;
        this.email = email;
        this.cmnd = cmnd;
        this.status= status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public  Date getBirthday() {

        return birthday;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(String birthday) throws ParseException {
        java.util.Date birth = (java.util.Date) new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        java.sql.Date sqlDate = new java.sql.Date(birth.getTime());
        this.birthday = sqlDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCmnd() {
        return cmnd;
    }

    public void setCmnd(Integer cmnd) {
        this.cmnd = cmnd;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
