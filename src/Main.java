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
    	int balance;
        String name = welcomeName();
        String bestTeacher = "ryan wilson or ryan john wilson or mr. wilson or mr wilson";
        if(bestTeacher.contains(name.toLowerCase())) {
        	System.out.println("\nWell hello Mr. Wilson! You found the easter egg and you automatically get 1000 G Dollars! Happy Playing.");
        	balance = 1000;
        } else {
        	balance = balance(0);
        }
        
        boolean on = true;
        while(on) {
            System.out.println("Your balance: " + balance + " G Dollars\n");
            System.out.println("Here are your options!");
            System.out.println("1. Convert more money to G Dollars");
            System.out.println("2. Play blackjack");
            System.out.println("3. Play poker");
            System.out.println("4. Convert G Dollars to money and end session");
            System.out.println("5. Turn game off");
            System.out.print("\nSelection: ");
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1: balance += balance(balance); break;
                case 2:
                    BlackJack bj = new BlackJack(in, balance, name);
                    balance = bj.start();
                    break;
                case 3:
                    Poker poker = new Poker(in, balance, name);
                    balance = poker.start();
                    //System.out.println("Poker (please delete this line, line 41)");
                    break;
                case 4:
                    balance = 0;
                    System.out.println("G Dollars converted to money");
                    System.out.println("Have a good day " + name + "!");
                    name = "";
                    name = welcomeName();
                    balance = balance(balance);
                    break;
                case 5:
                    System.out.println("Goodbye " + name + "!");
                    on = false;
                    break;
                default:
                    System.out.println("Please enter again.");
            }
        }
    }

    private String welcomeName() {
        System.out.println("\nWelcome to G Games!");
        System.out.print("Your name: ");
        return in.nextLine();
    }

    private int balance(int currentBalance) {
        if (currentBalance < 1000) {
            System.out.print("Amount of dollars to convert to G Dollars: ");
            int deposit = in.nextInt();
            in.nextLine();
            while (deposit < 0 || deposit > 1000) {
                System.out.println("Invalid deposit. Try again.");
                System.out.print("Amount of dollars to convert to G Dollars: ");
                deposit = in.nextInt();
                in.nextLine();
            }
            return deposit;
        } else {
            System.out.println("You already have " + currentBalance + " G Dollars. You cannot add more");
            return 0;
        }        
    }
}