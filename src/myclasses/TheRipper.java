package myclasses;

import myclasses.Minion;

import java.util.ArrayList;

public class TheRipper extends Minion {
    public TheRipper(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {
        super(mana, attackDamage, health, description, colors, name);
    }

    public TheRipper(Minion a) {
        super(a);
    }
}
