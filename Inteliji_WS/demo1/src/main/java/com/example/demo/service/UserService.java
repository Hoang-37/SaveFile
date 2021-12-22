package com.example.demo.service;

import com.example.demo.entity.DataF0;
import com.example.demo.entity.User;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataF0Repository dataF0Repository;

    // Lưu người dùng
    public void Save(User user) {
        userRepository.save(user);
    }

    // Lấy thông tin tất cả người dùng
    public List<User> FindAll() {
        return userRepository.findAll();
    }

    // Tìm kiếm người dùng theo tên
    public List<User> FindAllLikeName(String fullname) {
        return userRepository.findAllLikeName(fullname);
    }
    // Tìm kiếm theo tài khoản người dùng
    public User FindUserByAccount(String username){
        return  userRepository.findUserByAccount(username);
    }

    //Tìm theo ID
    public User FindByUserId(int id) {
        return userRepository.findByUserId(id);
    }

    //Tìm kiếm danh sach người dùng theo địa chỉ chính xác

    public List<User> FindAllByAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    // Danh sách khác F0
    public List<User> ListDifferentF0() {
        List<User> listuser = userRepository.findAll();
        List<User> list = new ArrayList<>();
        for (User user : listuser) {
            if (dataF0Repository.findByUserId(user.getUser_id()) == null) {
                list.add(user);
            }
        }
        return list;
    }
    // Lấy thông tin danh sách F0 trong cùng một địa chỉ
    public List<User> ListF0InAddress(String address){
        List<DataF0> listf0 = dataF0Repository.findAllByAddress(address);
        List<User> list = new ArrayList<>();
        for (DataF0 data : listf0) {
            list.add(userRepository.findByUserId(data.getUser_id()));
        }
        return list;
    }

}
