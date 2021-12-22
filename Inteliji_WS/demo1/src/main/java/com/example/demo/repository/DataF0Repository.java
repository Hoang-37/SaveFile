package com.example.demo.repository;

import com.example.demo.entity.DataF0;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataF0Repository extends JpaRepository<DataF0, Integer> {
    // Lấy tất cả F0
    @Query(value = "select * from dataf0 ",nativeQuery = true)
    List<DataF0> findAll();
    // Tim theo id user
    @Query(value = "select * from dataf0 where user_id =:id",nativeQuery = true)
    DataF0 findByUserId(@Param("id") int id);
    //Tìm kiếm số lượng F0 qua địa chỉ
    @Query(value = "select f0.f0_id, f0.user_id from dataf0 f0, users us where f0.user_id = us.user_id and us.address =:address",nativeQuery = true)
    List<DataF0> findAllByAddress(@Param("address") String address);
    // xóa đối tượng F0
    @Modifying
    @Query(value = "delete from dataf0 where user_id=:id",nativeQuery = true)
    void  deleteF0(int id);


}
