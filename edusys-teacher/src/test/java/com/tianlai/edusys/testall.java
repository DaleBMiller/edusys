package com.tianlai.edusys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class testall {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test01() {
        String pass = "123456";
        String encode = passwordEncoder.encode(pass);
        System.out.println(encode);

    }
}
