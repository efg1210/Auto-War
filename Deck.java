
import java.util.*;

public class Deck<Card> extends ArrayList<Card> {

    public Deck() {
        super();
    }

    //TODO: this is a guess based on what I imagine Nicole to do
    public void sort() {
        Collections.sort(this, (a, b) -> a.compareTo(b));
    }

    public Card deal() {
        int index = (int) (Math.random() * (this.size() + 1));

        return this.remove(index);
    }
}