
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Creating Player class
 * @author Abdulla
 */
public class Player {
    //Instance Fields
    private String name;
    private int balance;
    private int betAmount;
    private int guess;
    private int guessHard1;
    private int guessHard2;

    /**
     *Setting initial betting amount to 500
     */
   
    public int INITIAL_BALANCE = 500; // Player balance
   
    //Constructor  

    /**
     *Creating the constructor for the class
     * @param name
     */
    //Constructor for player class
    public Player(String name){
        this.name = name;
        this.balance = INITIAL_BALANCE;
    }
   
    //Mutator Methods

    /**
     *Creating the getName method
     * @return
     */
    public String getName(){  //getName method
        return name;
    }

    /**
     *Creating the getBalance method
     * @return
     */
    public int getBalance(){  //getBalance method
        return balance;
    }

    /**
     *Creating the getBetAmount method
     * @return
     */
    public int getBetAmount(){  //getBetAmount method
        return betAmount;
    }

    /**
     *Creating the getGuess method
     * @return
     */
    public int getGuess(){  //getGuess method
        return guess;
    }

    /**
     *Creating the setBalance method
     * @param balance
     */
    public void setBalance(int balance){   //setBalance method
        this.balance = balance;
    }

    /**
     *Creating the setBetAmount method
     * @param betAmount
     */
    public void setBetAmount(int betAmount){  //setBetAmount method
        this.betAmount = betAmount;
    }

    /**
     *Creating the setGuess method
     * @param guess
     */
    public void setGuess(int guess){  //setGuess method
        this.guess = guess;
    }

    /**
     *Creating the getHardGuess1 method
     * @return
     */
    public int getHardGuess1(){  //getHardGuess1 method
        return guessHard1;
    }

    /**
     *Creating the getHardGuess2 method
     * @return
     */
    public int getHardGuess2(){  //getHardGuess2 method
        return guessHard2;
    }

    /**
     *Creating the setHardGuess1 method
     * @param guessHard1
     */
    public void setHardGuess1 (int guessHard1){  //setHardGuess1 method
        this.guessHard1 =  guessHard1;
    }

    /**
     *Creating the setHardGuess2 method
     * @param guessHard2
     */
    public void setHardGuess2 (int guessHard2){  //setHardGuess2 method
        this.guessHard2 =  guessHard2;
    }


}

