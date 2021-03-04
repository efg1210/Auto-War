import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    private static final long serialVersionUID = 1L;

    public Deck() {
        super();
    }

    //adds all of the cards to the deck, in order
    public void populate() {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        for (int i = 1; i < 14; i++) {
          for (String suit : suits) {
            this.add(new Card(i, suit));
          }
        }
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public void sort() {
        Collections.sort(this, (a, b) -> a.compareTo(b));
    }

    //deals first card in the deck
    public Card deal() {
        return this.remove(0);
    }
}