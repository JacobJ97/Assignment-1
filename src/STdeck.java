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

        for (int i = 1; i < NUM_OF_CARDS_TOTAL + 1; i++) {
            cards.add(new STcard(i));
            STcard.x++;
            STcard.y++;
        }
        Collections.shuffle(cards);
    }

    public ArrayList<STcard> dealCards(int amountOfCardsDealt) {
        ArrayList<STcard> cardDealt = new ArrayList<>();
        for (int i = 0; i < amountOfCardsDealt; i++) {
            int idNum = i;
            STcard chosenCard = cards.remove(idNum);
            cardDealt.add(chosenCard);
            String chosenCardString = chosenCard.toString();
            //System.out.println(chosenCardString);
            String chosenCardStringSplitUp[] = chosenCardString.split("[|]");
            //System.out.println(chosenCardStringSplitUp[0]);
            System.out.println("Card dealt: " + chosenCardStringSplitUp[0]);
        }
        return cardDealt;
    }

    public ArrayList<STcard> pickUpCard(int numOfCardsInDeck) {
        ArrayList<STcard> cardPickedUp = new ArrayList<>();
        int idNum = numOfCardsInDeck;
        STcard chosenCard = cards.remove(idNum);
        cardPickedUp.add(chosenCard);
        return cardPickedUp;
    }

    public void throwOutCard(String[] cardThrown) {
        ArrayList<STdeck> thrownOutCard = new ArrayList<>();

    }
}
