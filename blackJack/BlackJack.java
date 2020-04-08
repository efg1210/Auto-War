
package blackJack;

import java.util.Scanner;

public class BlackJack {
    
    final Scanner in;
    int balance;
    final String name;

    public BlackJack(Scanner in, int balance, String name) {
        this.in = in;
        this.balance = balance;
        this.name = name;
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
        boolean continuePlaying = true;
        while (getBalance() > 0 && continuePlaying) {
            int bet;
            System.out.println("Your balance: " + getBalance() + " G Dollars");
            System.out.print("Place your bet: ");
            bet = in.nextInt();
            in.nextLine();
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