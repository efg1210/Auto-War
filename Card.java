
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

    @Override
    public String toString() {
        String message = "";
        switch (getRank()) {
            case "2":
                message += "Two of"; break;
            case "3":
                message += "Three of"; break;
            case "4":
                message += "Four of"; break;
            case "5":
                message += "Five of"; break;
            case "6":
                message += "Six of"; break;
            case "7":
                message += "Seven of"; break;
            case "8":
                message += "Eight of"; break;
            case "9":
                message += "Nine of"; break;
            case "T":
                message += "Ten of"; break;
            case "J":
                message += "Jack of"; break;
            case "Q":
                message += "Queen of"; break;
            case "K":
                message += "King of"; break;
            case "A":
                message += "Ace of"; break;
            }

        if (getSuit().equals("C")) {
            message += " Clubs";
        } else if (getSuit().equals("D")) {
            message += " Diamonds";
        } else if (getSuit().equals("H")) {
            message += " Hearts";
        } else if (getSuit().equals("S")) {
            message += " Spades";
        }
        
        return message;
    }
}