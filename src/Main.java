package src;
import java.util.*;

public class Main {
    private Scanner in;

    public static void main (String[] args) {
        Main main = new Main();
        main.startup();
    }

    public Main() {
        this.in = new Scanner(System.in);
    }

    public void startup() {      
//    	System.out.println("Choose a game to play\n1. Poker\n2. BlackJack");
//    	int game = in.nextInt();
//    	if(game == 1) {
    		Poker poker = new Poker(in, 10, "Nicole");
            poker.start();
//    	} else if (game == 2) {
//    		BlackJack bj = new BlackJack(in, 10, "Emily");
//            bj.start();
//    	}        
        
    }
}