package com.example.productmanagement;

import com.example.productmanagement.entities.User;
import com.example.productmanagement.repositories.UserRepository;
import com.example.productmanagement.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProductManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            User user = new User("TranChiKien","20110507student.hcmute.edu.vn","tck221020021234",
                    "0908671124", "Ho Chi Minh City, Viet Nam",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            userRepository.save(user);
        }
    }


}
