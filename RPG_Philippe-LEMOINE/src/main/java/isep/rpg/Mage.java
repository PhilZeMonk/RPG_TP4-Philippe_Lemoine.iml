package isep.rpg;

public class Mage extends SpellCaster{
    public Mage(String n) {
        super(n);
        healthPoint = 80;
        healthPointMax = 80;
        mana = 20;
        manaMax = 20;
        recupV = 0;
        degP = 1;
        resP = 1;
        cout = 2;
        recupM = 1;
        resS = 2;
        effetS = 0;
        effetP = 0;
        arrow = 0;
        arrowMax = 0;
        classe = "Mage";
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
