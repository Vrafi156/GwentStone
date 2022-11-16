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
    for(int i = 0 ; i < masa.get(row).size(); i++)
      masa.get(row).get(i).setHealth( masa.get(row).get(i).getHealth() - 1);
    int j = 0;
    while(j < masa.get(row).size()) {
      if(masa.get(row).get(j).getHealth() == 0)
        masa.get(row).remove(j);
      else
        j++;
    }


  }
  void Winterfell (ArrayList<ArrayList<Minion>> masa, int row){
    for(int i = 0 ; i < masa.get(row).size(); i++)
      masa.get(row).get(i).frozen = 1;

  }
  void Heart(ArrayList<ArrayList<Minion>> masa, int row , ArrayList<card> a) {
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
    if (masa.get(oglindit).size() < 5)
    {
      masa.get(row).remove(imax);
      masa.get(oglindit).add(schimb);
      a.remove(imax);

    }

  }

  void unfreeze(ArrayList<ArrayList<Minion>> masa, int x, int y) {
    for (int i = 0; i < masa.get(x).size(); i++) {
      masa.get(x).get(i).frozen = 0;
    }
  }
    void unattack(ArrayList<ArrayList<Minion>> masa, int x, int y) {
      for(int i = 0; i < masa.get(x).size();i++){
        masa.get(x).get(i).attacked = 0;

    }
      for(int i = 0; i < masa.get(y).size();i++){
        masa.get(y).get(i).attacked = 0;

      }
  }

   void getfrost(ArrayList<ArrayList<Minion>> masa,ArrayList<Minion> a){
    for(int i = 0 ; i < 4 ; i++)
      for(int j = 0 ; j < masa.get(i).size();j++)
      {
        if(masa.get(i).get(j).frozen == 1)
        {
          a.add(masa.get(i).get(j));
        }
      }
  }




  public int getStartingPlayer() {
    return startingPlayer;
  }

  public void setStartingPlayer(int startingPlayer) {
    this.startingPlayer = startingPlayer;
  }

}
