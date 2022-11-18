package com.jam.RollHero.Util;

public class Stat {

    private String name;
    private Integer statScore;
    private Integer modifierScore;

    public Stat(String name, Integer statScore, Integer modifierScore) {
        this.name = name;
        this.statScore = statScore;
        this.modifierScore = modifierScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatScore() {
        return statScore;
    }

    public void setStatScore(Integer statScore) {
        this.statScore = statScore;
        determineAndSetModifierScore();
    }

    public Integer getModifierScore() {
        return modifierScore;
    }

    public void setModifierScore(Integer modifierScore) {
        this.modifierScore = modifierScore;
    }

    public void determineAndSetModifierScore(){
        // alg for stat modifier
        Integer scoreMod = (int)Math.floor(statScore/2) - 5;
        setModifierScore(scoreMod);
    }
}
