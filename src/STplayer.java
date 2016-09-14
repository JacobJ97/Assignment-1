import java.util.ArrayList;

/**
 * Created by outba on 9/2/2016.
 */
public class STplayer {

    private ArrayList<STcard> cards;
    private String playerID;

    public STplayer(String playerID) {
        this.playerID = playerID;
    }

    public void setCards(ArrayList<STcard> cards) {
        this.cards = cards;
    }

    public String toString() {
        return (playerID + "\nYour cards are: \n" +
                cards.subList(0, 1) + "\n" +
                cards.subList(1, 2) + "\n" +
                cards.subList(2, 3) + "\n" +
                cards.subList(3, 4) + "\n" +
                cards.subList(4, 5) + "\n" +
                cards.subList(5, 6) + "\n" +
                cards.subList(6, 7) + "\n" +
                cards.subList(7, 8) + "\n");
    }
}
