package myclasses;

import java.util.ArrayList;

public class Environment extends card {

    public Environment(int mana, String description, ArrayList<String> colors, String name) {
        this.setMana(mana);
        this.setDescription(description);
        this.setColors(colors);
        this.setName(name);
    }

    public Environment(Environment a) {
        this.setMana(a.getMana());
        this.setDescription(new String(a.getDescription()));
        this.setColors(new ArrayList<String>(a.getColors()));
        this.setName(new String(a.getName()));
    }




    //de implementat spelluri
    public String toString() {
        return "CardInput{" + "mana=" + getMana() + ", description='" + getDescription() + '\'' + ", colors=" + getColors() + ", name='" + "" + getName() + '\'' + '}';
    }

}
