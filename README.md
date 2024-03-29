

# Tema POO  - GwentStone

<div align="center"><img src="https://tenor.com/view/witcher3-gif-9340436.gif" width="500px"></div>

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema)


## Skel Structure

* src/
  * checker/ - checker files
  * fileio/ - contains classes used to read data from the json files
  * main/
      * Main - the Main class runs the checker on your implementation. Add the entry point to your implementation in it. Run Main to test your implementation from the IDE or from command line.
      * Test - run the main method from Test class with the name of the input file from the command line and the result will be written
        to the out.txt file. Thus, you can compare this result with ref.
* input/ - contains the tests in JSON format
* ref/ - contains all reference output for the tests in JSON format

## Tests

1. test01_game_start - 3p
2. test02_place_card - 4p
3. test03_place_card_invalid - 4p
4. test04_use_env_card - 4p
5. test05_use_env_card_invalid - 4p
6. test06_attack_card - 4p
7. test07_attack_card_invalid - 4p
8. test08_use_card_ability - 4p
9. test09_use_card_ability_invalid -4p
10. test10_attack_hero - 4p
11. test11_attack_hero_invalid - 4p
12. test12_use_hero_ability_1 - 4p
13. test13_use_hero_ability_2 - 4p
14. test14_use_hero_ability_invalid_1 - 4p
15. test15_use_hero_ability_invalid_2 - 4p
16. test16_multiple_games_valid - 5p
17. test17_multiple_games_invalid - 6p
18. test18_big_game - 10p


<div align="center"><img src="https://tenor.com/view/homework-time-gif-24854817.gif" width="500px"></div>

So... Let's begin.
For the GwentStone I implemented a class Game which contains
3 variabiles , 2 of the player class(Player1 and Player2) and an Arraylist 
of Arraylist of Minions. Firstly I implemented the class card which contains
all of the common aspects of a card (Mana ,Name , Colors,description ).
From the card class I extended the Classes Minions, Enviroment and Hero
The minion class has health and attackDamage, Envioment Card has special
methods for the specific types. Every player has a deck which he draws at the 
begging of the deck, with a deep copy constructor. When the deck is selected
from the decks arraylist is shuffled.

A player has it's own arraylist of cards(i considered an arraylist of cards
for both the hand and deck because i can keep there minions and enviroment cards aswell)

Every player start with 1 mana. The board is an arraylist of arraylist of the type MINION
Soo when a game begins each player gets their cards in hand and can play a card if they meet the
requirements to do so.

The game plays out by itself by reading the comands from input i determine
what action will take place at a specific time. For this i implemented
a string parser which reads the command and the chooses what to do.

For UseEnviroment CARD I defined 3 metods based on everytype of possible speel
the Enviroments can only affect minions on the board.

For the attack and ATTACKHERO I verify if the minions can attack (if the target isn't a warden or goliath 
and the enemy has one of them ) and if the attack succeds if the damaged target reaches health < 0 it gets 
eliminated and in the case of a Hero recheaning < 0 health the game ends with victory for the attacker's hero minion

For the use Hero Ability I defined 4 methods that affect the board pretty much 
like the USEENVIROMENT.

And for playing multiple games i make sure to clear the player decks , hands and board. Also for almost every
get function i make a deep copy.