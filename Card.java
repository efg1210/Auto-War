
public class Card implements Comparable<Card> {
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

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    @Override
    public int compareTo(Card o) {
        return this.getRank().compareTo(o.getRank());
    }
}