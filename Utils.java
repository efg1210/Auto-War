
import java.util.*;

public class Utils {

    public static ArrayList<String> populateDeck() {
        ArrayList<String> deck = new ArrayList<String>();

        //Clubs
        for (int i = 1; i < 14; i++) {
            if (i == 1) {
                deck.add("AC");
            } else if (i == 11) {
                deck.add("JC");
            } else if (i == 12) {
                deck.add("QC");
            } else if (i == 13) {
                deck.add("KC");
            } else {
                deck.add(i + "C");
            }
        }

        //Diamonds
        for (int i = 1; i < 14; i++) {
            if (i == 1) {
                deck.add("AD");
            } else if (i == 11) {
                deck.add("JD");
            } else if (i == 12) {
                deck.add("QD");
            } else if (i == 13) {
                deck.add("KD");
            } else {
                deck.add(i + "D");
            }
        }

        //Hearts
        for (int i = 1; i < 14; i++) {
            if (i == 1) {
                deck.add("AH");
            } else if (i == 11) {
                deck.add("JH");
            } else if (i == 12) {
                deck.add("QH");
            } else if (i == 13) {
                deck.add("KH");
            } else {
                deck.add(i + "H");
            }
        }

        //Spades
        for (int i = 1; i < 14; i++) {
            if (i == 1) {
                deck.add("AS");
            } else if (i == 11) {
                deck.add("JS");
            } else if (i == 12) {
                deck.add("QS");
            } else if (i == 13) {
                deck.add("KS");
            } else {
                deck.add(i + "S");
            }
        }
        
        return deck;
    }
}