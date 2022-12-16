package isep.rpg;

public abstract class Food extends Consumable{
    public Food(String name) {
        super(name);
    }

    public int getHealthPoints() {
        return mana;
    }
    public int mana;
}
