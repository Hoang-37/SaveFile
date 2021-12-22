package com.example.demo.controller.user;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeclarationController  {
    @Autowired
    private DeclarationService declarationService;
   @RequestMapping("/User/AddDeclaration")
   @Transactional
    public String AddDeclaration(HttpServletRequest request, Model md) throws SQLException {
       HttpSession session = request.getSession();
       String sang= request.getParameter("sang");
       String chieu= request.getParameter("chieu");
       User user= (User) session.getAttribute("user");
       declarationService.WriterFile(sang, user.getUser_id());
       declarationService.WriterFile(chieu, user.getUser_id());
       declarationService.Save(user.getUser_id());
       String str="0";
       if(declarationService.CheckObjectUser(user.getUser_id())){
          str= "f1";
      }
      md.addAttribute("str", str);
       return "index";
   }
   @RequestMapping("/User/XemRead")
    public String XemRead(HttpServletRequest request,Model md){
       HttpSession session = request.getSession();
       User user= (User) session.getAttribute("user");
       md.addAttribute("list", declarationService.ReadFile(user.getUser_id()));
       return "index";
   }
}
