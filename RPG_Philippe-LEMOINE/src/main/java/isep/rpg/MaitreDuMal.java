package isep.rpg;

public class MaitreDuMal extends Ennemy{
    public MaitreDuMal(String n) {
        super(n);
        name = n;
        healthPoint = 1500;
        damagePoints = 10;
        resS = 1;
        effet = 0;
        etat = effet / resP;
        degP = 2;
        resP = 2;
        classe = "Troll";
    }

    @Override
    public void fight(Combattant combattant) {

    }
}
