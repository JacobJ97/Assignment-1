import java.util.ArrayList;

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
    }

    public void selectDealer() {
        double randomNumber = Math.random();
        System.out.println(randomNumber);
    }

    public void dealRandomCardsToEachPlayer() {
        players = new STplayer[numOfPlayers];
        for (STplayer player: players) {
            //ArrayList<STcard> card = deck.dealCards(AMOUNT_OF_CARDS_DEALT);
            //player.setCards();
        }

    }
}
