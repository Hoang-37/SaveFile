package com.example.demo.entity;
import javax.persistence.*;

@Entity
@Table(name="datacontact")
public class DataContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private Integer user_one;

    private Integer user_two;

    public DataContact(Integer id, Integer user_one, Integer user_two) {
        this.id = id;
        this.user_one = user_one;
        this.user_two = user_two;
    }

    public DataContact() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_one() {
        return user_one;
    }

    public void setUser_one(Integer user_one) {
        this.user_one = user_one;
    }

    public Integer getUser_two() {
        return user_two;
    }

    public void setUser_two(Integer user_two) {
        this.user_two = user_two;
    }
}
