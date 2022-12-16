package com.isep.rpg;

public class Soins extends Sort{
    public Soins(String name) {
        super(name);
        coutS = 2;
        healthPointS = 0; // Si c'est un mage ou un healer, hp + 50, sinon, hp+20
    }
}
