package com.isep.rpg;

public class Dragon extends Horde{
    public Dragon(String n, int niveau) {
        super(n, niveau);
        name = n;
        healthPoint = 500 * niveau;
        damagePoints = niveau;
        resS = niveau;
        effet = 0;
        etat = effet/niveau;
        degP = 2 * niveau;
        resP = 2 * niveau;
        classe = "Dragon";
    }

    @Override
    public void fight(Combattant combattant) {

    }
}
