package com.example.demo.repository;

import com.example.demo.entity.DataF1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface DataF1Repository extends JpaRepository<DataF1, Integer> {
   @Query(value = "select * from dataf1 where f0_id=:id",nativeQuery = true)
       List<DataF1> findByIdF0(@Param("id") int id);
    @Query(value = "select * from dataf1 where user_id=:id",nativeQuery = true)
    List<DataF1> findByIdUser(@Param("id") int id);
}
