import java.util.*;

public class Main {
    private Scanner in;
    private Player user;
    private Player computer;
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

        giveHands();
        
        this.in.close();
    }

    /*
    makes a temp deck of all cards
    shuffles them
    hands them out
    and shuffles each hand
    (it should already be random, though)
    */
    private void giveHands() {
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