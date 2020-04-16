
public class Card implements Comparable<Card> {
    private final int rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = Integer.parseInt(rank);
        this.suit = suit;
    }

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    @Override
    public int compareTo(Card c) {
        if (this.getRank() < c.getRank()) {
            return -1;
        } else if (this.getRank() > c.getRank()) {
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

    @Override
    public String toString() {
        switch (getRank()) {
            case 1: return "Ace of " + getSuit();
            case 2: return "Two of " + getSuit();
            case 3: return "Three of " + getSuit();
            case 4: return "Four of " + getSuit();
            case 5: return "Five of " + getSuit();
            case 6: return "Six of " + getSuit();
            case 7: return "Seven of " + getSuit();
            case 8: return "Eight of " + getSuit();
            case 9: return "Nine of " + getSuit();
            case 10: return "Ten of " + getSuit();
            case 11: return "Jack of " + getSuit();
            case 12: return "Queen of " + getSuit();
            case 13: return "King of " + getSuit();
        }
        return null;
    }
}