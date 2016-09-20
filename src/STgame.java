import java.util.ArrayList;
import java.util.Random;

/**
 * Created by outba on 9/2/2016.
 */
public class STgame {
    private static final int AMOUNT_OF_CARDS_DEALT = 8;
    public static final int THREE_PLAYERS = 3;
    public static final int FOUR_PLAYERS = 4;
    public static final int FIVE_PLAYERS = 5;
    private int numOfPlayers;
    private STplayer[] players;
    private STdeck deck;
    private String humanPlayer;
    private int humanPlayerID;
    private int computerPlayer1ID;
    private int computerPlayer2ID;
    private int computerPlayer3ID;
    private int computerPlayer4ID;

    public STgame (int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        deck = new STdeck();
    }

    public void selectDealer() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(numOfPlayers);
        System.out.println("Player number #" + randomNumber + " was chosen!");
        }

    public void dealRandomCardsToEachPlayer() {
        players = new STplayer[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new STplayer("ID = " + i);
        }

        for (STplayer player: players) {
            ArrayList<STcard> cards = deck.dealCards(AMOUNT_OF_CARDS_DEALT);
            player.setCards(cards);
        }

    }

    public void assignHumanPlayerID() {
        humanPlayerID = 0;
    }

    public void assignComputerPlayersID() {
        switch (numOfPlayers) {
            case THREE_PLAYERS: {
                computerPlayer1ID = 1;
                computerPlayer2ID = 2;
                break;
            }
            
            case FOUR_PLAYERS: {
                computerPlayer1ID = 1;
                computerPlayer2ID = 2;
                computerPlayer3ID = 3;
                break;
            }
            
            case FIVE_PLAYERS: {
                computerPlayer1ID = 1;
                computerPlayer2ID = 2;
                computerPlayer3ID = 3;
                computerPlayer4ID = 4;
                break;
            }
        }
    }

    public STplayer getHumanPlayer() {
        return players[humanPlayerID];
    }

    public STplayer getComputerPlayer1() {
        return players[computerPlayer1ID];
    }

    public STplayer getComputerPlayer2() {
        return players[computerPlayer2ID];
    }

    public STplayer getComputerPlayer3() {
        if (numOfPlayers == FOUR_PLAYERS || numOfPlayers == FIVE_PLAYERS) {
            return players[computerPlayer3ID];
        }
        return null;
    }

    public STplayer getComputerPlayer4() {
        if (numOfPlayers == FIVE_PLAYERS) {
            return players[computerPlayer4ID];
        }
        return null;
    }

    public void startPlayingGame() {
        boolean gameIsRunning = true;
        while (gameIsRunning) {
            gameIsRunning = false;
        }
    }
}
