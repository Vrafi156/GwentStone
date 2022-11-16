package main;

import java.util.ArrayList;

public class Game {
    player player1 = new player();
    player player2 = new player();
    ArrayList<ArrayList<Minion>> masa = new ArrayList<ArrayList<Minion>>(4);


    int playerOneDeckIdx;
    int playerTwoDeckIdx;
    int shuffleSeed;
    int startingPlayer;
    int turn;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public player getPlayer1() {
        return player1;
    }

    public void setPlayer1(player player1) {
        this.player1 = player1;
    }

    public player getPlayer2() {
        return player2;
    }

    public void setPlayer2(player player2) {
        this.player2 = player2;
    }


    public ArrayList<ArrayList<Minion>> getMasa() {
        return masa;
    }

    public void setMasa(ArrayList<ArrayList<Minion>> masa) {
        this.masa = masa;
    }

    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    public void setPlayerOneDeckIdx(int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    public void setPlayerTwoDeckIdx(int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }

    public int getShuffleSeed() {
        return shuffleSeed;
    }

    public void setShuffleSeed(int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }


    void Firestorm(ArrayList<ArrayList<Minion>> masa, int row) {
        for (int i = 0; i < masa.get(row).size(); i++)
            masa.get(row).get(i).setHealth(masa.get(row).get(i).getHealth() - 1);
        int j = 0;
        while (j < masa.get(row).size()) {
            if (masa.get(row).get(j).getHealth() == 0)
                masa.get(row).remove(j);
            else
                j++;
        }


    }

    void Winterfell(ArrayList<ArrayList<Minion>> masa, int row) {
        for (int i = 0; i < masa.get(row).size(); i++)
            masa.get(row).get(i).setFrozen(1);
        ;

    }

    void Heart(ArrayList<ArrayList<Minion>> masa, int row, ArrayList<card> a) {
        int imax = 0;
        int max = masa.get(row).get(0).getHealth();
        int oglindit;
        for (int i = 1; i < masa.get(row).size(); i++) {
            if (masa.get(row).get(i).getHealth() > max) {
                imax = i;
                max = masa.get(row).get(i).getHealth();
            }
        }
        Minion schimb = new Minion(masa.get(row).get(imax));
        if (row == 3) {
            oglindit = 0;
        } else if (row == 2) {
            oglindit = 1;
        } else if (row == 1) {
            oglindit = 2;
        } else {
            oglindit = 3;
        }
        if (masa.get(oglindit).size() < 5) {
            masa.get(row).remove(imax);
            masa.get(oglindit).add(schimb);
            a.remove(imax);

        }

    }

    void unfreeze(ArrayList<ArrayList<Minion>> masa, int x, int y) {
        for (int i = 0; i < masa.get(x).size(); i++) {
            masa.get(x).get(i).setFrozen(0);
        }
        for (int i = 0; i < masa.get(y).size(); i++)
            masa.get(y).get(i).setFrozen(0);
    }

    void unattack(ArrayList<ArrayList<Minion>> masa, int x, int y) {
        for (int i = 0; i < masa.get(x).size(); i++) {
            masa.get(x).get(i).setAttacked(0);
            ;

        }
        for (int i = 0; i < masa.get(y).size(); i++) {
            masa.get(y).get(i).setAttacked(0);

        }
    }

    void getfrost(ArrayList<ArrayList<Minion>> masa, ArrayList<Minion> a) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < masa.get(i).size(); j++) {
                if (masa.get(i).get(j).frozen == 1) {
                    a.add(masa.get(i).get(j));
                }
            }
    }

    String checkHeroAttack(Game joc, int x, int y, int starter) {
        Minion attacker = new Minion(masa.get(x).get(y));
        if (attacker.frozen == 1) {
            return "Attacker card is frozen.";
        }

        if (attacker.attacked == 1) {
            return "Attacker card has already attacked this turn.";
        }
        int ok = 0;
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
        if (ok == 1)
            return "Attacked card is not of type 'Tank'.";
        return "ok";

    }

    String attackHero(Game joc, int x, int y, int starter) {
        Minion attacker = new Minion(masa.get(x).get(y));
        if (starter == 1) {
            joc.getPlayer2().getErou().setHealth(joc.getPlayer2().getErou().getHealth() - attacker.getAttackDamage());
            if (joc.getPlayer2().getErou().getHealth() <= 0)
                return "Player one killed the enemy hero.";
        } else {
            joc.getPlayer1().getErou().setHealth(joc.getPlayer1().getErou().getHealth() - attacker.getAttackDamage());
            if (joc.getPlayer1().getErou().getHealth() <= 0)
                return "Player two killed the enemy hero.";
        }
        joc.getMasa().get(x).get(y).attacked = 1;
        return "ok";
    }

    String checkHeroability(Game joc, int row, int starter) {
        hero clone;
        if (starter == 1) {

            clone = joc.getPlayer1().getErou();
            if (joc.getPlayer1().getMana() < clone.getMana())
                return "Not enough mana to use hero's ability.";
            if (clone.attacked == 1)
                return "Hero has already attacked this turn.";

            if (clone.getName().equals("Lord Royce") || clone.getName().equals("Empress Thorina")) {
                if (row == 2 || row == 3)
                    return "Selected row does not belong to the enemy.";

            }
            if (clone.getName().equals("King Mudface") || clone.getName().equals("General Kocioraw")) {
                if (row == 0 || row == 1)
                    return "Selected row does not belong to the current player.";
            }
        } else {
            clone = joc.getPlayer2().getErou();
            if (joc.getPlayer2().getMana() < clone.getMana())
                return "Not enough mana to use hero's ability.";
            if (clone.attacked == 1)
                return "Hero has already attacked this turn.";

            if (clone.getName().equals("Lord Royce") || clone.getName().equals("Empress Thorina")) {
                if (row == 0 || row == 1)
                    return "Selected row does not belong to the enemy.";

            }
            if (clone.getName().equals("King Mudface") || clone.getName().equals("General Kocioraw")) {
                if (row == 2 || row == 3)
                    return "Selected row does not belong to the current player.";
            }
        }

        return "ok";
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

}
