package com.example.demo.controller.user;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchUserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/User/SearchUser")
    public String SearchUser(@RequestParam(name="SearchUser") String Search, Model md){

        md.addAttribute("listuser", userService.FindAllLikeName(Search));
        return "ListUser";
    }
    @RequestMapping("/User/DetailsUser/{id}")
    public String DetailsUser(@PathVariable int id, Model md){
        md.addAttribute("user",userService.FindByUserId(id));
        return "DetailsUser";
    }


}
