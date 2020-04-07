
public class Card implements Comparable<Card> {
    private final String rank;
    private final String suit;

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
    public int compareTo(Card c) {
        if (this.getRank().compareTo(c.getRank()) < 0) {
            return -1;
        } else if (this.getRank().compareTo(c.getRank()) > 0) {
            return 1;
        } else {
            if (this.getSuit().compareTo(c.getSuit()) < 0) {
                return -1;
            } else if (this.getSuit().compareTo(c.getSuit()) > 0) {
                return 1;
            }
        }
        return 0;
    }
}