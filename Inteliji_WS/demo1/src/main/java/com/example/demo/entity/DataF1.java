package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="dataf1")
public class DataF1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer f1_id;
    private Integer f0_id;
    private Integer user_id;

    public DataF1() {
    }

    public DataF1(Integer f1_id, Integer f0_id, Integer user_id) {
        this.f1_id = f1_id;
        this.f0_id = f0_id;
        this.user_id = user_id;
    }

    public Integer getF1_id() {
        return f1_id;
    }

    public void setF1_id(Integer f1_id) {
        this.f1_id = f1_id;
    }

    public Integer getF0_id() {
        return f0_id;
    }

    public void setF0_id(Integer f0_id) {
        this.f0_id = f0_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
