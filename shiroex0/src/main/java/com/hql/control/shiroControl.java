package com.hql.control;

import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class shiroControl {
    private static final Logger log = LoggerFactory.getLogger(shiroControl.class);


    @PostMapping("/login")

    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        System.out.println("login");
        System.out.println(username);
        Subject subject=SecurityUtils.getSubject();
        log.info("before judge");
        if(!subject.isAuthenticated()){
            log.info("not Authenticated");
            AuthenticationToken token=new UsernamePasswordToken(username,password);
            try{
                subject.login(token);

                subject.checkRole("root");
            }catch (AuthenticationException e){
                System.out.println("login fail");
                log.error(e.getMessage());
                return "/login.jsp";
            }
        }
        return "/success.jsp";
    }

//    @RequestMapping("/logout")
//    public String logout(){
//        log.info("logout");
//        Subject subject=SecurityUtils.getSubject();
//        subject.logout();
//
//        return "login.jsp";
//    }
}
