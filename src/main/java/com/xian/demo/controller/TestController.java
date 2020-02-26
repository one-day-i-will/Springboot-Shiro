package com.xian.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class TestController {

    @RequestMapping("/user")
    public String user(){
        return "user";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequiresRoles(value = {"superUser"})
    @RequestMapping("/success")
    public String success(){
        return "success";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/unAuthor")
    public String unAuthor(){
        return "unAuthor";
    }

    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam("username") String username,@RequestParam("password") String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("账号错误");
        }
        catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "redirect:/shiro/login";
    }
}
