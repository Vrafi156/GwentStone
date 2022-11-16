package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.CardInput;
import fileio.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1), Input.class);

        ArrayNode output = objectMapper.createArrayNode();
        //TODO add here the entry point to your implementation

        Game joc = new Game();
        int nrdecks1, nrCards1, indexdeck1, indexdeck2, seed;
        int nrCard2;
        int nrdecks2;
        int starter;
        String command;
        for (int i = 0; i < 4; i++) {
            joc.getMasa().add(new ArrayList<>());
        }

        int howManyTurns = 0;
        //Sa inceapa magia
        int manapower = 1;

        int v1 = 0;
        int v2 = 0;
        for (int k = 0; k < inputData.getGames().size(); k++) {
            nrCards1 = inputData.getPlayerOneDecks().getNrCardsInDeck();
            nrdecks1 = inputData.getPlayerOneDecks().getNrDecks();
            indexdeck1 = inputData.getGames().get(k).getStartGame().getPlayerOneDeckIdx();
            //detaliile pentru jucatorul 1;
            howManyTurns = 0;
           manapower = 1;
            for(int i = 0 ; i < 4 ; i++){
                joc.getMasa().get(i).clear();
            }
            joc.player1 = new player();
            joc.player2 = new player();
            joc.player2.setMana(1);
            joc.player1.setMana(1);
            joc.player1.getHand().clear();
            joc.player1.getDeck().clear();
            joc.player2.getHand().clear();
            joc.player2.getDeck().clear();

            //
            nrCard2 = inputData.getPlayerTwoDecks().getNrCardsInDeck();
            nrdecks2 = inputData.getPlayerTwoDecks().getNrDecks();
            indexdeck2 = inputData.getGames().get(k).getStartGame().getPlayerTwoDeckIdx();
            //detaliile pt jucatorul2
            seed = inputData.getGames().get(k).getStartGame().getShuffleSeed();
            Random r = new Random(seed);

            starter = inputData.getGames().get(k).getStartGame().getStartingPlayer();
            joc.player1.setDeck(new ArrayList<card>());
            joc.player2.setDeck(new ArrayList<card>());
            joc.player1.addDeck1(joc.player1, nrCards1, indexdeck1, inputData);
            r = new Random(seed);
            Collections.shuffle(joc.player1.getDeck(), r);
            if (joc.player1.getDecks().get(0) instanceof Environment)
                joc.player1.hand.add(new Environment((Environment) joc.player1.getDecks().get(0)));
            else
                joc.player1.hand.add(new Minion((Minion) joc.player1.getDecks().get(0)));

            joc.player1.getDecks().remove(0);
            //
            joc.player2.addDeck2(joc.player2, nrCard2, indexdeck2, inputData);
            r = new Random(seed);
            Collections.shuffle(joc.player2.getDeck(), r);
            if (joc.player2.getDecks().get(0) instanceof Environment)
                joc.player2.hand.add(new Environment((Environment) joc.player2.getDecks().get(0)));
            else joc.player2.hand.add(new Minion((Minion) joc.player2.getDecks().get(0)));

            joc.player2.getDecks().remove(0);
            //




            CardInput merou = inputData.getGames().get(k).getStartGame().getPlayerOneHero();
            joc.player1.setErou(new hero(merou.getMana(), merou.getDescription(), merou.getColors(), merou.getName()));
            //
            merou = inputData.getGames().get(k).getStartGame().getPlayerTwoHero();
            joc.player2.setErou(new hero(merou.getMana(), merou.getDescription(), merou.getColors(), merou.getName()));
            //
            starter = inputData.getGames().get(k).getStartGame().getStartingPlayer();
            //

            for (int j = 0; j < inputData.getGames().get(k).getActions().size(); j++) {
                command = inputData.getGames().get(k).getActions().get(j).getCommand();
                if (command.equals("getPlayerDeck")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getPlayerIdx();
                    if (x == 1)
                        output.addObject().put("command", "getPlayerDeck").put("playerIdx", x).putPOJO("output", joc.player1.getDecks());
                    else
                        output.addObject().put("command", "getPlayerDeck").put("playerIdx", x).putPOJO("output", joc.player2.getDecks());
                }
                if (command.equals("getPlayerHero")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getPlayerIdx();
                    hero a;
                    if (x == 1) {

                        a = new hero(joc.player1.getErou());
                        output.addObject().put("command", "getPlayerHero").put("playerIdx", 1).putPOJO("output", a);
                    } else {
                        a = new hero(joc.player2.getErou());

                        output.addObject().put("command", "getPlayerHero").put("playerIdx", 2).putPOJO("output", a);
                    }
                }
                if (command.equals("getPlayerTurn")) {
                    output.addObject().put("command", "getPlayerTurn").put("output", starter);

                }
                if (command.equals("endPlayerTurn")) {
                    howManyTurns++;
                    if (howManyTurns == 2) {
                        if (joc.player1.getDecks().size() != 0) {

                            if (joc.player1.getDecks().get(0) instanceof Environment)
                                joc.player1.hand.add(new Environment((Environment) joc.player1.getDecks().get(0)));
                            else joc.player1.hand.add(new Minion((Minion) joc.player1.getDecks().get(0)));
                            joc.player1.getDecks().remove(0);
                        }
                        if (joc.player2.getDecks().size() != 0) {

                            if (joc.player2.getDecks().get(0) instanceof Environment)
                                joc.player2.hand.add(new Environment((Environment) joc.player2.getDecks().get(0)));
                            else joc.player2.hand.add(new Minion((Minion) joc.player2.getDecks().get(0)));
                            joc.player2.getDecks().remove(0);
                        }
                        // de rezolvat cu mana TODO


                        if (manapower < 10) manapower++;
                        joc.player1.setMana(joc.player1.getMana() + manapower);
                        joc.player2.setMana(joc.player2.getMana() + manapower);

                        howManyTurns = 0;
                    }


                    if (starter == 2) {
                        joc.unfreeze(joc.getMasa(), 0, 1);
                        joc.unattack(joc.getMasa(), 0, 1);
                        joc.getPlayer2().getErou().attacked = 0;
                        starter = 1;
                    } else {
                        joc.unfreeze(joc.getMasa(), 2, 3);
                        joc.unattack(joc.getMasa(), 2, 3);
                        joc.getPlayer1().getErou().attacked = 0;
                        starter = 2;
                    }
                }
                if (command.equals("placeCard")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getHandIdx();
                    int row = 0;
                    Minion a;
                    if (starter == 1) {
                        if (!(joc.getPlayer1().getHand().get(x) instanceof Environment)) {
                            a = new Minion((Minion) joc.getPlayer1().getHand().get(x));
                            if (a.getName().equals("The Ripper") || a.getName().equals("Warden") || a.getName().equals("Goliath") || a.getName().equals("Miraj"))
                                row = 2;
                            else
                                row = 3;
                        }

                        if (joc.getPlayer1().getHand().get(x) instanceof Environment) {
                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Cannot place environment card on table.");

                        } else if (joc.player1.getMana() < joc.getPlayer1().getHand().get(x).getMana()) {
                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Not enough mana to place card on table.");

                        } else if (joc.getMasa().get(row).size() == 5) // asta nu mrege inca
                        {

                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Cannot place card on table since row is full.");
                        } else {

                            joc.getMasa().get(row).add(new Minion((Minion) joc.getPlayer1().getHand().get(x)));
                            joc.getPlayer1().setMana(joc.getPlayer1().getMana() - joc.getPlayer1().getHand().get(x).getMana());
                            joc.getPlayer1().getHand().remove(x);

                        }
                    } else {
                        if (!(joc.getPlayer2().getHand().get(x) instanceof Environment)) {
                            a = new Minion((Minion) joc.getPlayer2().getHand().get(x));
                            if ((a.getName().equals("The Ripper") || a.getName().equals("Warden") || a.getName().equals("Goliath") || a.getName().equals("Miraj")))
                                row = 1;
                            else
                                row = 0;
                        }

                        if (joc.getPlayer2().getHand().get(x) instanceof Environment) {
                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Cannot place environment card on table.");

                        } else if (joc.player2.getMana() < joc.getPlayer2().getHand().get(x).getMana()) {
                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Not enough mana to place card on table.");

                        } else if (joc.getMasa().get(row).size() == 5) // asta nu mrege inca
                            output.addObject().put("command", "placeCard").put("handIdx", x).put("error", "Cannot place card on table since row is full.");
                        else {

                            joc.getMasa().get(row).add(new Minion((Minion) joc.getPlayer2().getHand().get(x)));
                            joc.getPlayer2().setMana(joc.getPlayer2().getMana() - joc.getPlayer2().getHand().get(x).getMana());
                            joc.getPlayer2().getHand().remove(x);
                        }

                    }
                }
                if (command.equals("getPlayerMana")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getPlayerIdx();
                    int curMana;
                    if (x == 1) {
                        curMana = joc.player1.getMana();
                        output.addObject().put("command", "getPlayerMana").put("output", curMana).put("playerIdx", 1);
                    } else {
                        curMana = joc.player2.getMana();

                        output.addObject().put("command", "getPlayerMana").put("output", curMana).put("playerIdx", 2);
                    }
                }
                if (command.equals("getCardsInHand")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getPlayerIdx();
                    ArrayList<card> bonusArray = new ArrayList<card>();
                    if (x == 1) {
                        for (int i = 0; i < joc.player1.getHand().size(); i++)
                            if (joc.player1.getHand().get(i) instanceof Environment)
                                bonusArray.add(new Environment((Environment) joc.player1.getHand().get(i)));
                            else bonusArray.add(new Minion((Minion) joc.player1.getHand().get(i)));
                        output.addObject().put("command", "getCardsInHand").put("playerIdx", x).putPOJO("output", bonusArray);
                    } else {
                        for (int i = 0; i < joc.player2.getHand().size(); i++)
                            if (joc.player2.getHand().get(i) instanceof Environment)
                                bonusArray.add(new Environment((Environment) joc.player2.getHand().get(i)));
                            else bonusArray.add(new Minion((Minion) joc.player2.getHand().get(i)));
                        output.addObject().put("command", "getCardsInHand").put("playerIdx", x).putPOJO("output", bonusArray);
                    }
                }
                if (command.equals("getCardsOnTable")) {
                    ArrayList<ArrayList<Minion>> copytable = new ArrayList<ArrayList<Minion>>(4);
                    for (int q = 0; q < 4; q++)
                        copytable.add(new ArrayList<>());
                    for (int q = 0; q < 4; q++)
                        for (int p = 0; p < joc.getMasa().get(q).size(); p++)
                            copytable.get(q).add(new Minion(joc.getMasa().get(q).get(p)));
                    output.addObject().put("command", "getCardsOnTable").putPOJO("output", copytable);
                }
                if (command.equals("getEnvironmentCardsInHand")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getPlayerIdx();
                    ArrayList<card> bonusArray = new ArrayList<card>();
                    if (x == 1) {
                        for (int i = 0; i < joc.player1.getHand().size(); i++)
                            if (joc.player1.getHand().get(i) instanceof Environment)
                                bonusArray.add(new Environment((Environment) joc.player1.getHand().get(i)));
                        output.addObject().put("command", "getEnvironmentCardsInHand").put("playerIdx", x).putPOJO("output", bonusArray);
                    } else {
                        for (int i = 0; i < joc.player2.getHand().size(); i++)
                            if (joc.player2.getHand().get(i) instanceof Environment)
                                bonusArray.add(new Environment((Environment) joc.player2.getHand().get(i)));
                        output.addObject().put("command", "getEnvironmentCardsInHand").put("playerIdx", x).putPOJO("output", bonusArray);
                    }
                }
                if (command.equals("getCardAtPosition")) {
                    int x, y;
                    x = inputData.getGames().get(k).getActions().get(j).getX();
                    y = inputData.getGames().get(k).getActions().get(j).getY();
                    Minion extracted;
                    if (y < joc.getMasa().get(x).size()) {
                        extracted = new Minion(joc.masa.get(x).get(y));
                        output.addObject().put("command", "getCardAtPosition").putPOJO("output", extracted).put("x", x).put("y", y);
                    } else
                        output.addObject().put("command", "getCardAtPosition").put("x", x).put("y", y).put("output", "No card available at that position.");

                }
                if (command.equals("useEnvironmentCard")) {

                    int x = inputData.getGames().get(k).getActions().get(j).getHandIdx();
                    int affected = inputData.getGames().get(k).getActions().get(j).getAffectedRow();
                    Environment spell;
                    int ok = 1;
                    if (starter == 1) {
                        if (joc.player1.getHand().get(x) instanceof Environment && joc.player1.mana >= joc.player1.getMana() && (affected == 0 || affected == 1)) {
                            spell = new Environment((Environment) joc.player1.getHand().get(x));
                            ok = 1;

                            if (spell.getName().equals("Winterfell")) {
                                joc.Winterfell(joc.masa, affected);
                                joc.player1.getHand().remove(x);
                                joc.player1.setMana(joc.player1.getMana() - spell.getMana());

                            }
                            if (spell.getName().equals("Firestorm")) {
                                joc.Firestorm(joc.masa, affected);
                                joc.player1.getHand().remove(x);
                                joc.player1.setMana(joc.player1.getMana() - spell.getMana());

                            }
                            if (spell.getName().equals("Heart Hound")) {

                                if (affected == 3) {
                                    ok = 0;
                                } else if (affected == 2) {
                                    ok = 1;
                                } else if (affected == 1) {
                                    ok = 2;
                                } else {
                                    ok = 3;
                                }
                                if (joc.getMasa().get(ok).size() < 5) {
                                    joc.Heart(joc.masa, affected, joc.player1.getHand());
                                    joc.player1.setMana(joc.player1.getMana() - spell.getMana());
                                } else
                                    output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Cannot steal enemy card since the player's row is full.").put("handIdx", x);


                            }
                        } else {
                            if (!(joc.player1.getHand().get(x) instanceof Environment)) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Chosen card is not of type environment.").put("handIdx", x);

                            } else if (joc.player1.getHand().get(x).getMana() > joc.player1.getMana()) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Not enough mana to use environment card.").put("handIdx", x);

                            } else if (affected == 2 || affected == 3) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Chosen row does not belong to the enemy.").put("handIdx", x);
                            }
                        }

                    } else {
                        if (joc.player2.getHand().get(x) instanceof Environment && joc.player2.getMana() >= joc.player2.getMana() && (affected == 2 || affected == 3)) {

                            spell = new Environment((Environment) joc.player2.getHand().get(x));

                            if (spell.getName().equals("Winterfell")) {
                                joc.player2.getHand().remove(x);
                                joc.Winterfell(joc.masa, affected);

                                joc.player2.setMana(joc.player2.getMana() - spell.getMana());


                            }
                            if (spell.getName().equals("Firestorm")) {
                                joc.Firestorm(joc.masa, affected);
                                joc.player2.getHand().remove(x);
                                joc.player2.setMana(joc.player2.getMana() - spell.getMana());

                            }
                            if (spell.getName().equals("Heart Hound")) {
                                if (affected == 3) {
                                    ok = 0;
                                } else if (affected == 2) {
                                    ok = 1;
                                } else if (affected == 1) {
                                    ok = 2;
                                } else {
                                    ok = 3;
                                }
                                if (joc.masa.get(ok).size() < 5) {
                                    joc.Heart(joc.masa, affected, joc.player2.getHand());
                                    joc.player2.setMana(joc.player2.getMana() - spell.getMana());
                                } else
                                    output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Cannot steal enemy card since the player's row is full.").put("handIdx", x);
                            }
                        } else {
                            if (!(joc.player2.getHand().get(x) instanceof Environment)) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Chosen card is not of type environment.").put("handIdx", x);

                            } else if (joc.player2.getHand().get(x).getMana() > joc.player2.getMana()) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Not enough mana to use environment card.").put("handIdx", x);

                            } else if (affected == 0 || affected == 1) {
                                output.addObject().put("affectedRow", affected).put("command", "useEnvironmentCard").put("error", "Chosen row does not belong to the enemy.").put("handIdx", x);
                            }
                        }
                    }
                }
                if (command.equals("getFrozenCardsOnTable")) {
                    ArrayList<Minion> aux = new ArrayList<Minion>();
                    joc.getfrost(joc.getMasa(), aux);
                    output.addObject().put("command", "getFrozenCardsOnTable").putPOJO("output", aux);
                }
                if (command.equals("cardUsesAttack")) {
                    int x1, x2, y1, y2;

                    x1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getX();
                    y1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getY();
                    x2 = inputData.getGames().get(k).getActions().get(j).getCardAttacked().getX();
                    y2 = inputData.getGames().get(k).getActions().get(j).getCardAttacked().getY();
                    if (x1 >= 0 && x1 < 5 && x2 >= 0 && x2 < 5 && y1 >= 0 && y1 < joc.getMasa().get(x1).size() && y2 >= 0 && y2 < joc.getMasa().get(x2).size()) {
                        Minion min = new Minion(joc.getMasa().get(x1).get(y1));
                        String checkAttack;
                        checkAttack = min.CheckattackonTable(joc.masa, x1, y1, x2, y2, starter);

                        if (!checkAttack.equals("ok")) {
                            coordonate a = new coordonate(x1, y1);
                            coordonate b = new coordonate(x2, y2);
                            checkAttackError afisare = new checkAttackError("cardUsesAttack", checkAttack, a, b);
                            output.addPOJO(afisare);
                        } else {
                            min.attackonTable(joc.getMasa(), x1, y1, x2, y2);
                        }
                    }

                }
                if (command.equals("cardUsesAbility")) {
                    int x1, x2, y1, y2;
                    x1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getX();
                    y1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getY();
                    x2 = inputData.getGames().get(k).getActions().get(j).getCardAttacked().getX();
                    y2 = inputData.getGames().get(k).getActions().get(j).getCardAttacked().getY();
                    if (x1 >= 0 && x1 < 5 && x2 >= 0 && x2 < 5 && y1 >= 0 && y1 < joc.getMasa().get(x1).size() && y2 >= 0 && y2 < joc.getMasa().get(x2).size()) {
                        Minion min = new Minion(joc.getMasa().get(x1).get(y1));
                        String checkCardAbility;
                        checkCardAbility = min.Checkability(joc.masa, x1, y1, x2, y2, starter);
                        if (!checkCardAbility.equals("ok")) {
                            coordonate a = new coordonate(x1, y1);
                            coordonate b = new coordonate(x2, y2);
                            checkAttackError afisare = new checkAttackError("cardUsesAbility", checkCardAbility, a, b);
                            output.addPOJO(afisare);
                        } else {
                            min.attackability(joc.getMasa(), x1, y1, x2, y2);

                        }
                    }
                }
                if (command.equals("useAttackHero")) {
                    int x1, y1;
                    x1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getX();
                    y1 = inputData.getGames().get(k).getActions().get(j).getCardAttacker().getY();
                    if (x1 >= 0 && x1 < 5 && y1 >= 0 && y1 < joc.getMasa().get(x1).size()) {
                        Minion min = new Minion(joc.getMasa().get(x1).get(y1));
                        String checkHeroAttack;
                        String victory;
                        checkHeroAttack = joc.checkHeroAttack(joc, x1, y1, starter);
                        if (!checkHeroAttack.equals("ok")) {
                            coordonate a = new coordonate(x1, y1);
                            checkAttackHero afisare = new checkAttackHero("useAttackHero", checkHeroAttack, a);
                            output.addPOJO(afisare);
                        } else {
                            victory = joc.attackHero(joc, x1, y1, starter);
                            if (!(victory.equals("ok"))) {
                                if(victory.equals("Player one killed the enemy hero."))
                                    v1++;
                                if(victory.equals("Player two killed the enemy hero."))
                                    v2++;
                                output.addObject().put("gameEnded", victory);
                            }
                        }
                    }
                }
                if (command.equals("useHeroAbility")) {
                    int x = inputData.getGames().get(k).getActions().get(j).getAffectedRow();
                    String checkheropower;
                    checkheropower = joc.checkHeroability(joc, x, starter);
                    if (!checkheropower.equals("ok")) {
                        output.addObject().put("command", "useHeroAbility").put("affectedRow", x).put("error", checkheropower);
                    } else {
                        if (starter == 1) {
                            if (joc.getPlayer1().getErou().getName().equals("Lord Royce")) {
                                joc.getPlayer1().getErou().SubZero(joc, x);
                                joc.getPlayer1().setMana(joc.getPlayer1().getMana() - joc.getPlayer1().getErou().getMana());
                                if (joc.getPlayer1().getMana() < 0)
                                    joc.getPlayer1().setMana(0);

                            }
                            if (joc.getPlayer1().getErou().getName().equals("Empress Thorina")) {
                                joc.getPlayer1().getErou().LowBlow(joc, x);
                                joc.getPlayer1().setMana(joc.getPlayer1().getMana() - joc.getPlayer1().getErou().getMana());
                                if (joc.getPlayer1().getMana() < 0)
                                    joc.getPlayer1().setMana(0);
                            }
                            if (joc.getPlayer1().getErou().getName().equals("King Mudface")) {
                                joc.getPlayer1().getErou().EarthBorn(joc, x);
                                joc.getPlayer1().setMana(joc.getPlayer1().getMana() - joc.getPlayer1().getErou().getMana());
                                if (joc.getPlayer1().getMana() < 0)
                                    joc.getPlayer1().setMana(0);
                            }
                            if (joc.getPlayer1().getErou().getName().equals("General Kocioraw")) {
                                joc.getPlayer1().getErou().BloodThirst(joc, x);
                                joc.getPlayer1().setMana(joc.getPlayer1().getMana() - joc.getPlayer1().getErou().getMana());
                                if (joc.getPlayer1().getMana() < 0)
                                    joc.getPlayer1().setMana(0);
                            }
                            joc.getPlayer1().getErou().attacked = 1;

                        } else {
                            if (joc.getPlayer2().getErou().getName().equals("Lord Royce")) {
                                joc.getPlayer2().getErou().SubZero(joc, x);
                                joc.getPlayer2().setMana(joc.getPlayer2().getMana() - joc.getPlayer2().getErou().getMana());
                                if (joc.getPlayer2().getMana() < 0)
                                    joc.getPlayer2().setMana(0);

                            }
                            if (joc.getPlayer2().getErou().getName().equals("Empress Thorina")) {
                                joc.getPlayer2().getErou().LowBlow(joc, x);
                                joc.getPlayer2().setMana(joc.getPlayer2().getMana() - joc.getPlayer2().getErou().getMana());
                                if (joc.getPlayer2().getMana() < 0)
                                    joc.getPlayer2().setMana(0);
                            }
                            if (joc.getPlayer2().getErou().getName().equals("King Mudface")) {
                                joc.getPlayer2().getErou().EarthBorn(joc, x);
                                joc.getPlayer2().setMana(joc.getPlayer2().getMana() - joc.getPlayer2().getErou().getMana());
                                if (joc.getPlayer2().getMana() < 0)
                                    joc.getPlayer2().setMana(0);
                            }
                            if (joc.getPlayer2().getErou().getName().equals("General Kocioraw")) {
                                joc.getPlayer2().getErou().BloodThirst(joc, x);
                                joc.getPlayer2().setMana(joc.getPlayer2().getMana() - joc.getPlayer2().getErou().getMana());
                                if (joc.getPlayer2().getMana() < 0)
                                    joc.getPlayer2().setMana(0);
                            }
                            joc.getPlayer2().getErou().attacked = 1;
                        }
                    }
                }
                if (command.equals("getPlayerOneWins")){
                    output.addObject().put("command","getPlayerOneWins").put("output",v1);
                }

                if (command.equals("getPlayerTwoWins")){
                    output.addObject().put("command","getPlayerTwoWins").put("output",v2);
                }
                if (command.equals("getTotalGamesPlayed")){
                    output.addObject().put("command","getTotalGamesPlayed").put("output",k+1);

                }



            }
        }

        //acum citim functiilez

        System.out.println();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
