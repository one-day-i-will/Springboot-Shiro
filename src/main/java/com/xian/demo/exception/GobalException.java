package com.xian.demo.exception;


import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GobalException {


    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(AuthorizationException e){
        return "未认证权限";
    }
}
