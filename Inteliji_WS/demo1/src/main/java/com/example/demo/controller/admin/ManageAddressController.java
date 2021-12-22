package com.example.demo.controller.admin;

import com.example.demo.entity.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.DataContactService;
import com.example.demo.service.DataF0Service;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageAddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private DataF0Service dataF0Service;
    @Autowired
    private DataContactService dataContactService;
    // Xem địa chỉ tất cả người dùng
    @RequestMapping("/Admin/ManageAddress/AddressUser")
    public String AddressUser(Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        md.addAttribute("listaddress", addressService.FindAll());
        md.addAttribute("static","addressuser");
        return "ManageAddress";
    }

    @RequestMapping("/Admin/ManageAddress/ListUser/{address}")
    public String ListUser(@PathVariable String address, Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        md.addAttribute("list", userService.FindAllByAddress(address));
        md.addAttribute("static","list" );
        return "ManageAddress";
    }
    // Xem địa chỉ F0
    @RequestMapping("/Admin/ManageAddress/AddressF0")
    public String AddressF0(Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        md.addAttribute("static","addressf0");
        md.addAttribute("listaddressf0", addressService.FindAllAddressF0());
        return "ManageAddress";
    }

    @RequestMapping("/Admin/ManageAddress/ListF0/{address}")
    public String ListF0(@PathVariable String address, Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        md.addAttribute("list", userService.ListF0InAddress(address));
        md.addAttribute("static","list" );
        return "ManageAddress";
    }
    // Xem địa chỉ F1
    @RequestMapping("/Admin/ManageAddress/AddressF1")
    public String AddressF1(Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        md.addAttribute("listaddressf1",addressService.FindAllAddressF1());
        md.addAttribute("static", "addressf1");
        return "ManageAddress";
    }
    @RequestMapping("/Admin/ManageAddress/ListF1/{address}")
    public String ListObF1(@PathVariable String address,Model md){
        int number= addressService.FindAll().size();
        md.addAttribute("number_user", number);
        int number_f0 = dataF0Service.FindAll().size();
        md.addAttribute("number_f0", number_f0);
        int number_f1 = dataContactService.FindAllInforF1().size();
        md.addAttribute("number_f1", number_f1);
        List<User> listf1 = dataContactService.FindAllInforF1();
        List<User> list = new ArrayList<>();
        for(User f1 : listf1){
            if(f1.getAddress().equals(address)){
                list.add(f1);
            }
        }
        md.addAttribute("list", list);
        md.addAttribute("static","list" );
        return "ManageAddress";
    }
}
