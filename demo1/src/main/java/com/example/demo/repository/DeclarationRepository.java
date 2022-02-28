package com.example.demo.repository;

import com.example.demo.entity.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Integer> {
    //Lưu vào CSDL
    @Modifying
    @Query(value = "INSERT INTO declaration(schedule, user_id, time) values (LOAD_FILE(:filePath), :user_id, :time)",nativeQuery = true)
    void saveFile(@Param("filePath") String filePath, @Param("user_id") int user_id, @Param("time") Date time);
    // TÌm kiếm qua id người dung
    @Query(value = "select * from declaration where user_id =:user_id",nativeQuery = true)
    Declaration findByUserId(@Param("user_id") int user_id);
}
