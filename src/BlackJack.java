
import java.util.Scanner;
import java.util.Random;

public class BlackJack {
    
    final private Scanner in;
    private int balance;
    final private String name;
    private Deck deck;

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

            userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
            userHand.add((Card) deck.remove((int) Math.random() * deck.size()));

            dealerHand.add((Card) deck.remove((int) Math.random() * deck.size()));
            dealerHand.add((Card) deck.remove((int) Math.random() * deck.size()));

            int userPts = 0;
            int dealerPts = 0;

            System.out.println("\nYour hand: ");
            for (Card card: userHand) {
                System.out.println(card);
            }
            //userPts = calculatePts(userHand);
            System.out.println("Your points: " + calculatePts(userHand));

            System.out.println("\nDealer's first card: ");
            System.out.println(dealerHand.get(0));

            boolean stay = false;
            while (!stay) {
                System.out.println("Options:\n1. Hit\n2. Stay\n 3. Sort cards");
                String answer = in.nextLine();
                if (answer.equals("1") || (answer.toLowerCase()).equals("hit")) {
                    userPts = calculatePts(userHand);
                } else if (answer.equals("2") || (answer.toLowerCase()).equals("stay")) {
                    stay = true;
                } else if (answer.equals("3") || (answer.toLowerCase()).equals("sort") || (answer.toLowerCase()).equals("sort cards")) {

                }
            }

            continuePlaying = response("Do you want to continue playing (y/n): ");
            
        }
    }

    private void update(Deck userHand, Deck dealerHand) {

    }

    private boolean response(String message) {
        System.out.print(message);
        String answer = in.nextLine();
            switch (answer) {
                case "y": return true;
                case "n": return false;
                default: return false;
            }
    }

    private int calculatePts(Deck hand) {
        int total = 0;
        int aceCount = 0;
        for (Card card: hand) {
            if (card.getRank() == 1) {
                aceCount++;
            } else if (card.getRank() >= 10) {
                total += 10;
            } else {
                total += card.getRank();
            }
        }

        for (int i = 0; i < aceCount; i++) {
            total += (total + 11 > 21) ? 1 : 11;
        }

        return total;
    }
}