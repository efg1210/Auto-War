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
        BlackJack bj = new BlackJack(in, 10, "Emily");
        bj.start();
    }
}