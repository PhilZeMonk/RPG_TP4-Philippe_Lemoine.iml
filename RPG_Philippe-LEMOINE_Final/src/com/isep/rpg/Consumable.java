package com.isep.rpg;

public abstract class Consumable extends Item {
    public Consumable(String name) {
        super(name);
    }

    public int quantitee;
    public static int quantiteeM;
}
