package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeScreenController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public  String HomePage(){
        return "HomeScreen";
    }
    @RequestMapping("/Register")
    public String Register(Model md) {
        md.addAttribute("acc",new Account());
        md.addAttribute("user", new User());
        return "Register";
    }
    @RequestMapping("/Login")
    public String Login(Model md) {
        md.addAttribute("acc",new Account());

        return "Login";
    }








}
