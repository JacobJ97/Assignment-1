import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by outba on 9/2/2016.
 */
public class STgame {
    private static final int AMOUNT_OF_CARDS_DEALT = 8;
    public static final int THREE_PLAYERS = 3;
    public static final int FOUR_PLAYERS = 4;
    public static final int FIVE_PLAYERS = 5;
    private int randomNumber;
    private int numOfPlayers;
    private STplayer[] players;
    private STdeck deck;
    private String humanPlayer;
    private int humanPlayerID;
    private int computerPlayer1ID;
    private int computerPlayer2ID;
    private int computerPlayer3ID;
    private int computerPlayer4ID;

    public STgame(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        deck = new STdeck();
    }

    public void selectDealer() {
        Random rand = new Random();
        randomNumber = rand.nextInt(numOfPlayers);
        System.out.println("Player number #" + randomNumber + " was chosen!");
    }

    public void dealRandomCardsToEachPlayer() {
        players = new STplayer[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new STplayer("ID = " + i);
        }

        for (STplayer player : players) {
            ArrayList<STcard> cards = deck.dealCards(AMOUNT_OF_CARDS_DEALT);
            player.setCards(cards);
        }

    }

    public void assignHumanPlayerID() {
        humanPlayerID = 0;
    }

    public void assignComputerPlayersID() {
        if (numOfPlayers >= THREE_PLAYERS) {
            computerPlayer1ID = 1;
            computerPlayer2ID = 2;
        }
        if (numOfPlayers >= FOUR_PLAYERS) {
            computerPlayer3ID = 3;
        }
        if (numOfPlayers == FIVE_PLAYERS) {
            computerPlayer4ID = 4;
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
        String[] characterOrder;
        boolean gameIsRunning = true;
        int z = 1;
        characterOrder = determinePlayerOrder();
        System.out.println(" *** The player order is: *** \n");
        for (int x = 0; x < characterOrder.length; x++) {
            String characterIDNumOrder = characterOrder[x];
            System.out.println(characterIDNumOrder);
        }

        while (gameIsRunning) {
            System.out.println("\n *** Turn " + z + " *** \n");
            for (int y = 0; y < characterOrder.length; y++) {
                String characterIDNumPlay = characterOrder[y];
                char characterIDNumSingle = characterIDNumPlay.charAt(characterIDNumPlay.length() - 1);
                //System.out.println(characterIDNumSingle);
                if (numOfPlayers >= THREE_PLAYERS) {
                    if (characterIDNumSingle == '0') {
                        System.out.println("Human player's turn.");
                    }
                    if (characterIDNumSingle == '1') {
                        System.out.println("Computer player's 1 turn");
                    }
                    if (characterIDNumSingle == '2') {
                        System.out.println("Computer player's 2 turn");
                    }
                }
                if (numOfPlayers >= FOUR_PLAYERS) {
                    if (characterIDNumSingle == '3') {
                        System.out.println("Computer player's 3 turn");
                    }

                }
                if (numOfPlayers >= FIVE_PLAYERS) {
                    if (characterIDNumSingle == '4') {
                        System.out.println("Computer player's 4 turn");
                    }
                }
            }
            z++;
            if (z == 10) {
                gameIsRunning = false;
            }
        }

    }

    public String[] determinePlayerOrder() {
        String[] characterOrder = new String[0];
        switch (numOfPlayers) {
            case THREE_PLAYERS: {
                characterOrder = new String[3];
                if (randomNumber == 0) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[2] = "Human player ID: " + humanPlayerID;
                }
                if (randomNumber == 1) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[1] = "Human player ID: " + humanPlayerID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer1ID;
                }
                if (randomNumber == 2) {
                    characterOrder[0] = "Human player ID: " + humanPlayerID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer2ID;
                }
                break;
            }
            case FOUR_PLAYERS: {
                characterOrder = new String[4];
                if (randomNumber == 0) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[3] = "Human player ID: " + humanPlayerID;
                }
                if (randomNumber == 1) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[2] = "Human player ID: " + humanPlayerID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer1ID;
                }
                if (randomNumber == 2) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[1] = "Human player ID: " + humanPlayerID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer2ID;
                }
                if (randomNumber == 3) {
                    characterOrder[0] = "Human player ID: " + humanPlayerID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer3ID;
                }
                break;
            }
            case FIVE_PLAYERS: {
                characterOrder = new String[5];
                if (randomNumber == 0) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer4ID;
                    characterOrder[4] = "Human player ID: " + humanPlayerID;
                }
                if (randomNumber == 1) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer4ID;
                    characterOrder[3] = "Human player ID: " + humanPlayerID;
                    characterOrder[4] = "Computer player ID: " + computerPlayer1ID;
                }
                if (randomNumber == 2) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer4ID;
                    characterOrder[2] = "Human player ID: " + humanPlayerID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[4] = "Computer player ID: " + computerPlayer2ID;
                }
                if (randomNumber == 3) {
                    characterOrder[0] = "Computer player ID: " + computerPlayer4ID;
                    characterOrder[1] = "Human player ID: " + humanPlayerID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[4] = "Computer player ID: " + computerPlayer3ID;
                }
                if (randomNumber == 4) {
                    characterOrder[0] = "Human player ID: " + humanPlayerID;
                    characterOrder[1] = "Computer player ID: " + computerPlayer1ID;
                    characterOrder[2] = "Computer player ID: " + computerPlayer2ID;
                    characterOrder[3] = "Computer player ID: " + computerPlayer3ID;
                    characterOrder[4] = "Computer player ID: " + computerPlayer4ID;
                }
                break;
            }
        }
        return characterOrder;
    }
}


