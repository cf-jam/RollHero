package com.jam.RollHero.Controller;

import com.jam.RollHero.Repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SiteUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }
}
