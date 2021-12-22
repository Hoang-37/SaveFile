package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.DataF0;
import com.example.demo.entity.User;


import com.example.demo.repository.DataContactRepository;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataContactService {

    @Autowired
    private DataContactRepository dataContactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataF0Repository dataF0Repository;

    // Lấy dữ liệu F1 qua tiếp xúc F0
    public List<User> FindALLF1ById(int f0_id) {
        List<DataContact> listcontact = dataContactRepository.findAllByUser(f0_id);
        List<User> list = new ArrayList<>();
        for (DataContact contact : listcontact) {
            User user;
            if(contact.getUser_one()== f0_id){
                user = userRepository.findByUserId(contact.getUser_two());
            }else{
                user = userRepository.findByUserId(contact.getUser_one());
            }
            if(dataF0Repository.findByUserId(user.getUser_id()) == null){
                list.add(user);
            }

        }
        return list;
    }

    //Random tiếp xúc
    public void  SaveRandom(int id) {
        int number = userRepository.findAll().size();

        for (int i = 1; i <= number; i++) {
            Random rand = new Random();
            int result = rand.nextInt(3)+1;
            if(result == 1){
                DataContact contact = new DataContact();
                contact.setUser_one(id);
                contact.setUser_two(i);
                dataContactRepository.save(contact);
            }
        }
    }
    // Lây tất cả thông tin danh sách f1
    public List<User> FindAllInforF1(){
        List<DataF0> listf0 = dataF0Repository.findAll();
        List<User> list = new ArrayList<>();
        for(DataF0 f0 :listf0){
           List<User> listf1 = FindALLF1ById(f0.getUser_id());
           for(User user : listf1){
               list.add(user);
           }
         }
        Set<User> set = new HashSet<>(list);
        List<User> list1 = new ArrayList<>(set);
        return list1;
    }
    // Lấy danh sách f1 theo địa chỉ
    public List<User> FindAllF1ByAddress(String address){
        List<User> listf1 = FindAllInforF1();
        List<User> list = new ArrayList<>();
        for(User user : listf1){
            if(user.getAddress().equals(address)){
                list.add(user);
            }
        }
        return list;
    }
}

