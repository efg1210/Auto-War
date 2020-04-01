
import java.util.*;

public class Deck extends ArrayList<E> {

    public Deck() {
        super();
    }

    //TODO: this is a guess based on what I imagine Nicole to do
    public sort() {
        Collection.sort(this, (a, b) -> a.getSuit().compareTo(b.getSuit()));
    }

    public Card deal() {
        int index = (Math.random() * (this.size() + 1));

        return this.remove(index);
    }
}