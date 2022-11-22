package com.jam.RollHero.Controller;

import com.jam.RollHero.Model.SiteUser;
import com.jam.RollHero.Repository.SiteUserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
public class SiteUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    HttpServletRequest request;

//    ROUTES

    @GetMapping("/")
    public String getHomePage(){
        return "index.html";
    }

    @GetMapping("/signup")
    public String getSignup(){return "signup.html";}

    @PostMapping("/signup")
    public String postSignup(String username, String password){
        String hashedPw = passwordEncoder.encode(password);
        SiteUser newUser = new SiteUser(username, hashedPw);
        siteUserRepository.save(newUser);
        authWithHttpServletRequest(username, password);
        return "secretTEST.html";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login.html";
    }
    @GetMapping("/dashboard")
    public String getDashboard(Principal p, Model m){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        m.addAttribute("siteUser", siteUser);
        return "dashboard.html";
    }
    @GetMapping("/secret") // For testing non-logged in user restriction
    public String getSecret(){return "secretTEST.html";}

//    UTILS

    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
