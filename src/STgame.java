import java.util.ArrayList;
import java.util.Random;

/**
 * Created by outba on 9/2/2016.
 */
public class STgame {
    private static final int AMOUNT_OF_CARDS_DEALT = 8;
    private int numOfPlayers;
    private STplayer[] players;
    private STdeck deck;

    public STgame (int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        deck = new STdeck();
    }

    public void selectDealer() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(numOfPlayers) + 1;
        System.out.println("Player number #" + randomNumber + " was chosen!");
        }

    public void dealRandomCardsToEachPlayer() {
        players = new STplayer[numOfPlayers];
        for (STplayer player: players) {
            ArrayList<STcard> cards = deck.dealCards(AMOUNT_OF_CARDS_DEALT);
            player.setCards(cards);
        }

    }
}
