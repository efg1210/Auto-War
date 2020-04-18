package src;
import java.util.Scanner;

public class Poker {
	final private Scanner in;
	private int balance;
    final private String name;
    private Deck deck;
    
    public Poker(Scanner in, int balance, String name) {
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