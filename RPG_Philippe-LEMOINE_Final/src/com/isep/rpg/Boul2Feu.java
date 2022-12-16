package com.isep.rpg;

public class Boul2Feu extends Sort{
    public Boul2Feu(String name) {
        super(name);
        coutS = 2;
        degat = 20;
        effetS = 0; //Si mage, effet += 15, sinon effet += 5
    }
}
