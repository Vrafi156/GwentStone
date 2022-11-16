package main;

import java.util.ArrayList;

public class hero extends card{
    private int health;
    public hero(int mana, String description, ArrayList<String> colors,String name){
        this.setMana(mana);
        this.setDescription(description);
        this.setColors( colors);
        this.setName( name);

        this.health = 30;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int hp) {
        this.health = hp;
    }

    @Override

    public String toString() {
        return "hero{" +
                "mana=" + this.getMana() +
                ", description='" + this.getDescription() + '\'' +
                ", colors=" + this.getColors() +
                ", name='" +  this.getName() + ", health='" + health + '\'' +
                '}';
    }
    // de implementat eroi
}
