package com.jam.RollHero.Controller;

import com.jam.RollHero.Model.Hero;
import com.jam.RollHero.Model.SiteUser;
import com.jam.RollHero.Repository.HeroRepository;
import com.jam.RollHero.Repository.SiteUserRepository;
import com.jam.RollHero.Util.Dice;
import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import java.util.HashMap;

@Controller
public class HeroController {

    @Autowired
    HeroRepository heroRepository;
    @Autowired
    SiteUserRepository siteUserRepository;

    @GetMapping("/heroform")
    public String getHeroForm(Model m) {
        int[] statArray = new int[6];
        for(int i = 0; i < 6; i++ ){
            statArray[i] = Dice.rollStat();
        }
        m.addAttribute("statArray", statArray);
        return "HeroForm.html";
    }

    @PostMapping("/heroform")
    public RedirectView postHeroForm(Principal p, Model m, String name, String heroRace, String heroClass, String inputHero5, String inputHero4, String inputHero3, String inputHero2, String inputHero1, String inputHero0){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        HashMap<String, Integer> statsHero = new HashMap<>();
        statsHero.put(inputHero0.split(",")[0], Integer.parseInt(inputHero0.split(",")[1]));
        statsHero.put(inputHero1.split(",")[0], Integer.parseInt(inputHero1.split(",")[1]));
        statsHero.put(inputHero2.split(",")[0], Integer.parseInt(inputHero2.split(",")[1]));
        statsHero.put(inputHero3.split(",")[0], Integer.parseInt(inputHero3.split(",")[1]));
        statsHero.put(inputHero4.split(",")[0], Integer.parseInt(inputHero4.split(",")[1]));
        statsHero.put(inputHero5.split(",")[0], Integer.parseInt(inputHero5.split(",")[1]));
        Hero newHero = new Hero(heroRace, heroClass, name, siteUser, statsHero);
        m.addAttribute(siteUser);
        heroRepository.save(newHero);
        return new RedirectView("/dashboard");
    }

    @GetMapping("user/cards/{id}")
    public String getUserHeroCards(Principal p, Model m, @PathVariable Long id) throws NoSuchElementException {
        try{
            SiteUser siteUser = siteUserRepository.findById(id).orElseThrow();
            SiteUser viewUser = siteUserRepository.findByUsername(p.getName());
//            List<Hero> itemList = heroRepository.findAll();
            m.addAttribute("siteUser", siteUser);
            m.addAttribute("viewUser", viewUser);
            m.addAttribute("itemList", heroRepository.findAllBySiteUserId(id));
            return "/dashboard";
        }
        catch (Exception e){
            System.out.println("something wrong");
        }
        return null;
    }

    @PostMapping("/deleteHero/{id}")
    public RedirectView deleteHero(Principal p, @PathVariable Long id){
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        heroRepository.deleteById(id);
        return new RedirectView("/user/cards/" + siteUser.getId());
    }
}
