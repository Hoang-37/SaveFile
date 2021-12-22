package com.example.demo.service;

import com.example.demo.entity.DataContact;
import com.example.demo.entity.DataF0;
import com.example.demo.entity.Declaration;
import com.example.demo.entity.User;
import com.example.demo.repository.DataContactRepository;
import com.example.demo.repository.DataF0Repository;
import com.example.demo.repository.DeclarationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeclarationService {
    @Autowired
    private DeclarationRepository declarationRepository;
    @Autowired
    private DataF0Repository dataF0Repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataContactRepository dataContactRepository;

    // Tìm kiếm dec qua id nguoif dùng
    public Declaration FindByUserId(int user_id){
        return declarationRepository.findByUserId(user_id);
    }

    // Xoa File

    // So sánh với lịch trình F0
    public boolean CheckObjectUser(int user_id) throws SQLException {
        MoveFileTXT(user_id);
        List<String> dec = ReadFile(user_id);
        File file = new File("D://test"+user_id+".txt");
        file.delete();
        List<DataF0> listf0= dataF0Repository.findAll();
        for(DataF0 f0 : listf0){
           MoveFileTXT(f0.getUser_id());
           List<String> decf0 = ReadFile(f0.getUser_id());
            File files = new File("D://test"+f0.getUser_id()+".txt");
            files.delete();
           for(int i=0; i<2; i++){
               if(dec.get(i).equals(decf0.get(i))){
                   DataContact contact = new DataContact();
                   contact.setUser_one(f0.getUser_id());
                   contact.setUser_two(user_id);
                   dataContactRepository.save(contact);
                   return true;
               }
           }

        }
        return false;
    }
    // Lưu vào file txt
    public void WriterFile(String str, int user_id) {
        try {


            File file = new File("D://test" + user_id + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write( str+"\n" );
            bw.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    // Lưu vào CSDL
    public void Save(int user_id) {
        declarationRepository.saveFile("D://test" + user_id + ".txt", user_id);
        File file = new File("D://test"+user_id+".txt");
        file.delete();
    }
    //Chuyển Blob trong CSDL sang dạng txt
    public void MoveFileTXT(int user_id) throws SQLException {
        Blob dec = declarationRepository.findByUserId(user_id).getSchedule();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = dec.getBinaryStream();
            outputStream = new FileOutputStream("D://test"+user_id+".txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    public List<String> ReadFile(int user_id){
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("D://test"+user_id+".txt"))) {

            List<String> list = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();

            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
