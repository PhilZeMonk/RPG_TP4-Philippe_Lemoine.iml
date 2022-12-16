package isep.rpg;

public abstract class Ennemy extends Combattant {

    public Ennemy(String n) {
        super(n);
        this.damagePoints = damagePoints;
        effet = 0;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public int damagePoints;
    public int healthPoint;
    public int degP;
    public int resP;
    public int resS;
    public int effet;
    public int etat;
    public int niveau;
    public String classe;
}