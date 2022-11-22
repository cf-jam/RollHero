package com.jam.RollHero.Model;

import com.jam.RollHero.Util.Dice;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    SiteUser siteUser;
    HashMap<String, Integer> statMap = new HashMap<>();

    public HashMap<String, Integer> getStatMap() {
        return statMap;
    }

    private String name;
    private String heroRace = null;
    private String heroClass = null;

    private Integer hpMaximum = 0;

    private Integer speed = 0;

    private Integer heroLevel = 1;

    private Integer armorClass = 10;

    public Hero() {
    }

    public Hero(String race, String heroClass, String name, SiteUser siteUser, HashMap inputHero) {
        this.name = name;
        this.makeStatMap();
        this.setHeroRace(race);
        this.setHeroClass(heroClass);
        this.siteUser = siteUser;
        this.statMap = inputHero;
        this.setArmorClass(this.getArmorClass() + findModifier(this.getStatMap().get("dex")));
    }

    // TEST
    public void dummyHeroStats(String str, String dex, String intel, String con, String wis, String cha) {
        statMap.put("str", Dice.rollStat() + statMap.get("str"));
        statMap.put("dex", Dice.rollStat() + statMap.get("dex"));
        statMap.put("intel", Dice.rollStat() + statMap.get("intel"));
        statMap.put("con", Dice.rollStat() + statMap.get("con"));
        statMap.put("wis", Dice.rollStat() + statMap.get("wis"));
        statMap.put("cha", Dice.rollStat() + statMap.get("cha"));
    }

    //TEST
    public HashMap<String, Integer> makeStatMap() {
        statMap.put("str", 0);
        statMap.put("dex", 0);
        statMap.put("intel", 0);
        statMap.put("con", 0);
        statMap.put("wis", 0);
        statMap.put("cha", 0);
        return statMap;
    }

    public Long getId() {
        return id;
    }

    public void setStatMap(HashMap<String, Integer> statMap) {
        this.statMap = statMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroRace() {
        return heroRace;
    }

    public String describeHero() {
        String about = this.getName() + " is a level " + this.getHeroLevel() + " " + this.getHeroRace() + " " + this.getHeroClass() + ".";
        return about;
    }

    public HashMap<String, Integer> setHeroRace(String heroRace) {
        switch (heroRace) {
            case "dwarf":
                statMap.put("con", 2);
                this.setSpeed(25);
                break;
            case "elf":
                statMap.put("intel", 1);
                this.setSpeed(30);
                break;
            case "halfling":
                statMap.put("dex", 2);
                this.setSpeed(25);
                break;
            case "human":
                statMap.put("str", 1);
                statMap.put("dex", 1);
                statMap.put("intel", 1);
                statMap.put("con", 1);
                statMap.put("wis", 1);
                statMap.put("cha", 1);
                this.setSpeed(30);
                break;
            case "half-orc":
                statMap.put("str", 2);
                statMap.put("con", 1);
                this.setSpeed(30);
                break;
            case "half-elf":
                statMap.put("cha", 2);
                statMap.put("intel", 1);
                this.setSpeed(30);
                break;
            case "dragonborne":
                statMap.put("str", 2);
                statMap.put("cha", 1);
                this.setSpeed(30);
            case "tiefling":
                statMap.put("intel", 1);
                statMap.put("cha", 2);
                this.setSpeed(30);
            case "gnome":
                statMap.put("intel", 2);
                this.setSpeed(25);
                break;
            default:
                return null;
        }
        this.heroRace = heroRace;
        return statMap;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
        Integer newMaxHp = getHpMaximum();
        Integer hitDie = 0;
        switch (heroClass) {
            case "wizard":
            case "sorcerer":
                // hit die changes according to class
                hitDie = 6;
                if (newMaxHp.equals(0)) {
                    newMaxHp += hitDie;
                } else {
                    newMaxHp += Dice.rollDie(hitDie);
                }
//                add below line once Stat objects are implemented!!!!
//                newMaxHp += statMap.get("con").getModifierScore;
                setHpMaximum(newMaxHp);
                break;
            case "bard":
            case "cleric":
            case "druid":
            case "monk":
            case "rogue":
            case "warlock":
                // hit die changes according to class
                hitDie = 8;
                if (newMaxHp.equals(0)) {
                    newMaxHp += hitDie;
                } else {
                    newMaxHp += Dice.rollDie(hitDie);
                }
//                add below line once Stat objects are implemented!!!!
//                newMaxHp += statMap.get("con").getModifierScore;
                setHpMaximum(newMaxHp);
                break;
            case "fighter":
            case "paladin":
            case "ranger":
                // hit die changes according to class
                hitDie = 10;
                if (newMaxHp.equals(0)) {
                    newMaxHp += hitDie;
                } else {
                    newMaxHp += Dice.rollDie(hitDie);
                }
//                add below line once Stat objects are implemented!!!!
//                newMaxHp += statMap.get("con").getModifierScore;
                setHpMaximum(newMaxHp);
                break;
            case "barbarian":
                // hit die changes according to class
                hitDie = 12;
                if (newMaxHp.equals(0)) {
                    newMaxHp += hitDie;
                } else {
                    newMaxHp += Dice.rollDie(hitDie);
                }
//                add below line once Stat objects are implemented!!!!
//                newMaxHp += statMap.get("con").getModifierScore;
                setHpMaximum(newMaxHp);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Integer getHpMaximum() {
        return hpMaximum;
    }

    public void setHpMaximum(Integer hpMaximum) {
        this.hpMaximum = hpMaximum;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(Integer heroLevel) {
        this.heroLevel = heroLevel;
    }

    public Integer getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
    }

    public Integer findModifier(Integer statScore){
        Integer scoreMod = (int)Math.floor(statScore/2) - 5;
        return scoreMod;
    }
    public String stringifyModifier(Integer statScore){
        String retString ="";
        Integer scoreMod = (int)Math.floor(statScore/2) - 5;
        if(scoreMod >= 0){
            retString += "+";
        }
        retString += scoreMod.toString();
        return retString;
    }
}
