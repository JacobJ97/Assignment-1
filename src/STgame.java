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
        ArrayList<String> humanHand;
        ArrayList<String> computerOneHand;
        ArrayList<String> computerTwoHand;
        ArrayList<String> computerThreeHand;
        ArrayList<String> computerFourHand;
        String cardCategory = "";
        int numOfCardsInDeckLeft = numOfPlayers * 5;
        Scanner input = new Scanner(System.in);
        boolean gameIsRunning = true;
        int z = 1;
        humanHand = setUpHumanHand();
        computerOneHand = setUpComputerOneHand();
        computerTwoHand = setUpComputerTwoHand();
        computerThreeHand = setUpComputerThreeHand();
        computerFourHand = setUpComputerFourHand();
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
                                Boolean approvedCard = isCardValid(playersChosenCardID, pileOfCards, cardCategory);
                                if (approvedCard == true) {
                                    humanHand.remove(matchingCard);
                                    pileOfCards.add(playersChosenCardID);
                                    if (pileOfCards.size() == 1) {
                                        System.out.println("1. Card Hardness");
                                        System.out.println("2. Card Gravity");
                                        System.out.println("3. Card Cleagage");
                                        System.out.println("4. Card Crystal Abundance");
                                        System.out.println("5. Card Economic Value");
                                        System.out.printf("Select category >> ");
                                        int categoryChoice = input.nextInt();
                                        while (categoryChoice > 1 || categoryChoice < 5) {
                                            System.out.println("Invalid input! Try again.");
                                            System.out.printf("Select category >> ");
                                            categoryChoice = input.nextInt();
                                        }
                                        switch (categoryChoice) {
                                            case 1: {
                                                cardCategory = "Card Hardness";
                                            }
                                            case 2: {
                                                cardCategory = "Card Gravity";
                                            }
                                            case 3: {
                                                cardCategory = "Card Cleagage";
                                            }
                                            case 4: {
                                                cardCategory = "Card Crystal Abundance";
                                            }
                                            case 5: {
                                                cardCategory = "Card Economic Value";
                                            }
                                        }
                                    }
                                }
                                //turnLoop = false;
                            }
                            if (playerChoice == 3) {
                                System.out.println("You are picking up a card, and passing.");
                                ArrayList<STcard> cardPickedUp = deck.pickUpCard(numOfCardsInDeckLeft);
                                STcard cardPickedUpObject = cardPickedUp.remove(0);
                                String cardDetailsPickedUp = cardPickedUpObject.toString();
                                humanHand.add(cardDetailsPickedUp);
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

    private Boolean isCardValid(String cardID, ArrayList<String> pileOfCards, String cardCategory) {
        if (pileOfCards.size() == 0) {
            return true;
        }
        String cardIDSplit[] = cardID.split("[|]");
        switch (cardCategory) {
            case "Card Hardness": {
                String categoryIndividual = cardIDSplit[2];
                if (Objects.equals(categoryIndividual.substring(12, 13), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 12);
                }
                if  (Objects.equals(categoryIndividual.substring(14, 15), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 14);
                }
                if  (Objects.equals(categoryIndividual.substring(16, 17), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 16);
                }
                if  (Objects.equals(categoryIndividual.substring(18, 19), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 18);
                }
            }
            case "Card Gravity": {
                String categoryIndividual = cardIDSplit[3];
                if (Objects.equals(categoryIndividual.substring(13, 14), " ")) {
                    String gravityNum = categoryIndividual.substring(10, 13);
                }
                if (Objects.equals(categoryIndividual.substring(14, 15), " ")) {
                    String gravityNum = categoryIndividual.substring(10, 14);
                }
                if (Objects.equals(categoryIndividual.substring(17, 18), " ")) {
                    String gravityNum = categoryIndividual.substring(10, 17);
                }
            }
            case "Card Cleagage": {
                String categoryIndividual = cardIDSplit[4];
                if (Objects.equals(categoryIndividual.substring(15, 16), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 15);
                }
                if (Objects.equals(categoryIndividual.substring(17, 18), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 17);
                }
                if (Objects.equals(categoryIndividual.substring(17, 18), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 17);
                }
                if (Objects.equals(categoryIndividual.substring(20, 21), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 20);
                }
                if (Objects.equals(categoryIndividual.substring(25, 26), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 25);
                }
                if (Objects.equals(categoryIndividual.substring(29, 30), " ")) {
                    String cleavageNum = categoryIndividual.substring(11, 28);
                }
            }
            case "Card Crystal Abundance": {
                String categoryIndividual = cardIDSplit[5];
                if (Objects.equals(categoryIndividual.substring(22, 23), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 22);
                }
                if (Objects.equals(categoryIndividual.substring(23, 24), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 23);
                }
                if (Objects.equals(categoryIndividual.substring(24, 25), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 24);
                }
                if (Objects.equals(categoryIndividual.substring(28, 29), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 28);
                }
                if (Objects.equals(categoryIndividual.substring(29, 30), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 29);
                }
                if (Objects.equals(categoryIndividual.substring(30, 31), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(20, 30);
                }
            }
            case "Card Economic Value": {
                String categoryIndividual = cardIDSplit[6];
                if (Objects.equals(categoryIndividual.substring(19, 20), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 19);
                }
                if (Objects.equals(categoryIndividual.substring(20, 21), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 20);
                }
                if (Objects.equals(categoryIndividual.substring(23, 24), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 23);
                }
                if (Objects.equals(categoryIndividual.substring(24, 25), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 24);
                }
                if (Objects.equals(categoryIndividual.substring(25, 26), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 25);
                }
                if (Objects.equals(categoryIndividual.substring(26, 27), " ")) {
                    String crystalAbundanceNum = categoryIndividual.substring(17, 26);
                }
            }

        }

        return true;
    }

    private ArrayList<String> setUpComputerFourHand() {
        String computerFourHandString = players[4].toString();
        computerFourHandString = computerFourHandString.replace("ID = 3\nYour cards are: \n[", "");
        computerFourHandString = computerFourHandString.replace("]", "");
        return new ArrayList<>(Arrays.asList(computerFourHandString.split(",")));
    }

    private ArrayList<String> setUpComputerThreeHand() {
        String computerThreeHandString = players[3].toString();
        computerThreeHandString = computerThreeHandString.replace("ID = 3\nYour cards are: \n[", "");
        computerThreeHandString = computerThreeHandString.replace("]", "");
        return new ArrayList<>(Arrays.asList(computerThreeHandString.split(",")));
    }

    private ArrayList<String> setUpComputerTwoHand() {
        String computerTwoHandString = players[2].toString();
        computerTwoHandString = computerTwoHandString.replace("ID = 2\nYour cards are: \n[", "");
        computerTwoHandString = computerTwoHandString.replace("]", "");
        return new ArrayList<>(Arrays.asList(computerTwoHandString.split(",")));
    }

    private ArrayList<String> setUpComputerOneHand() {
        String computerOneHandString = players[1].toString();
        computerOneHandString = computerOneHandString.replace("ID = 1\nYour cards are: \n[", "");
        computerOneHandString = computerOneHandString.replace("]", "");
        return new ArrayList<>(Arrays.asList(computerOneHandString.split(",")));
    }

    private ArrayList<String> setUpHumanHand() {
        String humanHandString = players[0].toString();
        humanHandString = humanHandString.replace("ID = 0\nYour cards are: \n[", "");
        humanHandString = humanHandString.replace("]", "");
        return new ArrayList<>(Arrays.asList(humanHandString.split(",")));
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
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace(",", "|");
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace("|  ]", "");
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace("[", "");
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace("|  SU", "| SU");
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace("|  Na", "| Na");
                                cardDetailSuperTrumpString = cardDetailSuperTrumpString.replace("|  Ac", "| Ac");
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


