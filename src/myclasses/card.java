package myclasses;

import java.util.ArrayList;

public class card {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //
//    public card(int mana, String description, ArrayList<String> colors,String name)
//    {
//        this.mana =  mana;
//        this.description = description;
//        this.colors= colors;
//        this.name = name;
//    }
    int checkrow(card a) {
        if (a.getName().equals("Goliath") || a.getName().equals("Warden") || a.getName().equals("Miraj") || a.getName().equals("The Ripper"))
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "card{" +
                "mana=" + mana +
                ", description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}
