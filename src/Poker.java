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
    
    public void start() {
    	System.out.println("\nWelcome to Poker, " + getName() + "!");
        deck.populate();
        deck.shuffle();
        boolean continuePlaying = true;
        while (getBalance() > 0 && continuePlaying) {
        	int bet = 0;
        	while (bet <= 0 || bet >= 10 || bet > balance) {
        		System.out.println(bet);
                System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                System.out.print("Place your bet: ");
                bet = in.nextInt();
                in.nextLine();
            }
        	Deck userHand = new Deck();
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        }
    	
    }
}