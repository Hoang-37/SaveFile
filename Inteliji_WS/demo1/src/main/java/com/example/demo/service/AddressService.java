package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DataContactRepository;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataF0Repository dataF0Repository;
    @Autowired
    private DataContactRepository dataContactRepository;
    @Autowired
    private DataF1Service dataF1Service;

    //Lấy thông tin tất cả địa chỉ
    public List<Address> FindAll() {
        return addressRepository.findAll();
    }

    //Tìm kiếm địa chỉ
    public List<Address> FindAllLikeAddress(String Address) {
        return addressRepository.findAllLikeAddress(Address);
    }

    public Address FindByAddress(String Address) {
        return addressRepository.findByAddress(Address);
    }


    //Lưu thông tin địa chỉ
    public void Save(String address) {

        if (addressRepository.findByAddress(address) == null) {
            Address addr = new Address();
            addr.setAddress(address);
            addr.setNumber_user(1);
            addressRepository.save(addr);
        }else{
            UpdateNumber_User(address);
        }
    }
    //Update lại Number_User
    public void UpdateNumber_User(String address){
        List<User> listuser = userRepository.findAllByAddress(address);

        addressRepository.updateNumber(listuser.size(), address);
    }
    //ưu Number_F0 qua Dia chi
    public void UpdateNumber_F0(String address){
        List<DataF0> list = dataF0Repository.findAllByAddress(address) ;

         addressRepository.updateNumber_f0(list.size(), address);
    }

    //Delete Number_f1 khi xóa f0

    public void AddNumber_f1(List<User> listf1){

    }

    public void UpdateNumber_f1(List<DataF1> listf1){
        if(listf1 != null){
            for(DataF1 f1 :listf1){
                String address = userRepository.findByUserId(f1.getUser_id()).getAddress();
                addressRepository.updateNumber_f1(dataF1Service.FindAllF1ByAddress(address).size(), address);
            }
        }
    }
    //Tim theo id
    public Address FindById(int id) {
        return addressRepository.findById(id);
    }

    // Tìm tấ cả địa chỉ F0
    public List<Address> FindAllAddressF0(){
       return addressRepository.findAllAddressF0();
    }
    // Tìm tấ cả địa chỉ F1
    public List<Address> FindAllAddressF1(){
        return addressRepository.findAllAddressF1();
    }
}
