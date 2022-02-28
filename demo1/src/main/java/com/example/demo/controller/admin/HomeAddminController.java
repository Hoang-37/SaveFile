package com.example.demo.controller.admin;

import com.example.demo.service.AddressService;
import com.example.demo.service.DataContactService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAddminController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private DataContactService dataContactService;
    @Autowired
    private UserService userService;

    //Quản lý địa chi
    @RequestMapping("/Admin/ManageAddress")
    public String ManageAddress(Model md) {
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = userService.ListF0().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = userService.ListF1().size();
        md.addAttribute("number_f1", number_f1);
        return "ManageAddress";
    }

    //Quản lý F0
    @RequestMapping("/Admin/ManageF0")
    public String ManageF0(Model md) {

        return "ManageF0";
    }

    //Quản lý người dùng
    @RequestMapping("/Admin/ManageUser")
    public String ManageUser(Model md) {
        int number_user = userService.FindAll().size();
        md.addAttribute("user", number_user);
        int number_f0 = userService.ListF0().size();
        md.addAttribute("f0", number_f0);
        int number_f1= userService.ListF1().size();
        md.addAttribute("f1",number_f1);
        return "ManageUser";
    }
}
