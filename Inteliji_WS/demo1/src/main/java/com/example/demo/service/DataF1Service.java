package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.DataF1;
import com.example.demo.entity.User;
import com.example.demo.repository.DataContactRepository;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.DataF1Repository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DataF1Service {
    @Autowired
    private DataF1Repository dataF1Repository;
    @Autowired
    private DataF0Repository dataF0Repository;
    @Autowired
    private DataContactRepository dataContactRepository;
    @Autowired
    private UserRepository userRepository;
    // Lưu thông tin F1
    public void SaveF1(List<DataF1> listf1){
        for(DataF1 f1 : listf1){

           if(dataF1Repository.findByIdF0(f1.getUser_id()) == null && dataF1Repository.findByIdUser(f1.getF0_id()) == null){
               dataF1Repository.save(f1);
           }
           if(dataF1Repository.findByIdUser(f1.getF0_id()) != null && dataF1Repository.findByIdF0(f1.getUser_id()) == null){
              for(DataF1 dataF1 :listf1){
                   dataF1Repository.delete(dataF1);
               }
           }

        }
    }
    public void DeleteF1(List<DataF1> listf1){
        for(DataF1 f1 : listf1){
            dataF1Repository.delete(f1);
        }
    }
    // Lấy danh sách f1 tiếp xúc f0
    public List<DataF1> FindAllF1ByIdF0(int id){
        List<DataContact> listcontact = dataContactRepository.findAllByUser(id);
        List<DataF1> listdataf1 = new ArrayList<>();
        for(DataContact contact : listcontact){
            DataF1 f1 = new DataF1();
            if(contact.getUser_one() == id){
                f1.setF0_id(id);
                f1.setUser_id(contact.getUser_two());
            }
            if(contact.getUser_two() == id){
                f1.setF0_id(id);
                f1.setUser_id(contact.getUser_one());
            }
            listdataf1.add(f1);
        }
        return listdataf1;
    }
    // Lấy thông tin danh sách f1
    public List<User> FindAllF1(){
        List<DataF1> listf1 = dataF1Repository.findAll();
        List<User> list = new ArrayList<>();
        for(DataF1 f1 : listf1){
            User user = userRepository.findByUserId(f1.getUser_id());
            list.add(user);
        }
        Set<User> set = new HashSet<>(list);
        List<User> list1 = new ArrayList<>(set);
        return list1;
    }
    // Lấy danh sách F1 qua địa chỉ
    public List<User> FindAllF1ByAddress(String address){
        List<User> listf1 = FindAllF1();
        List<User> list = new ArrayList<>();
        for(User f1 : listf1){
            if(f1.getAddress().equals(address)){
                list.add(f1);
            }
        }
        return list;
    }
}
