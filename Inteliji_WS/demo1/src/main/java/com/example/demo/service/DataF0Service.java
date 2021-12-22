package com.example.demo.service;

import com.example.demo.entity.DataF0;
import com.example.demo.entity.User;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataF0Service {
    @Autowired
    private DataF0Repository repository;
    @Autowired
    private UserRepository userRepository;


    // Thêm F0
    @Transactional
    public void Save(DataF0 F0){
        repository.save(F0);

    }
    //Lấy tất cả danh sách F0
    public List<DataF0>  FindAll(){
        return repository.findAll();
    }
    //Lấy tất cả danh sách F0 theo địa chỉ
    public List<DataF0> FindAllByAddress(String address){
        return repository.findAllByAddress(address);
    }
    // Lấy thông tin  của F0
    public List<User> getAllInforF0(){
        List<DataF0> listF0 = repository.findAll();
        List<User> listuser = new ArrayList<>();
        for(DataF0 f0 : listF0){
            User user = userRepository.findByUserId(f0.getUser_id());
            listuser.add(user);
        }
        return listuser;
    }

    // Xoa F0
    @Transactional
    public void DeleteF0(int id){
        repository.deleteF0(id);

    }


}
