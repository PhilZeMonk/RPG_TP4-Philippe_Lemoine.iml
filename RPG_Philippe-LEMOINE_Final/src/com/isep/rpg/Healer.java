package com.isep.rpg;

public class Healer extends SpellCaster {
    public Healer(String n) {
        super(n);
        healthPoint = 70;
        healthPointMax = 70;
        mana = 20;
        manaMax = 20;
        cout = 1;
        recupM = 0;
        degP = 1;
        resP = 2;
        resS = 2;
        recupV = 7;
        effetS = 0;
        effetP = 0;
        arrow = 0;
        arrowMax = 0;
        classe = "Healer";
    }

    @Override
    public void fight(Combattant combattant) {

    }

    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        }
        else {
            Game.displayMessage("Oups ! " + item.getName() + " est inutile...");
        }
    }
}
