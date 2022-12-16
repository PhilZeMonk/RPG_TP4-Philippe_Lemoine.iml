package com.isep.rpg;

public abstract class Potion extends Consumable{
    public Potion(String name) {
        super(name);
    }

    public int recupVie;
    public int resistance;
    public int poison;
    public int effet;
}
