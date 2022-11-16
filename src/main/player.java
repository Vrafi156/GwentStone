package main;

import fileio.CardInput;
import fileio.Input;

import java.util.ArrayList;

public class player {
    private int nrCardsInDeck;
    private int nrDecks;
     ArrayList<card> deck = new ArrayList<card>();
     ArrayList<card> hand = new ArrayList<card>();

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    private hero erou;
    int mana;
    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public void setNrDecks(int nrDecks) {
        this.nrDecks = nrDecks;
    }

    public ArrayList<card> getDecks() {
        return deck;
    }

    public void setDecks(ArrayList<card> decks) {
        this.deck = decks;
    }

    //insert functie de adaugat carti pentru jucator


    public hero getErou() {
        return erou;
    }
    void addDeck1(player jucator, int n, int j , Input input){
        Minion b;
        Environment c;
        for(int i = 0 ; i < n ; i++) {
            CardInput a = input.getPlayerOneDecks().getDecks().get(j).get(i); //selectez o carte
            if (a.getName().equals("Winterfell") || a.getName().equals("Firestorm") || a.getName().equals("Heart Hound")) {
                c = new Environment(a.getMana(), a.getDescription(), a.getColors(), a.getName());
                jucator.getDecks().add(new Environment(c));
            } else {
                b = new Minion(a.getMana(), a.getAttackDamage(), a.getHealth(), a.getDescription(), a.getColors(), a.getName());
                jucator.getDecks().add(new Minion(b));
            }
        }
    }
    void addDeck2(player jucator, int n, int j , Input input) {
        Minion b;
        Environment c;
        for (int i = 0; i < n; i++) {
                CardInput a = input.getPlayerTwoDecks().getDecks().get(j).get(i); //selectez o carte
                if (a.getName().equals("Winterfell") || a.getName().equals("Firestorm") || a.getName().equals("Heart Hound")) {
                    c = new Environment(a.getMana(), a.getDescription(), a.getColors(), a.getName());
                    jucator.getDecks().add(new Environment(c));
                } else {
                    b = new Minion(a.getMana(), a.getAttackDamage(), a.getHealth(), a.getDescription(), a.getColors(), a.getName());
                    jucator.getDecks().add(new Minion(b));
                }
            }
    }


    public ArrayList<card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<card> deck) {
        this.deck = deck;
    }

    public ArrayList<card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<card> hand) {
        this.hand = hand;
    }

    public void setErou(hero erou) {
        this.erou = erou;
    }

    @Override
    public String toString() {
        return "player{" +
                "nrCardsInDeck=" + nrCardsInDeck +
                ", nrDecks=" + nrDecks +
                ", decks=" + deck +
                ", erou=" + erou +
                '}';
    }
}
