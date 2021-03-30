import java.util.*;

public class Main {
    private Scanner in;
    private Player user;
    private Player computer;
    
    private ArrayList<Card> cardsFromUser = new ArrayList<Card>();
    private ArrayList<Card> cardsFromComputer = new ArrayList<Card>();
    
    private int roundCounter = 0;
    public static void main (String[] args) {
        Main main = new Main();
        main.startup();
    }

    //starts Main class
    public Main() {
        in = new Scanner(System.in);
    }

    //starts the game
    public void startup() {
        System.out.print("Number of Players (1 or 2): ");
        int numPlayers = in.nextInt();
        in.nextLine();

        if (numPlayers == 1) {
            System.out.print("\nYour name: ");
            this.user = new Player(in.nextLine());
            this.computer = new Player("Computer");
        } else {
            System.out.print("1st player's name: ");
            this.user = new Player(in.nextLine());
            System.out.print("2nd player's name: ");
            this.computer = new Player(in.nextLine());
        }

        do {
            System.out.print("\nHello " + user.getName() + "! ");
            System.out.print("You will be playing against " + computer.getName());
            System.out.print(" today! Let's play! Are you ready? ");
        } while (!toBoolean(in.nextLine()));
        System.out.println();

        do {
            giveHand();
            
            while (user.getHand().size() >= 1 && computer.getHand().size() >= 1) {
                round();
            }

            boolean userWon = (user.getHand().size() > computer.getHand().size());
            System.out.println("\nWinner: " + (userWon ? user.getName() : computer.getName()));
            System.out.println("Number of rounds: " + roundCounter);

            System.out.print("\nPlay again (y/n)? ");
        } while (toBoolean(in.nextLine()));

        System.out.println("\nBye!");

        this.in.close();
    }

    //converts a message from the user to y/n
    //since users are told to use y/n, all y responses are yes
    private Boolean toBoolean(String response) {
        if (response.contains("y") || response.contains("Y")) {
            return true;
        }
        return false;
    }

    //gives the current cards to the winning player
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
    
    //does the logic of the round
    private void round() {
        roundCounter++;

        cardsFromUser.add(user.getTopCard());
        cardsFromComputer.add(computer.getTopCard());

        if (cardsFromUser.get(cardsFromUser.size() - 1).compareTo(cardsFromComputer.get(cardsFromComputer.size() - 1)) > 0) {
            giveCards(user);
            System.out.println("This round's winner: " + user.getName());
        } else if (cardsFromUser.get(cardsFromUser.size() - 1).compareTo(cardsFromComputer.get(cardsFromComputer.size() - 1)) < 0) {
            giveCards(computer);
            System.out.println("This round's winner: " + computer.getName());
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