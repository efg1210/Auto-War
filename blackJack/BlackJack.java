
package blackJack;

import deck.Card;
import deck.Deck;

public class BlackJack {
    
    Scanner in;
    int balance;

    public BlackJack(Scanner in, int balance) {
        this.in = in;
        this.balance = balance;
    }

    public start() {
        boolean continue = true;
        while (balance > 0 && continue) {
            int bet;
            System.out.println("Your balance: " + balance + " G Dollars");
            System.out.print("Place your bet: ");
            bet = in.nextInt();
            in.nextLine();
        }
    }
}