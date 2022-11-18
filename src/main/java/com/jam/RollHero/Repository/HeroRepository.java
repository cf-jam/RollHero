package com.jam.RollHero.Repository;

import com.jam.RollHero.Model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
