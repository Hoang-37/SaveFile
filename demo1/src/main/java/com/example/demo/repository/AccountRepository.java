package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account acc where acc.username = :username ",nativeQuery = true)
    Account findByUsername(@Param("username") String username);

    @Query(value = "select * from account acc where acc.username = :username and acc.password = :password",nativeQuery = true)
    Account findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);



}
