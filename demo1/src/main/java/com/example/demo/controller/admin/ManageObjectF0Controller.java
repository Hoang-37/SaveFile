package com.example.demo.controller.admin;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageObjectF0Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private DataContactService dataContactService;
    @Autowired
    private AddressService addressService;

    //Thêm F0
    @RequestMapping("/Admin/ManageObjectF0/ListAddF0")
    public String ListAddF0(Model md) {
        md.addAttribute("static", "add");
        md.addAttribute("listNoF0", userService.ListNoF0());
        return "ManageF0";
    }

    @Transactional
    @RequestMapping("/Admin/ManageObjectF0/AddF0/{id}")
    public String AddF0(@PathVariable("id") int id, Model md) {
        userService.AddF0(id);

        /*String address = userService.FindByUserId(id).getAddress();
        addressService.UpdateNumber_F0(address);
        List<DataF1> listf1 = dataF1Service.FindAllF1ByIdF0(id);
        dataF1Service.SaveF1(listf1);
        addressService.UpdateNumber_f1(listf1);*/
        md.addAttribute("listNoF0", userService.ListNoF0());
        md.addAttribute("static", "add");
        return "ManageF0";
    }

    // Xem đối tượng f0
    @RequestMapping("/Admin/ManageObjectF0/ListF0")
    public String ListF0(Model md) {
        md.addAttribute("listf0", userService.ListF0());
        md.addAttribute("static", "look");
        return "ManageF0";
    }

    // Truy vết đối tượng F1
    @RequestMapping("/Admin/ManageObjectF0/ListF1/{id}")
    public String ListF1(@PathVariable("id") int id, Model md) {

        md.addAttribute("listf1", userService.ListF1ByF0(id));
        md.addAttribute("static", "f1");
        return "ManageF0";
    }

    // Xoa F0
    @RequestMapping("/Admin/ManageObjectF0/ListDeleteF0")
    public String ListDeleteF0(Model md) {
        md.addAttribute("listdeletef0", userService.ListF0());
        md.addAttribute("static", "delete");
        return "ManageF0";
    }

    @Transactional
    @RequestMapping("/Admin/ManageObjectF0/DeleteF0/{id}")
    public String DeleteF0(@PathVariable("id") int id, Model md) {
        userService.DeleteF0(id);
        if(dataContactService.CheckF1(id)== false){
            userService.AddF1(id);
        }
      /*  dataF0Service.DeleteF0(id);
        String address = userService.FindByUserId(id).getAddress();
        addressService.UpdateNumber_F0(address);
        List<DataF1> listf1 = dataF1Service.FindAllF1ByIdF0(id);
        dataF1Service.DeleteF1(listf1);
        addressService.UpdateNumber_f1(listf1);*/
        md.addAttribute("listdeletef0", userService.ListF0());
        md.addAttribute("static", "delete");
        return "ManageF0";
    }

}
