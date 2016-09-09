import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by outba on 9/2/2016.
 */
public class STdeck {
    private static final int NUM_OF_CARDS_TOTAL = 60;
    private ArrayList<STcard> cards;

    public STdeck() {
        cards = new ArrayList<>();

        for (int i = 0; i < NUM_OF_CARDS_TOTAL; i++) {
            cards.add(new STcard(i));
        }
        Collections.shuffle(cards);
    }

    public ArrayList<STcard> dealCards(int amountOfCardsDealt) {
        for (int i = 0; i < amountOfCardsDealt; i++) {

        }
        return null;
    }
}
