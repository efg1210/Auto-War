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
//        while (getBalance() > 0 && continuePlaying) {
        	int bet = 0;
            while (bet <= 0 || bet > 10 || bet > balance) {
                System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                System.out.print("Place your bet: ");
                bet = in.nextInt();
                in.nextLine();
            }
            balance -= bet;
            
        	Deck userHand = new Deck();
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	
//        	int userPts = calculatePts(userHand);
        	
        	update(userHand);
        	
        	int yn = 0;
        	int secondBet = 0;
        	System.out.println("\nPlace another bet?\n1. Yes\n2. No");
        	yn = in.nextInt();
        	System.out.println(yn);
        	if(yn == 1) {
        		while (secondBet <= 0 || secondBet > 10 || secondBet > balance) {
                    System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                    System.out.print("Place your bet: ");
                    secondBet = in.nextInt();
                    in.nextLine();
                }
        		balance -= secondBet;
        		bet += secondBet;
        	}
        	System.out.println("bet: " + bet);
        	System.out.println("second bet: " + secondBet);
        	System.out.println("balance: " + balance);
        	
//        }
    	
    }
    
    private void update(Deck userHand) {
        System.out.println("\nYour hand: ");
            for (Card card: userHand) {
                System.out.println(card);
            }
//            System.out.println("Your points: " + calculatePts(userHand));
    }
}