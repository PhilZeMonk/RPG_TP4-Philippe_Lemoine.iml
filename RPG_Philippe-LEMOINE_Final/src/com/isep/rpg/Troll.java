package com.isep.rpg;

public class Troll extends Horde{
    public Troll(String n, int niveau) {
        super(n, niveau);
        name = n;
        healthPoint = 200 * niveau;
        damagePoints = niveau;
        resS = niveau;
        effet = 0;
        etat = effet / niveau;
        degP = 2 * niveau;
        resP = 2 * niveau;
        classe = "Troll";
    }

    @Override
    public void fight(Combattant combattant) {

    }
}
