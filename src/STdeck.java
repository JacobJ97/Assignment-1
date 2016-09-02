import java.util.ArrayList;

/**
 * Created by outba on 9/2/2016.
 */
public class STdeck {
    private static final int NUM_OF_CARDS_TOTAL = 60;
    private ArrayList<STcard> cards;

    public STdeck() {
        cards = new ArrayList<STcard>();

        for (int i = 0; i < NUM_OF_CARDS_TOTAL; i++) {
            cards.add(new STcard());
            
        }
    }

    /*public ArrayList<STcard> dealCards(int amountOfCardsDealt) {
        return;
    }*/
}
