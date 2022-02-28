package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.Declaration;
import com.example.demo.repository.DataContactRepository;
import com.example.demo.repository.DeclarationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

@Service
public class DeclarationService {
    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataContactRepository dataContactRepository;

    // Tìm kiếm dec qua id nguoif dùng
    public Declaration FindByUserId(int user_id){
        return declarationRepository.findByUserId(user_id);
    }






}
