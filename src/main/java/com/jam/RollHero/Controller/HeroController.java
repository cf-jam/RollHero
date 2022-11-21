package com.jam.RollHero.Controller;

import com.jam.RollHero.Model.Hero;
import com.jam.RollHero.Model.SiteUser;
import com.jam.RollHero.Repository.HeroRepository;
import com.jam.RollHero.Repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HeroController {

    @Autowired
    HeroRepository heroRepository;
    @Autowired
    SiteUserRepository siteUserRepository;

    @GetMapping("/heroform")
    public String getHeroForm() {
        return "HeroForm.html";
    }

    @PostMapping("/heroform")
    public RedirectView postHeroForm(Principal p, Model m, String name, String heroRace, String heroClass) {
        m.addAttribute(name);
        m.addAttribute(heroRace);
        m.addAttribute(heroClass);
        SiteUser siteUser = siteUserRepository.findByUsername(p.getName());
        Hero newHero = new Hero(heroRace, heroClass, name, siteUser);
        m.addAttribute(siteUser);
        heroRepository.save(newHero);
        return new RedirectView("/");
    }
    @GetMapping("user/heroes/{id}")
    public String getUserHeroes(Principal p,Model m,@PathVariable Long id){
        SiteUser siteUser = siteUserRepository.findById(id).orElseThrow();
        m.addAttribute("heroList",heroRepository.findAllBySiteUserId(id));
        return "displayHeroes";
    }

    @GetMapping("/card")
    public String getHeroCard(Model m){
            List<Hero> itemList = heroRepository.findAll();
            m.addAttribute("itemList",itemList);
        return "/fragments/card";
    }
}
