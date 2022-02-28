package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addr_id;
    private Integer number_user;
    private Integer number_f1;
    private Integer number_f0;
    @Column(name="address", nullable = false, unique = true)
    private String address;
    private String color_area;

    public Address() {
    }

    public Address(Integer addr_id, Integer number_user, Integer number_f1, Integer number_f0, String address, String color_area) {
        this.addr_id = addr_id;
        this.number_user = number_user;
        this.number_f1 = number_f1;
        this.number_f0 = number_f0;
        this.address = address;
        this.color_area = color_area;
    }

    public Integer getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(Integer addr_id) {
        this.addr_id = addr_id;
    }

    public Integer getNumber_user() {
        return number_user;
    }

    public void setNumber_user(Integer number_user) {
        this.number_user = number_user;
    }

    public Integer getNumber_f1() {
        return number_f1;
    }

    public void setNumber_f1(Integer number_f1) {
        this.number_f1 = number_f1;
    }

    public Integer getNumber_f0() {
        return number_f0;
    }

    public void setNumber_f0(Integer number_f0) {
        this.number_f0 = number_f0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor_area() {
        return color_area;
    }

    public void setColor_area(String color_area) {
        this.color_area = color_area;
    }
}
