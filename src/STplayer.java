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
                cards);
    }
}
