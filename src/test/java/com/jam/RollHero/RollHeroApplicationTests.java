package com.jam.RollHero;
import com.jam.RollHero.Model.SiteUser;
import com.jam.RollHero.Util.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.jam.RollHero.Model.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;


@SpringBootTest
class RollHeroApplicationTests {
	@Test
	void testRollDice() {
		int[] sut = Dice.rollDice(2,6);
		//sut[1] is minimal
		assertTrue(sut[0]<13 && sut[0]>1 && sut[1]<sut[0] );
	}
	@Test
	void testSetMap(){
		Hero def = new Hero();
		HashMap test = def.makeStatMap();
		SiteUser testUser = new SiteUser();
//		Hero sut = new Hero("barbarian", "human", "dude", , test));
//		assertTrue(sut.getStatMap()==null);
	}
	@Test

	void testSetHeroRace() {
		Hero sut = new Hero();
		sut.dummyHeroStats();
		System.out.println("+++++++++++++++"+sut);
		assertTrue(sut.getHeroRace()!=null);
	}
}
