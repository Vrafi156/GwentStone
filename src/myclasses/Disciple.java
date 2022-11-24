package myclasses;

import java.util.ArrayList;

public class Disciple extends Minion {
    public Disciple(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {
        super(mana, attackDamage, health, description, colors, name);
    }

    public Disciple(Minion a) {
        super(a);
    }
}
