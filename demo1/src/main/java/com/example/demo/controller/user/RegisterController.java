package com.example.demo.controller.user;

import com.example.demo.entity.Account;
import com.example.demo.entity.Address;
import com.example.demo.entity.DataContact;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.AddressService;
import com.example.demo.service.DataContactService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class RegisterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private DataContactService dataContactService;
    @RequestMapping("/SaveRegister")
    @Transactional
    public String Save(@ModelAttribute("acc") Account acc , @ModelAttribute("user") User user, Model md){
        // Lưu tài khoản
        accountService.Save(acc);
        // Lưu thông tin người dùng
        userService.Save(user);
        // Random tiếp xúc
        User user1 = userService.FindUserByAccount(user.getUsername());
        dataContactService.SaveRandom(user1.getUser_id());
        // Lưu địa chỉ người dùng
        addressService.SaveAddress(user.getAddress());
        md.addAttribute("acc",new Account());
        return "Login";
    }



}
