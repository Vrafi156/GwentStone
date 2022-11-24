package myclasses;

import java.util.ArrayList;

public class Minion extends card {
    private int attackDamage;
    private int health;
    int frozen;
    int attacked;

    public Minion(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {


        this.setMana(mana);
        this.setDescription(description);
        this.setColors(colors);
        this.setName(name);

        this.attackDamage = attackDamage;
        this.health = health;
        this.frozen = 0;
        this.attacked = 0;

    }

    public Minion(Minion a) {
        this.setMana(a.getMana());
        this.setDescription(new String(a.getDescription()));
        this.setColors(new ArrayList<String>(a.getColors()));
        this.setName(new String(a.getName()));
        this.attackDamage = a.attackDamage;
        this.health = a.health;
        this.frozen = a.frozen;
        this.attacked = a.attacked;
    }

    int firstorsecond(Minion a) {
        return 1;

    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void attackonTable(ArrayList<ArrayList<Minion>> masa, int x1, int y1, int x2, int y2) {
        Minion attacker = new Minion(masa.get(x1).get(y1));
        masa.get(x2).get(y2).setHealth(masa.get(x2).get(y2).getHealth() - attacker.attackDamage);
        if (masa.get(x2).get(y2).getHealth() <= 0)
            masa.get(x2).remove(y2);
        masa.get(x1).get(y1).attacked = 1;
    }


    public String CheckattackonTable(ArrayList<ArrayList<Minion>> masa, int x1, int y1, int x2, int y2, int starter) {
        Minion attacker = new Minion(masa.get(x1).get(y1));
        if (starter == 1) {
            if (x2 == 2 || x2 == 3)
                return "Attacked card does not belong to the enemy.";
        }
        if (starter == 2) {
            if (x2 == 0 || x2 == 1)
                return "Attacked card does not belong to the enemy.";
        }
        if (attacker.frozen == 1) {
            return "Attacker card is frozen.";
        }

        if (attacker.attacked == 1) {
            return "Attacker card has already attacked this turn.";

        }
        if (masa.get(x2).get(y2).getName().equals("Goliath") || masa.get(x2).get(y2).equals("Warden"))
            return "ok";

        int ok = 0;
        if (!(masa.get(x2).get(y2).getName().equals("Goliath") || masa.get(x2).get(y2).getName().equals("Warden"))) {
            if (starter == 1) {
                for (int i = 0; i < masa.get(1).size(); i++) {
                    if (masa.get(1).get(i).getName().equals("Goliath") || masa.get(1).get(i).getName().equals("Warden")) {
                        ok = 1;
                    }
                }
            } else {
                for (int i = 0; i < masa.get(2).size(); i++) {
                    if (masa.get(2).get(i).getName().equals("Goliath") || masa.get(2).get(i).getName().equals("Warden")) {
                        ok = 1;

                    }
                }
            }

        }
        if (ok == 1)
            return "Attacked card is not of type 'Tank'.";
        return "ok";
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public void setAttacked(int attacked) {
        this.attacked = attacked;
    }

    public String Checkability(ArrayList<ArrayList<Minion>> masa, int x1, int y1, int x2, int y2, int starter) {
        Minion attacker = new Minion(masa.get(x1).get(y1));
        if (attacker.frozen == 1) {
            return "Attacker card is frozen.";

        }
        if (attacker.attacked == 1) {
            return "Attacker card has already attacked this turn.";
        }
        if (attacker.getName().equals("Disciple")) {
            if (starter == 1) {
                if (x2 == 0 || x2 == 1)
                    return "Attacked card does not belong to the current player.";

            } else {
                if (x2 == 2 || x2 == 3)
                    return "Attacked card does not belong to the current player.";
            }
        }
        if (attacker.getName().equals("The Ripper") || attacker.getName().equals("Miraj") || attacker.getName().equals("The Cursed One")) {
            if (starter == 1) {
                if (x2 == 2 || x2 == 3)
                    return "Attacked card does not belong to the enemy.";
            } else {
                if (x2 == 0 || x2 == 1)
                    return "Attacked card does not belong to the enemy.";
            }
            int ok = 0;
            if (starter == 1) {
                for (int i = 0; i < masa.get(1).size(); i++) {
                    if (masa.get(1).get(i).getName().equals("Goliath") || masa.get(1).get(i).equals("Warden")) {
                        ok = 1;
                    }
                }
            } else {
                for (int i = 0; i < masa.get(2).size(); i++) {
                    if (masa.get(2).get(i).getName().equals("Goliath") || masa.get(2).get(i).equals("Warden")) {
                        ok = 1;

                    }
                }
            }

            if (ok == 1)
                if (!(masa.get(x2).get(y2).getName().equals("Goliath") || masa.get(x2).get(y2).equals("Warden")))
                    return "Attacked card is not of type 'Tank'.";

        }
        return "ok";

    }


    public void attackability(ArrayList<ArrayList<Minion>> masa, int x1, int y1, int x2, int y2) {
        Minion attacker = new Minion(masa.get(x1).get(y1));

        if (attacker.getName().equals("Disciple")) {
            masa.get(x2).get(y2).setHealth(masa.get(x2).get(y2).getHealth() + 2);
        }
        if (attacker.getName().equals("Miraj")) {
            int swap = attacker.getHealth();
            masa.get(x1).get(y1).setHealth(masa.get(x2).get(y2).getHealth());
            masa.get(x2).get(y2).setHealth(swap);

        }
        if (attacker.getName().equals("The Cursed One")) {
            int swap = masa.get(x2).get(y2).getHealth();
            masa.get(x2).get(y2).setHealth(masa.get(x2).get(y2).getAttackDamage());
            masa.get(x2).get(y2).setAttackDamage(swap);
            if (masa.get(x2).get(y2).getHealth() == 0) {
                masa.get(x2).remove(y2);
            }
        }
        if (attacker.getName().equals("The Ripper")) {
            masa.get(x2).get(y2).setAttackDamage(masa.get(x2).get(y2).getAttackDamage() - 2);
            if (masa.get(x2).get(y2).getAttackDamage() < 0)
                masa.get(x2).get(y2).setAttackDamage(0);

        }
        masa.get(x1).get(y1).attacked = 1;
    }

    void talent() {
    }

    //de implementat minioni
    public String toString() {
        return "CardInput{"
                + "mana="
                + getMana()
                + ", attackDamage="
                + attackDamage
                + ", health="
                + health
                + ", description='"
                + getDescription()
                + '\''
                + ", colors="
                + getColors()
                + ", name='"
                + ""
                + getName()
                + '\''
                + '}';
    }


}
