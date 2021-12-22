package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/Admin")
    public String Login(@ModelAttribute("acc") Account acc, HttpServletRequest request, Model md){
        User user = accountService.FindUserByAccount(acc.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        String str="";
        Account admin = new Account("Admin", "123456");
        if(acc.getUsername().equals(admin.getUsername()) == true && acc.getPassword().equals(admin.getPassword()) == true ){
            return "HomeAdmin";
        }
        if(accountService.CheckAccount(acc,str) == false){
            md.addAttribute("str", str);
            return "Login";
        }


        md.addAttribute("acc",acc);
        return "HomeUser";
    }


}
