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

        for (int i = 0; i < NUM_OF_CARDS_TOTAL; i++) {
            cards.add(new STcard(i));
            STcard.x++;
        }
        Collections.shuffle(cards);
    }

    public ArrayList<STcard> dealCards(int amountOfCardsDealt) {
        ArrayList<STcard> cardReceived = new ArrayList<STcard>();
        for (int i = 0; i < amountOfCardsDealt; i++) {
            int idRandom = new Random().nextInt(cards.size());
            STcard chosenCard = cards.remove(idRandom);
            cardReceived.add(chosenCard);
            System.out.println("Card received = " + chosenCard);
        }
        return cardReceived;
    }
}
