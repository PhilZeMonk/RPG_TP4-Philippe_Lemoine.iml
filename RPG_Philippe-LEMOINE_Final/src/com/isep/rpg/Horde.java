package com.isep.rpg;

public abstract class Horde extends Ennemy{
    public Horde(String n, int niveau) {
        super(n);

        this.niveau = niveau;
    }
}
