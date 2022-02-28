package com.example.demo.repository;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     //Tìm kiếm người dùng
     @Query(value = "select * from users  where fullname like %:fullname% ",nativeQuery = true)
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
     // Thêm F0
     @Modifying
     @Query(value = "update users set status=0 where user_id= :id",nativeQuery = true)
     void addF0(@Param("id") int id);
     // Xóa F0
     @Modifying
     @Query(value = "update users set status=NULL where user_id= :id",nativeQuery = true)
     void deleteF0(@Param("id") int id);
     // Danh sách không phải F0
     @Query(value = "select * from users where status is null or status =1",nativeQuery = true)
     List<User> listNoF0();
     // Danh sách F0
     @Query(value = "select * from users where status =0",nativeQuery = true)
     List<User> listF0();
     // Tìm danh sách F0 theo địa chỉ
     @Query(value = "select * from users where address=:address and status = 0", nativeQuery = true)
     List<User> findF0ByAddress(@Param("address") String address);
     // Tìm danh sách F0 theo id
     @Query(value = "select * from users where user_id=:user_id and status = 0", nativeQuery = true)
     User findF0ById(@Param("user_id") int user_id);
     // Kiểm tra đối tượng truy vết có phải f0
     // Danh sách F1
     @Query(value = "select * from users where status =1",nativeQuery = true)
     List<User> listF1();
     //Thêm F1
     @Modifying
     @Query(value = "update users set status=1 where user_id= :id",nativeQuery = true)
     void addF1(@Param("id") int id);
     //Xóa F1
     @Modifying
     @Query(value = "update users set status=NULL where user_id= :id",nativeQuery = true)
     void deleteF1(@Param("id") int id);

}
