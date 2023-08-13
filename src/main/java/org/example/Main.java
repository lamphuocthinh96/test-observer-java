package org.example;

import org.example.game.Battle;
import org.example.observable.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------- BEGIN ---------");
        Battle battle = new Battle();
        battle.start();
        System.out.println("--------- END ---------");

    }
}