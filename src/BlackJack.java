
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
        System.out.println("\nWelcome to Blackjack, " + getName() + "!");
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

            Deck userHand = new Deck();
            Deck dealerHand = new Deck();

            userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
            userHand.add((Card) deck.remove((int) Math.random() * deck.size()));

            dealerHand.add((Card) deck.remove((int) Math.random() * deck.size()));
            dealerHand.add((Card) deck.remove((int) Math.random() * deck.size()));

            int userPts = calculatePts(userHand);

            update(userHand, dealerHand);

            boolean stay = false;
            if (userPts == 21) {
                stay = true;
            }

            while (!stay && userPts < 21) {
                System.out.println("\nOptions:\n1. Hit\n2. Stay\n3. Sort cards\n");
                System.out.print("Selection: ");
                String answer = in.nextLine();
                if (answer.equals("1") || (answer.toLowerCase()).equals("hit")) {
                    userHand.add((Card) deck.remove((int) Math.random() * deck.size()));
                    userPts = calculatePts(userHand);
                } else if (answer.equals("2") || (answer.toLowerCase()).equals("stay")) {
                    stay = true;
                } else if (answer.equals("3") || (answer.toLowerCase()).equals("sort") || (answer.toLowerCase()).equals("sort cards")) {
                    userHand.sort();
                }
                update(userHand, dealerHand);
            }

            System.out.println("\nYour points: " + userPts);

            int dealerPts = 0;
            if (!(userHand.size() == 2 && userPts == 21) && userPts <= 21) {
                dealerPts = calculatePts(dealerHand);
                int target = 17;
                boolean containsAce = false;

                for (Card card : dealerHand) {
                    if (card.getRank() == 1) {
                        containsAce = true;
                        target = 18;
                    }
                }

                while (dealerPts < target) {
                    dealerHand.add((Card) deck.remove((int) Math.random() * deck.size()));
                    for (Card card : dealerHand) {
                        if (card.getRank() == 1) {
                            containsAce = true;
                            target = 18;
                        }
                    }
                    dealerPts = calculatePts(dealerHand);
                }
                System.out.println("Dealer points: " + dealerPts + "\n");
            }

            if (userHand.size() == 2 && userPts == 21) {
                System.out.println("Blackjack! You win an additional " + (bet * 3 / 2) + " G Dollars! Congrats, " + getName() + "!");
                setBalance(Math.round(bet * 3 / 2) + getBalance());
            } else if (userPts > 21) {
                System.out.println("Darn, you lose! You lost " + bet + " G Dollars. Better luck next time.");
                setBalance(getBalance() - bet);
            } else if (dealerPts > 21) {
                System.out.println("You win an additional " + bet + " G Dollars! Congrats, " + getName() + "!");
                setBalance(bet + getBalance());
            } else if ((21 - userPts) < (21 - dealerPts)) {
                System.out.println("You win an additional " + bet + " G Dollars! Congrats, " + getName() + "!");
                setBalance(bet + getBalance());
            } else if ((21 - userPts) == (21 - dealerPts)) {
                System.out.println("Push! You don't win any additional money, but you do keep your bet. Not bad!");
            } else {
                System.out.println("Darn, you lose! You lost " + bet + " G Dollars. Better luck next time.");
                setBalance(getBalance() - bet);
            }

            String message = (getBalance() == 1) ? "You now have 1 G Dollar." : ("You now have " + getBalance() + " G Dollars.");
            System.out.println(message);
            continuePlaying = response("\nDo you want to continue playing (y/n): ");
        }
    }

    private void update(Deck userHand, Deck dealerHand) {
        System.out.println("\nYour hand: ");
            for (Card card: userHand) {
                System.out.println(card);
            }
            System.out.println("Your points: " + calculatePts(userHand));

            System.out.println("\nDealer's first card: ");
            System.out.println(dealerHand.get(0));
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