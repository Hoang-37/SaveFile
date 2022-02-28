package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.User;
import com.example.demo.repository.DataContactRepository;
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
    private DataContactRepository dataContactRepository;

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
    public List<User> ListNoF0() {
        return userRepository.listNoF0();
    }

    // Danh sách F0
    public List<User> ListF0(){
        return userRepository.listF0();
    }
    // Thêm F0
    public void AddF0(int id){
        userRepository.addF0(id);
        SaveF1(id);
    }
    // Xóa F0
    public void DeleteF0(int id){
        userRepository.deleteF0(id);
        DeleteF1(id);
    }
    // Thêm F1
    public void AddF1(int id){
        userRepository.addF1(id);
    }
    // Lưu danh sách f1 tiếp xúc f0
    public void  SaveF1(int id){
        List<DataContact> listcontact = dataContactRepository.findAllByUser(id);
        for(DataContact contact : listcontact){
            if(contact.getUser_one() == id && userRepository.findByUserId(contact.getUser_two()).getStatus() == null){
                userRepository.addF1(contact.getUser_two());
            }
            if(contact.getUser_two() == id &&  userRepository.findByUserId(contact.getUser_one()).getStatus() == null){
                userRepository.addF1(contact.getUser_one());
            }

        }
    }
    // Xóa danh sách f1 tiếp xúc F0
    public void DeleteF1(int id){
        List<DataContact> listcontact = dataContactRepository.findAllByUser(id);
        for(DataContact contact : listcontact){
            if(contact.getUser_one() == id && userRepository.findByUserId(contact.getUser_two()).getStatus() == true){
                userRepository.deleteF1(contact.getUser_two());
            }
            if(contact.getUser_two() == id &&  userRepository.findByUserId(contact.getUser_one()).getStatus() == true){
                userRepository.deleteF1(contact.getUser_one());
            }

        }
    }
    // Lấy dữ liệu F1 qua tiếp xúc F0
    public List<User> ListF1ByF0(int id_f0){
        List<DataContact> listcontact = dataContactRepository.findAllByUser(id_f0);
        List<User> list = new ArrayList<>();
        for(DataContact contact : listcontact){
            if(contact. getUser_one() == id_f0 && userRepository.findByUserId(contact.getUser_two()).getStatus() == true){
                list.add(userRepository.findByUserId(contact.getUser_two()));
            }
            if(contact.getUser_two() == id_f0 &&  userRepository.findByUserId(contact.getUser_one()).getStatus() == true){
                list.add(userRepository.findByUserId(contact.getUser_two()));
            }
        }
        return list;
    }
    // Tìm danh sách F0 theo địa chỉ
    public List<User> FindF0ByAddress(String address){
       return userRepository.findF0ByAddress(address);
    }
    // Tìm danh sách F1
    public  List<User> ListF1(){
        return userRepository.listF1();
    }
}
