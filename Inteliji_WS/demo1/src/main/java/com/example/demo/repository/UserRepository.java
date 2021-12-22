package com.example.demo.repository;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     //Tìm kiếm người dùng
     @Query(value = "select * from users u where u.fullname like %:fullname% ",nativeQuery = true)
     List<User> findAllLikeName(@Param("fullname") String fullname);
     // Tìm kiếm địa chỉ
     @Query(value = "select * from users u where u.address like %:address% ",nativeQuery = true)
     List<User> findAllLikeAddress(@Param("address") String address);
     //Tìm theo id
     @Query(value = "select * from users u where u.user_id =:id ",nativeQuery = true)
     User findByUserId(@Param("id") int id);
     // TÌm địa chỉ chính xác
     @Query(value = "select * from users  where address =:address",nativeQuery = true)
     List<User>  findAllByAddress(@Param("address") String address);
     //Tìm kiếm theo tài khoản Account
     @Query(value = "select * from users where username=:username",nativeQuery = true)
     User findUserByAccount(@Param("username") String username);
     // Lấy thông tin F0 qua địa chỉ



}
