package com.isep.rpg;

public abstract class Hero extends Combattant{
    public Hero (String n){
        super(n);
    }

    public int getMana(){return mana;}
    public abstract void take(Item item);
    public int healthPoint;
    public int mana;
    public int degP;
    public int resP;
    public int resS;
    public int cout;
    public int recupM;
    public int recupV;
    public int effetS; // effet sort
    public int effetP; // effet potion
    public String classe;
    public Weapon weapon;
    public static int healthPointMax;
    public int arrow = 15;

    public int arrowMax;
    public int manaMax;
}
