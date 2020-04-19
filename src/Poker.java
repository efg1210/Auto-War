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
            		} else {System.out.println("\nOnly one trade is allowed.");}
            	} else if (yn == 3) {
            		Collections.sort(userHand);
            		System.out.println("\nYour hand sorted: ");
            		update(userHand);
            	} else if (yn == 4) {
            		evaluate(userHand);
            		break;
            	}
        	}
//        }
    	
    }
    
	public void evaluate(Deck userHand) {
		if (this.royalFlush(userHand) == 1) {
			System.out.println("You have a royal flush!");
		} else if (this.straightFlush(userHand) == 1) {
			System.out.println("You have a straight flush!");
		} else if (this.fourOfaKind(userHand) == 1) {
			System.out.println("You have four of a kind!");
		} else if (this.fullHouse(userHand) == 1) {
			System.out.println("You have a full house!");
		} else if (this.flush(userHand) == 1) {
			System.out.println("You have a flush!");
		} else if (this.straight(userHand) == 1) {
			System.out.println("You have a straight!");
		} else if (this.triple(userHand) == 1) {
			System.out.println("You have a triple!");
		} else if (this.twoPairs(userHand) == 1) {
			System.out.println("You have two pairs!");
		} else if (this.pair(userHand) == 1) {
			System.out.println("You have a pair!");
		} else {
			System.out.println("You have no hands.");
		}
	}


	public int royalFlush(Deck userHand) {
		if (userHand.get(0).getRank()== 1 && userHand.get(1).getRank() == 10 && userHand.get(2).getRank() == 11 && userHand.get(3).getRank() == 12 && userHand.get(4).getRank() == 13) {
			return 1;
		} else {
			return 0;
		}
	}


	public int straightFlush(Deck userHand) {
		for (int i = 1; i < 5; i++) {
			if (userHand.get(0).getSuit() != userHand.get(i).getSuit()) {
				return 0;
			}
		}
		for (int x = 1; x < 5; x++) {
			if (userHand.get(x-1).getRank() != (userHand.get(x).getRank() - 1)) {
				return 0;
			}				
		}
		return 1;		
	}


	public int fourOfaKind(Deck userHand) {
		if (userHand.get(0).getRank() != userHand.get(3).getRank() && userHand.get(1).getRank() != userHand.get(4).getRank()) {
			return 0;
		} else {
			return 1;
		}
	}


	public int fullHouse(Deck userHand) {
		int full = 0;
		for (int i = 1; i < 5; i++) {
			if (userHand.get(i-1).getRank() == userHand.get(i).getRank()) {
				full++;
			}
		}
		if (full == 3) {
			return 1;
		} else {
			return 0;
		}
	}


	public int flush(Deck userHand) {
		for (int i = 1; i < 5; i++) {
			if (userHand.get(0).getSuit() != userHand.get(i).getSuit()) {
				return 0;
			}
		}
		return 1;
	}
		
	public int straight(Deck userHand) {
		for (int i = 1; i < 5; i++) {
			if (userHand.get(i-1).getRank() != (userHand.get(i).getRank() - 1)) {
				return 0;
			}	
		}
		return 1;
	}


	public int triple(Deck userHand) {
		if (userHand.get(0).getRank() == userHand.get(2).getRank() || userHand.get(2).getRank() == userHand.get(4).getRank()) {
			return 1;
		}
		return 0;
	}


	public int twoPairs(Deck userHand) {
		int pair = 0;
		for(int i = 1; i < 5; i++) {
			if (userHand.get(i-1).getRank() == userHand.get(i).getRank()) {
				pair++;
			}
		}
		if (pair == 2) {
			return 1;
		} else {
			return 0;
		}
	}


	public int pair(Deck userHand) {
		int pair = 0;
		for(int i = 1; i < 5; i++) {
			if (userHand.get(i-1).getRank() == userHand.get(i).getRank()) {
				pair++;
			}
		}
		if (pair == 1) {
			return 1;
		} else {
			return 0;
		}
	}
     
    private void update(Deck userHand) {
    	int num = 1;
        for (Card card: userHand) {
            System.out.println(num + ". " + card);
            num++;
        }
    }
}