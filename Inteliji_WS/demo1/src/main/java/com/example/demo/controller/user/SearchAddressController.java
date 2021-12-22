package com.example.demo.controller.user;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchAddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @RequestMapping("/User/SearchAddress")
    public String SearchAddress(@RequestParam(name="SearchAddress") String Search, Model md){
        md.addAttribute("listaddress", addressService.FindByAddress(Search));
         return "ListUserAddress";
    }

    @RequestMapping("/User/DetailsAddress/{id}")
    public String DetailsAddress(@PathVariable int id, Model md){
        md.addAttribute("address", addressService.FindById(id));
        return "DetailsAddress";
    }

    @RequestMapping("/User/Number_User/{address}")
    public String Number_User( @PathVariable String address,Model md){
        Address addr=  addressService.FindByAddress(address);
        List<User> list =userService.FindAllByAddress(address);
        md.addAttribute("address",addr);
        md.addAttribute("listuser", list);
        return "DetailsAddress";
    }
}
