package com.example.demo.controller.admin;

import com.example.demo.entity.User;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.service.DataContactService;
import com.example.demo.service.DataF0Service;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManageUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DataF0Service dataF0Service;
    @Autowired
    private DataContactService dataContactService;

    @RequestMapping("/Admin/ManageUser/ListUser")
    public String ListUser(Model md){
        int number_user = userService.FindAll().size();
        md.addAttribute("user", number_user);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("f0", number_f0);
        int number_f1= dataContactService.FindAllInforF1().size();
        md.addAttribute("f1",number_f1);
        List<User> listuser = userService.FindAll();
        md.addAttribute("list", listuser);
        return "ManageUser";
    }
    @RequestMapping("/Admin/ManageUser/ListF0")
    public String ListF0(Model md){
        int number_user = userService.FindAll().size();
        md.addAttribute("user", number_user);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("f0", number_f0);
        int number_f1= dataContactService.FindAllInforF1().size();
        md.addAttribute("f1",number_f1);
        List<User> listf0 = dataF0Service.getAllInforF0();
        md.addAttribute("list", listf0);
        return "ManageUser";
    }
    @RequestMapping("/Admin/ManageUser/ListF1")
    public String ListF1(Model md){
        int number_user = userService.FindAll().size();
        md.addAttribute("user", number_user);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("f0", number_f0);
        int number_f1= dataContactService.FindAllInforF1().size();
        md.addAttribute("f1",number_f1);
        List<User> listf1 = dataContactService.FindAllInforF1();
        md.addAttribute("list", listf1);
        return "ManageUser";
    }
}
