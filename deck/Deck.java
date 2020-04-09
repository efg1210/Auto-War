
package deck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    private static final long serialVersionUID = 1L;

    public Deck() {
        super();
        //populate();
    }

    public void populate() {
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades"};
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

    public Card deal() {
        int index = (int) (Math.random() * (this.size() + 1));
        return this.remove(index);
    }
}