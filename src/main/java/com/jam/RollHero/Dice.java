package com.jam.RollHero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Dice {
    public static int rollStat() {
        int[] res = rollDice(4, 6);
//        System.out.println("total " + res[0] + " minus min" + res[1]);
        return res[0] - res[1];
    }

    public static int[] rollDice(int number, int nSides) {
        int num = 0;
        int roll = 0;
        int min = 401;
        int[] res = {0, 0};
        Random r = new Random();
        if (nSides >= 3) {
            for (int i = 0; i < number; i++) {
                roll = r.nextInt(nSides) + 1;
                if (roll < min) min = roll;
//                System.out.println("Roll is:  " + roll);
                num = num + roll;
            }
        } else {
            System.out.println("Error num needs to be from 3");
        }
        res[0] = num;
        res[1] = min;
        return res;
    }

    public static Integer rollDie(Integer nSides){
        Random ran = new Random();
        Integer num = ran.nextInt(nSides) + 1;
        return num;
    }

//    public static void main(String[] args) {
//        System.out.println("Total is: " + rollDice(3, 6));
//    }


}