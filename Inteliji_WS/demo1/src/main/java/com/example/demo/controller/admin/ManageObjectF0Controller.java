package com.example.demo.controller.admin;

import com.example.demo.entity.Address;
import com.example.demo.entity.DataF0;
import com.example.demo.entity.DataF1;
import com.example.demo.entity.User;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManageObjectF0Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private DataF0Service dataF0Service;
    @Autowired
    private DataContactService dataContactService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DataF1Service dataF1Service;
    //Thêm F0
    @RequestMapping("/Admin/ManageObjectF0/ListAddF0")
    public String ListAddF0(Model md) {
        md.addAttribute("static", "add");
        md.addAttribute("listNoF0", userService.ListDifferentF0());
        return "ManageF0";
    }

    @Transactional
    @RequestMapping("/Admin/ManageObjectF0/AddF0/{id}")
    public String AddF0(@PathVariable("id") int id, Model md) {
        DataF0 F0 = new DataF0();
        F0.setUser_id(id);
        dataF0Service.Save(F0);
        String address = userService.FindByUserId(id).getAddress();
        addressService.UpdateNumber_F0(address);
        List<DataF1> listf1 = dataF1Service.FindAllF1ByIdF0(id);
        dataF1Service.SaveF1(listf1);
        addressService.UpdateNumber_f1(listf1);
        md.addAttribute("listNoF0", userService.ListDifferentF0());
        md.addAttribute("static", "add");
        return "ManageF0";
    }

    // Xem đối tượng f0
    @RequestMapping("/Admin/ManageObjectF0/ListF0")
    public String ListF0(Model md) {
        md.addAttribute("listf0", dataF0Service.getAllInforF0());
        md.addAttribute("static", "look");
        return "ManageF0";
    }

    // Truy vết đối tượng F1
    @RequestMapping("/Admin/ManageObjectF0/ListF1/{id}")
    public String ListF1(@PathVariable("id") int id, Model md) {

        md.addAttribute("listf1", dataContactService.FindALLF1ById(id));
        md.addAttribute("static", "f1");
        return "ManageF0";
    }

    // Xoa F0
    @RequestMapping("/Admin/ManageObjectF0/ListDeleteF0")
    public String ListDeleteF0(Model md) {
        md.addAttribute("listdeletef0", dataF0Service.getAllInforF0());
        md.addAttribute("static", "delete");
        return "ManageF0";
    }

    @Transactional
    @RequestMapping("/Admin/ManageObjectF0/DeleteF0/{id}")
    public String DeleteF0(@PathVariable("id") int id, Model md) {
        dataF0Service.DeleteF0(id);
        String address = userService.FindByUserId(id).getAddress();
        addressService.UpdateNumber_F0(address);
        List<DataF1> listf1 = dataF1Service.FindAllF1ByIdF0(id);
        dataF1Service.DeleteF1(listf1);
        addressService.UpdateNumber_f1(listf1);
        md.addAttribute("listdeletef0", dataF0Service.getAllInforF0());
        md.addAttribute("static", "delete");
        return "ManageF0";
    }

}
