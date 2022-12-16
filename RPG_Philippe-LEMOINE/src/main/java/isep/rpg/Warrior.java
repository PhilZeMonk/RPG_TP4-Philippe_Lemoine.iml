package isep.rpg;

public class Warrior extends Hero{

    public Warrior(String n) {
        super(n);
        healthPoint = 100;
        healthPointMax = 100;
        mana = 4;
        manaMax = 4;
        resS = 1;
        cout = 1;
        recupM = 0;
        recupV = 0;
        effetS = 1;
        effetP = 0;
        degP = 4;
        resP = 2;
        arrow = 0;
        arrowMax = 0;
        classe = "Warrior";
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
