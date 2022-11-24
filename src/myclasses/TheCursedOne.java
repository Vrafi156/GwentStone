package myclasses;

import myclasses.Minion;

import java.util.ArrayList;

public class TheCursedOne extends Minion {
    public TheCursedOne(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {
        super(mana, attackDamage, health, description, colors, name);
    }

    public TheCursedOne(Minion a) {
        super(a);
    }
}
