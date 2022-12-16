package com.isep.rpg;

public abstract class Combattant {
    public Combattant(String n){
        name = n;
    }

    public abstract void fight(Combattant combattant);

    public String name;
}
