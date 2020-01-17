
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Creating Dice Game
 *
 * @author Abdulla
 */
public class DiceGame {

    //Instance Fields
    private Player[] players; 
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    private boolean easy;
     

    //Constructor
    /**
     * Creating the constructor for the class
     */
    public DiceGame() {
        setUpGame();
    }

    //Mutator Methods
    /**
     * Creating the NumPlayers method
     *
     * @return
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Creating the player array
     *
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Creating the isGameOver method
     *
     * @return Boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * setUpGame method sets up the basics of the game
     */
    public void setUpGame() {
        //Setting up the basics of the game
        System.out.println("What mode of difficulty would you like to play?");
        Scanner mode = new Scanner(System.in);
        String modeSelect = mode.nextLine();

        System.out.println("How many people are going to play?");
        Scanner input = new Scanner(System.in);
        this.numPlayers = input.nextInt();
        input.nextLine();
        players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            System.out.println("Input the players name");
            players[i] = new Player(input.nextLine());
        }
        //Difficulty level
        //Type in "easy" for easy mode and "hard" for hard mode.
        if (modeSelect.equals("easy")) {
            easy = true;
        } else if (modeSelect.equals("hard")) {
            easy = false;
        }

        displayRules();
    }

    /**
     * displayRules displays the rules of the game
     */
    public void displayRules() {
        //Displaying the Rules of the game
        if (easy == true) {
            System.out.println("Rules for easy mode!");
            System.out.println("Each player places a bet and chooses a number between 2 and 12.");
            System.out.println("The total of all the bets forms a 'pot'.");
            System.out.println("Then two dices are rolled.");
            System.out.println("If one of the players bet on the result correctly, he or she wins the entire pot.");
            System.out.println("If more than one player bet on that number, the one who bet the most wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If notbody bet on that number, the money remains in the pot for the next round.");
            System.out.println("The game is over if one of the players run out of money.");

        } else {
            System.out.println("Rules for hard mode!");
            System.out.println("Each player places a bet and chooses a number between 1 and 6 for each of the 2 dices.");
            System.out.println("The total of all the bets forms a 'pot'.");
            System.out.println("Then two dices are rolled.");
            System.out.println("If one of the players bet on the result correctly, he or she wins the entire pot.");
            System.out.println("If more than one player bet on that number, the one who bet the most wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If notbody bet on that number, the money remains in the pot for the next round.");
            System.out.println("The game is over if one of the players run out of money.");
        }
    }

    /**
     * playGame method deals with the stuff that happens the in background
     */
    public void playGame() {
        //If easy mode is selected
        if (easy == true) {
            System.out.println("Easy Mode");
            for (int i = 0; i < players.length; i++) {
                playTurn(players[i]);
                pot += players[i].getBetAmount();

                System.out.println("Balance in the pot is: " + pot);
            }

            //Rolling the dice and adding up the outcome
            int dice = (int) (Math.random() * 6) + 1;
            int dice1 = (int) (Math.random() * 6) + 1;

            int outcome = dice + dice1;

            checkWinner(outcome);

            for (int i = 0; i < players.length; i++) {
                if (players[i].getBalance() == 0) {
                    gameOver = true;
                }
            }
        } else {
            //If hard mode is selected

            for (int i = 0; i < players.length; i++) {
                playTurn(players[i]);
                pot += players[i].getBetAmount();
                System.out.println("Balance in the pot is: " + pot);
                pot = 0;
            }

            int dice = (int) (Math.random() * 6) + 1;
            int dice1 = (int) (Math.random() * 6) + 1;
            //calling the checkWinner method
            checkWinner(dice, dice1);

            for (int i = 0; i < players.length; i++) {
                if (players[i].getBalance() == 0) {
                    gameOver = true;
                }
            }
        }
    }

    /**
     * playTurn method deals with the bets and balances of each player
     *
     * @param player
     */
    public void playTurn(Player player) {
        //Entering bet amount and enshuring correct player balance
        System.out.println("You balance is " + player.getBalance());
        int bet = player.getBalance() + 1;
        while (bet > player.getBalance()) {
            System.out.println("Enter bet amount ");
            Scanner input2 = new Scanner(System.in);

            //Checking if the player has enough balance to make a bet
            bet = input2.nextInt();
            if (bet > player.getBalance()) {
                System.out.println("Bet balance exceeds available funds");
            }
        }
        //If player chooses easy mode
        if (easy == true) {
            Scanner input3 = new Scanner(System.in);
            player.setBetAmount(bet);
            player.setBalance(player.getBalance() - bet);

            System.out.println("Enter guess amount ");
            int guess = input3.nextInt();
            player.setGuess(guess);
        } else {
            //If the player chooses hard mode
            //User entering hard guess #1
            Scanner input4 = new Scanner(System.in);
            player.setBetAmount(bet);
            player.setBalance(player.getBalance() - bet);

            System.out.println("Enter guess 1 ");
            int guess1 = input4.nextInt();
            player.setHardGuess1(guess1);

            //User entering hard guess #2
            Scanner input5 = new Scanner(System.in);
            System.out.println("Enter guess 2 ");
            int guess2 = input5.nextInt();
            player.setHardGuess2(guess2);

        }
    }

    /**
     * checkWinner method deals with the outcome of the game (who wins, player
     * balance, etc) for the "hard" setting
     *
     * @param dice
     * @param dice1
     */
    public void checkWinner(int dice, int dice1) {
        System.out.println("Generated outcome: " + dice + " and " + dice1);
        Player[] players = getPlayers();
        int[] playerList = new int[1];
        System.out.println(players.toString());

        //we filter the correct players in the 2 folloing for loops
        for (int i = 0; i < players.length; i++) {
            if (dice == players[i].getHardGuess1() && dice1 == players[i].getHardGuess2()) {
                players[i].setBalance(players[i].getBalance() + pot);
            }
        }
        int outcomeCounter = 0;
        for (int i = 0; i < players.length; i++) {
            if (dice == players[i].getHardGuess1() && dice1 == players[i].getHardGuess2()) {
                playerList[outcomeCounter] = i;
                outcomeCounter += 1;
            }

            int max = 0;
            for (int k = 0; k < playerList.length; k++) {
                if (max < players[playerList[k]].getBetAmount()) {
                    max = players[playerList[k]].getBetAmount();
                }
            }
            ArrayList<Player> myList = new ArrayList<Player>();   //Making an array list to loop through players
            for (int l = 0; l < playerList.length; l++) {
                if (players[playerList[l]].getBetAmount() == max) {
                    myList.add(players[playerList[l]]);
                }
            }

            if (myList.size() == 1) {
                System.out.println(myList.get(0).getName() + " won the game");
            } //If the players guesses the same number and same amount to be bet; the pot size gets devided
            else if (myList.size() > 1) {
                int eachReward = pot / myList.size();
                for (int p = 0; p < myList.size(); p++) {
                    myList.get(p).setBalance(eachReward);
                    System.out.println(myList.get(p).getName() + " won the game");
                }
            }
        }
    }

    /**
     * checkWinner method deals with the outcome of the game (who wins, player
     * balance, etc) for the "easy" setting
     *
     * @param outcome
     *
     */
    public void checkWinner(int outcome) {
        System.out.println("Generated outcome: " + outcome);
        Player[] players = getPlayers();
        int[] playerList = new int[1];
        System.out.println(players.toString());

        for (int i = 0; i < players.length; i++) {    //Looping through all the players in the game
            if (outcome == players[i].getGuess()) {  //If player guess = outcome, then the pot balance gets added to the player balance
                players[i].setBalance(players[i].getBalance() + pot);
            }
        }

        int outcomeCounter = 0;
        for (int i = 0; i < players.length; i++) {
            if (outcome == players[i].getGuess()) {

                playerList[outcomeCounter] = i;
                outcomeCounter += 1;

            }
            int max = 0;
            for (int k = 0; k < playerList.length; k++) {
                if (max < players[playerList[k]].getBetAmount()) {  
                    max = players[playerList[k]].getBetAmount();
                }
            }
            ArrayList<Player> myList = new ArrayList<Player>();   //Making an array list to loop through players
            for (int l = 0; l < playerList.length; l++) {
                if (players[playerList[l]].getBetAmount() == max) {
                    myList.add(players[playerList[l]]);
                }
            }

            if (myList.size() == 1) {
                System.out.println(myList.get(0).getName() + " won the game");  // If these is one 1 winner; pot balance gets added to his balance
            } else if (myList.size() > 1) {
                int eachReward = pot / myList.size();  //Dividing the pot amount by the number of players in the game
                for (int p = 0; p < myList.size(); p++) {
                    myList.get(p).setBalance(eachReward);
                    System.out.println(myList.get(p).getName() + " won the game");
                }

            }

        }
    }
}