package com.example.demo.repository;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>  {

    @Query(value = "select * from address  where address like %:address% ",nativeQuery = true)
    List<Address> findAllLikeAddress(@Param("address") String address);

    //Tìm theo id
    @Query(value = "select * from address where addr_id =:id ",nativeQuery = true)
    Address findById(@Param("id") int id);

    //Tìm địa chỉ chính xác
    @Query(value = "select * from address  where address=:address ",nativeQuery = true)
    Address findByAddress(@Param("address") String address);

    //Tìm địa chỉ chứa F0
    @Query(value = "select * from address where number_f0 > 0",nativeQuery = true)
    List<Address> findAllAddressF0();
    //Tìm địa chỉ chứa F1
    @Query(value = "select * from address where number_f1 > 0",nativeQuery = true)
    List<Address> findAllAddressF1();
    //Thay đổi Number_user
    @Modifying
    @Query(value = "update address set number_user=:counts where address=:address",nativeQuery = true)
     void updateNumber(@Param("counts") int counts, @Param("address") String address);
     // Thay đổi Number_F0 qua address
    @Modifying
    @Query(value = "update address set number_f0=:counts where address=:address",nativeQuery = true)
    void updateNumber_f0(@Param("counts") int counts,@Param("address") String address);
    // Thay đổi Number_F1 qua address
    @Modifying
    @Query(value = "update address set number_f1 = :counts where address=:address",nativeQuery = true)
    void updateNumber_f1(@Param("counts") int counts , @Param("address") String address);
    // Thay đổi Number_f1 khi xóa f0
    @Modifying
    @Query(value = "update address set number_f1 =number_f1 - 1 where address=:address",nativeQuery = true)
    void deleteNumber_f1(@Param("address") String address);
}
