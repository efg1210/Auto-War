
package poker;

import deck.Card;
import deck.Deck;

import java.util.Scanner;

public class Poker {
    Scanner in;
    int balance;
    
    public Poker(Scanner in, int balance) {
    	this.in = in;
    	this.balance = balance;
    }
    
    public static void start() {
    	Scanner in = new Scanner(System.in);
    	System.out.print("Poker:\nPlace a bet:");
    	int betInt = in.nextInt();
    	if(betInt < 1) {
    		System.out.println("Minimum bet is 1 chip");
    	} else if (betInt > 10) {
    		System.out.println("Maximum bet is 10 chips");
    	}
    }
}