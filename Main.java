import java.util.*;

public class Main {
    private Scanner in;

    public static void main (String[] args) {
        Utils.populateDeck();
        Deck deck = new Deck();
        System.out.println("Original: ");
        deck.populate();
        for (Card c: deck) {
            System.out.println(c);
        }
        System.out.println("length: " + deck.size());

        System.out.println("Shuffled: ");
        deck.shuffle();
        for (Card c: deck) {
            System.out.println(c);
        }
        System.out.println("length: " + deck.size());

        System.out.println("Sorted: ");
        deck.sort();
        for (Card c: deck) {
            System.out.println(c);
        }
        System.out.println("length: " + deck.size());

        Main main = new Main();
        main.startup();
    }

    public Main() {
        this.in = new Scanner(System.in);
    }

    public void startup() {
        
    }
}