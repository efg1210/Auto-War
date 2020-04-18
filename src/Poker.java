package src;
import java.util.ArrayList;
import java.util.Collections;
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
        	
        	System.out.println("\nYour hand: ");
        	update(userHand);
        	
        	int yn = 0;
        	int secondBet = 0;
        	boolean traded = false;
        	while(true) {
        		System.out.println("\nMake a selection:\n1. Place another bet\n2. Trade in\n3. Sort Cards\n4. Payout");
            	yn = in.nextInt();
            	if(yn == 1) {
            		if(secondBet != 0) {
                		System.out.println("\nSecond bet already made. Choose another option.");
            		}		
            		while (secondBet <= 0 || secondBet > 10 || secondBet > balance) {
                      System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                      System.out.print("Place your bet: ");
                      secondBet = in.nextInt();
                      in.nextLine();
            		}
            		balance -= secondBet;
            		bet += secondBet;
            	} else if (yn == 2) {
            		if(!traded) {
            			int num = 1;
                		int index = 0;
                		System.out.println("\nSelect a card to trade:");
                		update(userHand);
                		index = in.nextInt();
                		userHand.remove(index-1);
                    	userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
                    	System.out.println("\nNew Hand:");
                		update(userHand);
                		traded = true;
            		} else {
            			System.out.println("\nOnly one trade is allowed.");
            		}
            	} else if (yn == 3) {
            		Collections.sort(userHand);
            		System.out.println("\nYour hand sorted: ");
            		update(userHand);
            	} else if (yn == 4) {
            		break;
            	}
        	}
       
        	
        	
//        }
    	
    }
    
    private void update(Deck userHand) {
    	int num = 1;
        for (Card card: userHand) {
            System.out.println(num + ". " + card);
            num++;
        }
    }

}