package com.jam.RollHero;

import com.jam.RollHero.Model.Hero;
import com.jam.RollHero.Util.Dice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RollHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollHeroApplication.class, args);
		System.out.println(Dice.rollDie(6));

	}
}
