import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by outba on 9/2/2016.
 */
public class STdeck {
    private static final int NUM_OF_CARDS_TOTAL = 60;
    private ArrayList<STcard> cards;

    public STdeck() {
        cards = new ArrayList<>();

        for (int i = 1; i < NUM_OF_CARDS_TOTAL + 1; i++) {
            cards.add(new STcard(i));
            STcard.x++;
            STcard.y++;
        }
        Collections.shuffle(cards);
    }

    public ArrayList<STcard> dealCards(int amountOfCardsDealt) {
        ArrayList<STcard> cardReceived = new ArrayList<STcard>();
        for (int i = 0; i < amountOfCardsDealt; i++) {
            int idNum = i;
            STcard chosenCard = cards.remove(idNum);
            cardReceived.add(chosenCard);
            System.out.println("Card received = " + chosenCard);
        }
        return cardReceived;
    }
}
