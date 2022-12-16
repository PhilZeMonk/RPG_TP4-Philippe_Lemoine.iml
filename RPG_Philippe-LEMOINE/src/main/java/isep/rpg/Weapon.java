package isep.rpg;

public class Weapon extends Item {

    public Weapon(String name) {
        super(name);
        switch (name){
            case "Épée":
                damagePoints = 5;
                break;
            case "Arc":
                damagePoints = 4;
                break;
            case "Bâton de sorcier":
                damagePoints = 10;
                break;
            case "Baguette":
                damagePoints = 10;
                break;
        }
    }

        public int getDamagePoints() {
        return damagePoints;
    }

    private int damagePoints;
    private String name;
}

