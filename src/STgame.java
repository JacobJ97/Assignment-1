import java.util.*;

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
    private int cardCounter = -1;

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
        return players[computerPlayer3ID];
    }

    public STplayer getComputerPlayer4() {
        return players[computerPlayer4ID];
    }

    public void startPlayingGame() {
        String[] characterOrder;
        ArrayList<String> pileOfCards = new ArrayList<>();
        int numOfCardsInDeckLeft = numOfPlayers * 5;
        Scanner input = new Scanner(System.in);
        boolean gameIsRunning = true;
        int z = 1;
        STplayer humanHandClass = players[0];
        String humanHandString = humanHandClass.toString();
        humanHandString = humanHandString.replace("ID = 0\nYour cards are: \n[", "");
        humanHandString = humanHandString.replace("]", "");
        ArrayList<String> humanHand = new ArrayList<>(Arrays.asList(humanHandString.split(",")));
        characterOrder = determinePlayerOrder();
        System.out.println(" *** The player order is: *** \n");
        for (int x = 0; x < characterOrder.length; x++) {
            String characterIDNumOrder = characterOrder[x];
            System.out.println(characterIDNumOrder);
        }

        while (gameIsRunning) {
            Boolean turnLoop = true;
            System.out.println("\n *** Turn " + z + " *** \n");
            for (int y = 0; y < characterOrder.length; y++) {
                String characterIDNumPlay = characterOrder[y];
                char characterIDNumSingle = characterIDNumPlay.charAt(characterIDNumPlay.length() - 1);
                //System.out.println(characterIDNumSingle);
                if (numOfPlayers >= THREE_PLAYERS) {
                    if (characterIDNumSingle == '0') {
                        //System.out.println("Human player's turn.");
                        while (turnLoop) {
                            turnLoop = true;
                            System.out.println("What would you like to do?");
                            System.out.println("1. See cards");
                            System.out.println("2. Play card");
                            System.out.println("3. Pick up");
                            System.out.printf("I choose >> ");
                            int playerChoice = input.nextInt();
                            if (playerChoice == 1) {
                                System.out.println("1");
                                System.out.println("");
                                System.out.println(humanHand);
                            }
                            if (playerChoice == 2) {
                                int matchingCard = 0;
                                System.out.println("You are throwing out a card");
                                String playersChosenCardID = findValidCard(humanHand);
                                System.out.println(playersChosenCardID);
                                for (int x = 0; x < humanHand.size(); x++) {
                                    String ass = humanHand.get(x);
                                    ass = ass.replace("\n", "");
                                    ass = ass.replace(" Card", "Card");
                                    if (Objects.equals(playersChosenCardID, ass)) {
                                        matchingCard = x;
                                        System.out.println(matchingCard);
                                        break;
                                    }
                                }
                                humanHand.remove(matchingCard);
                                pileOfCards.add(playersChosenCardID);
                                //turnLoop = false;
                            }
                            if (playerChoice == 3) {
                                System.out.println("You are picking up a card, and passing.");
                                ArrayList<STcard> cardPickedUp = deck.pickUpCard(numOfCardsInDeckLeft);
                                STcard cardPickedUpObject = cardPickedUp.remove(0);
                                String cardDetailsPickedUp = cardPickedUpObject.toString();
                                System.out.println(cardDetailsPickedUp);
                                humanHand.add(humanHand.size(), cardDetailsPickedUp);
                                //turnLoop = false;
                            }
                        }
                    }
                    if (characterIDNumSingle == '1') {
                        turnLoop = true;
                        System.out.println("Computer player's 1 turn");
                        //System.out.println("What would you like to do?");
                        //System.out.println("1. See cards");
                        //System.out.println("2. Play cards");
                        //System.out.println("3. Pick up card (and skip turn)");
                    }
                    if (characterIDNumSingle == '2') {
                        System.out.println("Computer player's 2 turn");
                        //System.out.println("What would you like to do?");
                        //System.out.println("1. See cards");
                        //System.out.println("2. Play cards");
                        //System.out.println("3. Pick up card (and skip turn)");
                    }
                }
                if (numOfPlayers >= FOUR_PLAYERS) {
                    if (characterIDNumSingle == '3') {
                        System.out.println("Computer player's 3 turn");
                        //System.out.println("What would you like to do?");
                        //System.out.println("1. See cards");
                        //System.out.println("2. Play cards");
                        //System.out.println("3. Pick up card (and skip turn)");
                    }

                }
                if (numOfPlayers >= FIVE_PLAYERS) {
                    if (characterIDNumSingle == '4') {
                        System.out.println("Computer player's 4 turn");
                        //System.out.println("What would you like to do?");
                        //System.out.println("1. See cards");
                        //System.out.println("2. Play cards");
                        //System.out.println("3. Pick up card (and skip turn)");
                    }
                }
            }
            z++;
            if (z == 10) {
                gameIsRunning = false;
            }
        }

    }

    private String findValidCard(ArrayList<String> humanDeck) {
        int x;
        int y = 0;
        Boolean cardValid = true;
        String idNumSingle;
        String cardIDNum;
        String idNumString;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter card ID >> ");
        cardIDNum = input.next();
        String humanDeckString = humanDeck.toString();
        humanDeckString = humanDeckString.replace("[\n", "");
        humanDeckString = humanDeckString.replace("\n", "|");
        String humanDeckStringSplitUp[] = humanDeckString.split("[|]");

        while (cardValid) {
            for (x = 0; x < humanDeckStringSplitUp.length; x++) {
                idNumString = humanDeckStringSplitUp[x];
                if (Objects.equals(idNumString.substring(0, 9), "Card ID: ")) {
                    if (Objects.equals(idNumString.substring(10, 11), " ")) {
                        idNumSingle = idNumString.substring(9, 10);
                    } else {
                        idNumSingle = idNumString.substring(9, 11);
                    }
                    if (Objects.equals(idNumSingle, cardIDNum)) {
                        if (Objects.equals(idNumSingle, cardIDNum)) {
                            if (Objects.equals(idNumString, "55") || Objects.equals(idNumString, "56") ||
                                    Objects.equals(idNumString, "57") || Objects.equals(idNumString, "58") ||
                                    Objects.equals(idNumString, "59") || Objects.equals(idNumString, "60")) {
                                String[] cardDetailSuperTrumpArray = new String[4];
                                for (int z = 0; z < cardDetailSuperTrumpArray.length; z++) {
                                    String cardDetailStuff = humanDeckStringSplitUp[y];
                                    cardDetailSuperTrumpArray[z] = cardDetailStuff;
                                }
                                String cardDetailSuperTrumpString = Arrays.toString(cardDetailSuperTrumpArray);
                                return cardDetailSuperTrumpString;
                            } else {
                                String[] cardDetailMineralArray = new String[7];
                                for (int z = 0; z < cardDetailMineralArray.length; z++) {
                                    String cardDetailStuff = humanDeckStringSplitUp[y];
                                    cardDetailMineralArray[z] = cardDetailStuff;
                                    y++;
                                }
                                String cardDetailMineralString = Arrays.toString(cardDetailMineralArray);
                                cardDetailMineralString = cardDetailMineralString.replace(",", "|");
                                cardDetailMineralString = cardDetailMineralString.replace("|  ]", "");
                                cardDetailMineralString = cardDetailMineralString.replace("[", "");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Na", "| Na");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Ha", "| Ha");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Gr", "| Gr");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Cl", "| Cl");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Cr", "| Cr");
                                cardDetailMineralString = cardDetailMineralString.replace("|  Ec", "| Ec");
                                return cardDetailMineralString;
                            }
                        }
                    }
                }
                y++;
            }
            System.out.println("Match cannot be made. Try again.");
            System.out.printf("Enter card ID >> ");
            cardIDNum = input.next();
        }
        return "0";
    }

    private String[] determinePlayerOrder() {
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


