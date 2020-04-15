package com.demo.community2.controller;

import com.demo.community2.dto.AccessTokenDTO;
import com.demo.community2.dto.GithubUserDTO;
import com.demo.community2.mapper.UserMapper;
import com.demo.community2.model.User;
import com.demo.community2.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name ="state") String state,
                           HttpServletRequest request//Spring框架自动装载request对象，可以通过他拿到session
                            ){

        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO github_user = githubProvider.getUser(accessToken);
        if(github_user !=null){
            //登录成功，写cookie和session
            User user = new User();

            user.setToken(UUID.randomUUID().toString());
            user.setAccount_id(String.valueOf(github_user.getId()));
            user.setName(github_user.getName());
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            request.getSession().setAttribute("user",github_user);
            userMapper.insert(user);


            return "redirect:";//把地址全部去掉，重新设为主页，根目录
        }
        else{
            //重新登录
            return "redirect:/";
        }

        //System.out.println(user.getName());


    }

}
