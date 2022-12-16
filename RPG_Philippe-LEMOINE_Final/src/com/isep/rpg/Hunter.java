package com.isep.rpg;

import java.util.Random;

public class Hunter extends Hero{
    public Hunter(String n) {
        super(n);
        healthPoint = 90;
        healthPointMax = 90;
        mana = 6;
        manaMax = 6;
        resS = 1;
        cout = 1;
        recupM = 0;
        recupV = 0;
        resP = 1;
        effetS = 1;
        effetP = 0;
        arrowMax = 15;
        arrow(arrow);
        classe = "Hunter";
    }

    public void arrow(int arrow){
        if (arrow > 0){
            Random rand = new Random();
            int critique = rand.nextInt(1);
            if (critique == 1){
                degP = 4;
            }
            else{
                degP = 3;
            }
        }
        else{
            degP = 1;
        }
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
