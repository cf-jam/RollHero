package com.jam.RollHero.Controller;

import com.jam.RollHero.Model.SiteUser;
import com.jam.RollHero.Repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class SiteErrorController implements ErrorController {
    @Autowired
    SiteUserRepository siteUserRepository;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model m, Principal p){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        Object message = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
        m.addAttribute("status", status);
//        m.addAttribute("message", message);
        return "error";
    }
}
