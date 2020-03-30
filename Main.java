import java.util.Scanner;

public class Main {
    private Scanner in;


    public static void main (String[] args) {
        startup();
    }

    public Main() {
        this.in = new Scanner(System.in);
    }

    public static void startup() {
    	Poker.start();
    }
}