package myclasses;

import java.util.ArrayList;

public class Miraj extends Minion {
    public Miraj(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {
        super(mana, attackDamage, health, description, colors, name);
    }
    void talent(){

    }
    public Miraj(Minion a) {
        super(a);
    }
}
