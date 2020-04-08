
package blackJack;

import Deck;

import java.util.Scanner;
import java.util.Random;

public class BlackJack {
    
    final Scanner in;
    int balance;
    final String name;
    Deck deck;

    public BlackJack(Scanner in, int balance, String name) {
        this.in = in;
        this.balance = balance;
        this.name = name;
        this.deck = new Deck();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {
        balance = newBalance;
    }

    public String getName() {
        return name;
    }

    public void start() {
        deck.populate();
        deck.shuffle();
        boolean continuePlaying = true;
        while (getBalance() > 0 && continuePlaying) {
            int bet = 0;
            while (bet <= 0 || bet > 10 || bet > balance) {
                System.out.println("Your balance: " + getBalance() + " G Dollars");
                System.out.print("Place your bet: ");
                bet = in.nextInt();
                in.nextLine();
            }

            Deck userHand = new Deck();
            Deck dealerHand = new Deck();

            userHand.add(deck.remove(Math.random() * deck.size()));
            userHand.add(deck.remove(Math.random() * deck.size()));

            dealerHand.add(deck.remove(Math.random() * deck.size()));
            dealerHand.add(deck.remove(Math.random() * deck.size()));

            System.out.println(deck.size());
            
            System.out.print("Do you want to continue playing (y/n): ");
            String answer = in.nextLine();
            switch (answer) {
                case "n": continuePlaying = false; break;
                case "y": continuePlaying = true; break;
                default: continuePlaying = false; break;
            }
        }
    }
}