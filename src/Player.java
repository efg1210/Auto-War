public class Player {

    private final String name;
    private Deck hand;
    private Deck winnings;

    public Player(String name) {
        this.name = name;
        this.hand = new Deck();
        this.winnings = new Deck();
    }

    //getters

    public Deck getHand() {
        if (this.hand.size() <= 0) {
            replaceHand();
        }
        return this.hand;
    }

    public Deck getWinnings() {
        return this.winnings;
    }

    public String getName() {
        return this.name;
    }

    //setters

    public void setHand(Deck hand) {
        this.hand = hand;
    }

    public void setWinnings(Deck winnings) {
        this.winnings = winnings;
    }

    //other functions

    //adds add cards won in a round to the winnings deck
    public void addToWinnings(Card card) {
        this.winnings.add(card);
    }

    //replaces hand deck with winnings deck
    private void replaceHand() {
        this.winnings.shuffle();
        setHand(getWinnings());
        setWinnings(new Deck());
    }
}