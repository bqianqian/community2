package com.demo.community2.controller;

import com.demo.community2.mapper.UserMapper;
import com.demo.community2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie :cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user=userMapper.findByToken(token); //在数据库中找已经登录过的用户的token
                if(user !=null){
                    request.getSession().setAttribute("user",user);//将session设置为user
                }
                break;
            }
        }



        return "/index";
    }
}
