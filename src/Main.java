import java.util.*;

public class Main {
    private Scanner in;
    private Player user;
    private Player computer;
    private ArrayList<Card> cardsFromUser = new ArrayList<Card>();
    private ArrayList<Card> cardsFromComputer = new ArrayList<Card>();
    public static void main (String[] args) {
        Main main = new Main();
        main.startup();
    }

    public Main() {
        in = new Scanner(System.in);
    }

    public void startup() {
        System.out.print("Your name: ");
        this.user = new Player(in.nextLine());
        this.computer = new Player("Computer");

        System.out.print("Hello " + user.getName() + "! ");
        System.out.print("You will be playing against " + computer.getName());
        System.out.println(" today! Let's play!");

        giveHand();
        
        while (user.getHand().size() >= 1 && computer.getHand().size() >= 1) {
            round();
        }

        boolean userWon = (user.getHand().size() > computer.getHand().size());
        System.out.println("\nWinner: " + (userWon ? user.getName() : computer.getName()));

        this.in.close();
    }

    private void giveCards(Player p) {
        for (Card c: cardsFromUser) {
            p.addToWinnings(c);
        }
        cardsFromUser = new ArrayList<Card>();
        for (Card c: cardsFromComputer) {
            p.addToWinnings(c);
        }
        cardsFromComputer = new ArrayList<Card>();
    }
    
    private void round() {
        cardsFromUser.add(user.getTopCard());
        cardsFromComputer.add(computer.getTopCard());

        System.out.println("\n\nUser card: " + cardsFromUser.get(0));
        System.out.println("Computer card: " + cardsFromComputer.get(0));

        if (cardsFromUser.get(cardsFromUser.size() - 1).compareTo(cardsFromComputer.get(cardsFromComputer.size() - 1)) > 0) {
            giveCards(user);
        } else if (cardsFromUser.get(cardsFromUser.size() - 1).compareTo(cardsFromComputer.get(cardsFromComputer.size() - 1)) < 0) {
            giveCards(computer);
        } else {
            if (cardsFromUser.size() >= 4 && cardsFromComputer.size() >= 4) {
                for (int i = 0; i < 3; i++) {
                    cardsFromUser.add(user.getTopCard());
                    cardsFromComputer.add(computer.getTopCard());
                    round();
                }
            } else {
                if (cardsFromUser.size() < cardsFromComputer.size()) {
                    for (int i = 0; i < (cardsFromUser.size() - 1); i++) {
                        cardsFromUser.add(user.getTopCard());
                        cardsFromComputer.add(computer.getTopCard());
                        round();
                    }
                } else {
                    for (int i = 0; i < (cardsFromComputer.size() - 1); i++) {
                        cardsFromUser.add(user.getTopCard());
                        cardsFromComputer.add(computer.getTopCard());
                        round();
                    }
                }
            }
        }

        System.out.println("\nUser winnings: " + user.getWinnings());
        System.out.println("Computer winnings: " + computer.getWinnings());

        System.out.println("\nUser hand length: " + user.getHand().size());
        System.out.println("Computer hand length: " + computer.getHand().size());
    }

    /*
    makes a temp deck of all cards
    shuffles them
    hands them out
    and shuffles each hand
    (it should already be random, though)
    */
    private void giveHand() {
        Deck deck = new Deck();
        deck.populate();
        deck.shuffle();
        int length = deck.size();
        for (int i = 0; i < length; i+=2) {
            user.addToHand(deck.deal());
            computer.addToHand(deck.deal());
        }
        user.shuffleHand();
        computer.shuffleHand();
    }
}