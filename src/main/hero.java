package main;

import java.util.ArrayList;

public class hero extends card{
    private int health;
    int attacked;
    public hero(int mana, String description, ArrayList<String> colors,String name){
        this.setMana(mana);
        this.setDescription(description);
        this.setColors( colors);
        this.setName( name);

        this.health = 30;
        attacked = 0;

    }
    public hero(hero a){
        this.setMana(a.getMana());
        this.setDescription(a.getDescription());
        this.setColors( a.getColors());
        this.setName( a.getName());
        this.setHealth(a.getHealth());
        this.attacked = a.attacked;

    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int hp) {
        this.health = hp;
    }



    void SubZero(Game joc, int row){
        Minion ref;
        int maxAttack = joc.getMasa().get(row).get(0).getAttackDamage();
        ref = joc.getMasa().get(row).get(0);
        for(int i = 1; i < joc.getMasa().get(row).size();i++) {
            if(joc.getMasa().get(row).get(i).getAttackDamage() > maxAttack) {
                maxAttack = joc.getMasa().get(row).get(i).getAttackDamage();
                ref = joc.getMasa().get(row).get(i);
            }
        }
        ref.frozen = 1;
    }
    void LowBlow(Game joc, int row) {
        Minion ref;
        int maxhp = joc.getMasa().get(row).get(0).getHealth();
        int j = 0 ;
        ref = joc.getMasa().get(row).get(0);
        for(int i = 1; i < joc.getMasa().get(row).size();i++) {
            if(joc.getMasa().get(row).get(i).getHealth() > maxhp) {
                maxhp = joc.getMasa().get(row).get(i).getHealth();
                ref = joc.getMasa().get(row).get(i);
                j = i;
            }
        }
        joc.getMasa().get(row).remove(j);
    }

    void EarthBorn(Game joc, int row){
        for(int i = 0 ; i < joc.getMasa().get(row).size();i++){
            joc.getMasa().get(row).get(i).setHealth(joc.getMasa().get(row).get(i).getHealth() + 1);
        }

    }
    void BloodThirst(Game joc, int row){
        for(int i = 0 ; i < joc.getMasa().get(row).size();i++){
            joc.getMasa().get(row).get(i).setAttackDamage(joc.getMasa().get(row).get(i).getAttackDamage() + 1);
        }
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
