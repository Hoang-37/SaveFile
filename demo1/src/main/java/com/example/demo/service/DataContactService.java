package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.User;


import com.example.demo.repository.DataContactRepository;
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
            if(userRepository.findF0ById(user.getUser_id()) == null){
                list.add(user);
            }
        }
        return list;
    }
    // Check tiếp xúc F0
    public boolean CheckF1(int id){
        List<DataContact> listcontact = dataContactRepository.findAllByUser(id);
        for (DataContact contact : listcontact) {
            if(contact.getUser_one()== id && userRepository.findF0ById(contact.getUser_two()) != null){
                return false;
            }else if(contact.getUser_two()== id && userRepository.findF0ById(contact.getUser_one()) != null){
                return false;
            }
        }
        return true;
    }
    //Random tiếp xúc
    public void  SaveRandom(int id){
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

}

