package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public void Save(Account acc) {
        accountRepository.save(acc);
    }

    public boolean CheckAccount(Account acc, String str){
        if(accountRepository.findByUsername(acc.getUsername())==null){
             str ="Tài khoản không tồn tại";
             return false;
        }
        else if(accountRepository.findByUsernameAndPassword(acc.getUsername(), acc.getPassword())== null){
            str ="Mật khẩu sai";
            return false;
        }
        else{
            return true;
        }
    }
    //Tìm thông tin người dùng qua username
    public User FindUserByAccount(String username){
        return userRepository.findUserByAccount(username);
    }

}
