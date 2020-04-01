import java.util.*;

public class Main {
    private Scanner in;

    public static void main (String[] args) {
        Utils.populateDeck();
        Deck deck = new Deck<Card>();
        Main main = new Main();
        main.startup();
    }

    public Main() {
        this.in = new Scanner(System.in);
    }

    public void startup() {
        
    }
}