package com.example.demo.repository;

import com.example.demo.entity.DataContact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataContactRepository extends JpaRepository<DataContact, Integer> {
    // truy vết f1 qua id f0
    @Query(value = "select * from datacontact where user_one =:id or user_two =:id", nativeQuery = true)
    List<DataContact> findAllByUser(@Param("id") int id);

}
