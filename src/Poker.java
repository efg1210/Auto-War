package src;
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
        while (getBalance() > 0 && continuePlaying) {
        	int bet = 0;
            while (bet <= 0 || bet > 10 || bet > balance) {
                System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                System.out.print("Place your bet: ");
                bet = in.nextInt();
                in.nextLine();
            }
            balance -= bet;
            
        	Deck hand = new Deck();
        	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
        	
        	System.out.println("\nYour hand: ");
        	update(hand);
        	
        	int yn = 0;
        	int secondBet = 0;
        	boolean traded = false;
        	boolean sorted = false;
        	while(true) {
        		System.out.println("\nMake a selection:\n1. Place a second bet\n2. Trade in\n3. Sort Cards\n4. Payout");
            	yn = in.nextInt();
            	if(yn == 1) {
            		if(secondBet != 0) {
                		System.out.println("\nSecond bet already made. Choose another option.");
            		}
            		if (balance == 0) {
            			System.out.println("\nYour balance: " + getBalance() + " G Dollars. \nInsufficient funds");
            		} else {
            			while (secondBet <= 0 || secondBet > 10 || secondBet > balance) {
                            System.out.println("\nYour balance: " + getBalance() + " G Dollars");
                            System.out.print("Place your bet: ");
                            secondBet = in.nextInt();
                            in.nextLine();
                  		}
                  		balance -= secondBet;
                  		bet += secondBet;
            		}
            	} else if (yn == 2) {
            		if(!traded) {
                		int index = 0;
                		System.out.println("\nSelect a card to trade:");
                		update(hand);
                		index = in.nextInt();
                		hand.remove(index-1);
                    	hand.add((Card) deck.remove((int) Math.random() * deck.size()));
                    	System.out.println("\nNew Hand:");
                		update(hand);
                		traded = true;
                		sorted = false;
            		} else {System.out.println("\nOnly one trade is allowed.");}
            	} else if (yn == 3) {
            		sorted = true;
            		Collections.sort(hand);
            		System.out.println("\nYour hand sorted: ");
            		update(hand);
            	} else if (yn == 4) {
            		if(!sorted) {
            			System.out.println("\nMust sort hand before evaluating payout.");
            		} else {
            			if(payout(hand, bet) == 1) {
            				if(getBalance() == 0) {
            					System.out.println("\nInsufficient funds. Thank you for playing poker.\n");
            				}
            				break;
            			} else if (payout(hand, bet) == 2) {
            				continuePlaying = false;
            				break;
            			} else {
            				System.out.println("Please select 1 or 2.");
            			}
            		}
            	}
        	}
        }
        
    }
    
	public int payout(Deck hand, int bet) {
		int yn = 0;
		if (this.royalFlush(hand) == 1) {
			setBalance(balance+(bet*250));
			System.out.println("\nYou have a royal flush! This pays 250-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.straightFlush(hand) == 1) {
			setBalance(balance+(bet*100));
			System.out.println("\nYou have a straight flush! This pays 100-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.fourOfaKind(hand) == 1) {
			setBalance(balance+(bet*25));
			System.out.println("\nYou have four of a kind! This pays 25-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.fullHouse(hand) == 1) {
			setBalance(balance+(bet*10));
			System.out.println("\nYou have a full house! This pays 10-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.flush(hand) == 1) {
			setBalance(balance+(bet*5));
			System.out.println("\nYou have a flush! This pays 5-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n. No");
			yn = in.nextInt();
		} else if (this.straight(hand) == 1) {
			setBalance(balance+(bet*3));
			System.out.println("\nYou have a straight! This pays 3-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.triple(hand) == 1) {
			setBalance(balance+(bet*2));
			System.out.println("\nYou have a triple! This pays 2-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.twoPairs(hand) == 1) {
			setBalance(balance+bet);
			System.out.println("\nYou have two pairs! This pays 1-to-1. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else if (this.pair(hand) == 1) {
			setBalance(balance+bet);
			System.out.println("balance: "+balance);
			System.out.println("bet: "+bet);
			System.out.println("\nYou have a pair! You get your bet back. The funds have been transferred to your balance. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		} else {
			setBalance(balance);
			System.out.println("\nYou have no hands. You loose your bet. Current Balance: " + getBalance() + " G Dollars.\nAnother round?\n1. Yes\n2. No");
			yn = in.nextInt();
		}
		return yn;
	}


	public int royalFlush(Deck hand) {
		if (hand.get(0).getRank()== 1 && hand.get(1).getRank() == 10 && hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12 && hand.get(4).getRank() == 13) {
			return 1;
		} else {
			return 0;
		}
	}


	public int straightFlush(Deck hand) {
		for (int i = 1; i < 5; i++) {
			if (hand.get(0).getSuit() != hand.get(i).getSuit()) {
				return 0;
			}
		}
		for (int x = 1; x < 5; x++) {
			if (hand.get(x-1).getRank() != (hand.get(x).getRank() - 1)) {
				return 0;
			}				
		}
		return 1;		
	}


	public int fourOfaKind(Deck hand) {
		if (hand.get(0).getRank() != hand.get(3).getRank() && hand.get(1).getRank() != hand.get(4).getRank()) {
			return 0;
		} else {
			return 1;
		}
	}


	public int fullHouse(Deck hand) {
		int full = 0;
		for (int i = 1; i < 5; i++) {
			if (hand.get(i-1).getRank() == hand.get(i).getRank()) {
				full++;
			}
		}
		if (full == 3) {
			return 1;
		} else {
			return 0;
		}
	}


	public int flush(Deck hand) {
		for (int i = 1; i < 5; i++) {
			if (hand.get(0).getSuit() != hand.get(i).getSuit()) {
				return 0;
			}
		}
		return 1;
	}
		
	public int straight(Deck hand) {
		for (int i = 1; i < 5; i++) {
			if (hand.get(i-1).getRank() != (hand.get(i).getRank() - 1)) {
				return 0;
			}	
		}
		return 1;
	}


	public int triple(Deck hand) {
		if (hand.get(0).getRank() == hand.get(2).getRank() || hand.get(1).getRank() == hand.get(3).getRank() || hand.get(2).getRank() == hand.get(4).getRank()) {
			return 1;
		}
		return 0;
	}


	public int twoPairs(Deck hand) {
		int pair = 0;
		for(int i = 1; i < 5; i++) {
			if (hand.get(i-1).getRank() == hand.get(i).getRank()) {
				pair++;
			}
		}
		if (pair == 2) {
			return 1;
		} else {
			return 0;
		}
	}


	public int pair(Deck hand) {
		int pair = 0;
		for(int i = 1; i < 5; i++) {
			if (hand.get(i-1).getRank() == hand.get(i).getRank()) {
				pair++;
			}
		}
		if (pair == 1) {
			return 1;
		} else {
			return 0;
		}
	}
     
    private void update(Deck hand) {
    	int num = 1;
        for (Card card: hand) {
            System.out.println(num + ". " + card);
            num++;
        }
    }
}