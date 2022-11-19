package com.jam.RollHero.Repository;

import com.jam.RollHero.Model.Hero;
import com.jam.RollHero.Model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    public List<Hero> findAllBySiteUserId(Long id);
}
