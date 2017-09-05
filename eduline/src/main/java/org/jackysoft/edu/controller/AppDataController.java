package org.jackysoft.edu.controller;

import com.google.common.base.Strings;
import org.jackysoft.edu.message.Message;
import org.jackysoft.edu.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppDataController {

    static final Map<String, String> tokenSession = new HashMap<>();

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected SysUserService userService;
    @PostMapping("/rest/login")

    public Message login(
            @RequestParam("username")String username,
            @RequestParam("password")String password){
        if(Strings.isNullOrEmpty(username)||Strings.isNullOrEmpty(password)){
            return new Message(1,null);
        }
        userService.findById(username);


    }


    boolean isTokenValid(String token) {
        if(Strings.isNullOrEmpty(token)) return false;
        String userid = tokenSession.get(token);
        return !Strings.isNullOrEmpty(userid);
    }

}
