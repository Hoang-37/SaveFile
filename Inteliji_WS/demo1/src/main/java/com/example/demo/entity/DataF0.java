package com.example.demo.entity;
import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="dataf0")
public class DataF0 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer f0_id;


    private Integer user_id;

    public DataF0() {

    }

    public DataF0(Integer f0_id, Integer user_id) {
        this.f0_id = f0_id;
        this.user_id = user_id;
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
