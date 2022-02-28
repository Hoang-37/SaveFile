package com.example.demo.controller.user;

import com.example.demo.entity.Account;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AddressService addressService;
    @RequestMapping("/User/Declaration")
    public String Declaration(Model md){


        return "Declaration";
    }
    @RequestMapping("/User/HistoryDeclaration")
    public String HistoryDeclaration(Model md, HttpSession session){
        Account acc = (Account) session.getAttribute("acc");


        return "HistoryDeclaration";
    }
    @RequestMapping("/User/ListUserAddress")
    public String ListUserAddress(Model md){
        List<Address> list = addressService.FindAll();
        md.addAttribute("listaddress", list);
        return "ListUserAddress";
    }
    @RequestMapping("/User/ListUser")
    public String ListUser(Model md){

        List<User> list = userService.FindAll();
        md.addAttribute("listuser", list);

        return "ListUser";
    }



}
