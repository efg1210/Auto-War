
public class Card {
    String rank;
    String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(int rank, String suit) {
        this.rank = Integer.toString(rank);
        this.suit = suit;
    }
}