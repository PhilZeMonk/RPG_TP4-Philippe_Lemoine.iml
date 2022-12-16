package isep.rpg;

public class Gnome extends Horde{
    public Gnome(String n, int niveau) {
        super(n, niveau);
        name = n;
        healthPoint = 50 * niveau;
        damagePoints = niveau;
        resS = niveau;
        effet = 0;
        etat = effet/niveau;
        degP = 2 * niveau;
        resP = 2 * niveau;
        classe = "Gnome";
    }

    @Override
    public void fight(Combattant combattant) {

    }
}
