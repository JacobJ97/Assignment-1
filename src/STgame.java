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
        if (numOfPlayers >= FOUR_PLAYERS) {
            computerThreeHand = setUpComputerThreeHand();
        }
        if (numOfPlayers == FIVE_PLAYERS) {
            computerFourHand = setUpComputerFourHand();
        }
        double previousCardNum = 0;
        ArrayList<Double> previousCardNumArray = new ArrayList<>();
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
                                System.out.println(humanHand);
                            }
                            if (playerChoice == 2) {
                                int matchingCard = 0;
                                System.out.println("You are throwing out a card");
                                String playersChosenCardID = findValidCard(humanHand);
                                System.out.println(playersChosenCardID);
                                String retrievedCardDetails = "";
                                for (int x = 0; x < humanHand.size(); x++) {
                                    retrievedCardDetails = humanHand.get(x);
                                    retrievedCardDetails = retrievedCardDetails.replace("\n", "");
                                    retrievedCardDetails = retrievedCardDetails.replace(" Card", "Card");
                                    if (Objects.equals(playersChosenCardID, retrievedCardDetails)) {
                                        matchingCard = x;
                                        break;
                                    }
                                }
                                if (playersChosenCardID.split("|").length != 4) {
                                    if (pileOfCards.size() == 0) {
                                        System.out.println("1. Card Hardness");
                                        System.out.println("2. Card Gravity");
                                        System.out.println("3. Card Cleagage");
                                        System.out.println("4. Card Crustal Abundance");
                                        System.out.println("5. Card Economic Value");
                                        System.out.printf("Select category >> ");
                                        int categoryChoice = input.nextInt();
                                        while (categoryChoice < 1 && categoryChoice > 5) {
                                            System.out.println("Invalid input! Try again.");
                                            System.out.printf("Select category >> ");
                                            categoryChoice = input.nextInt();
                                        }
                                        switch (categoryChoice) {
                                            case 1: {
                                                cardCategory = "Card Hardness";
                                                break;
                                            }
                                            case 2: {
                                                cardCategory = "Card Gravity";
                                                break;
                                            }
                                            case 3: {
                                                cardCategory = "Card Cleavage";
                                                break;
                                            }
                                            case 4: {
                                                cardCategory = "Card Crustal Abundance";
                                                break;
                                            }
                                            case 5: {
                                                cardCategory = "Card Economic Value";
                                                break;
                                            }
                                        }
                                    }
                                }
                                String[] approvedCard = isCardValid(playersChosenCardID, cardCategory, previousCardNum);
                                boolean booleanCard;
                                //assert approvedCard != null;
                                if (Objects.equals(approvedCard[2], "SUPERTRUMP CARD")) {
                                    cardCategory = approvedCard[0];
                                    booleanCard = Boolean.valueOf(approvedCard[1]);
                                    previousCardNum = 0;
                                    previousCardNumArray.add(previousCardNum);
                                }
                                else {
                                    String booleanString = approvedCard[0];
                                    String previousCardNumString = approvedCard[1];
                                    previousCardNum = Double.parseDouble(previousCardNumString);
                                    previousCardNumArray.add(previousCardNum);
                                    booleanCard = Boolean.valueOf(booleanString);
                                }
                                if (booleanCard) {
                                    humanHand.remove(matchingCard);
                                    pileOfCards.add(playersChosenCardID);
                                    System.out.println("Your card has been successfully thrown out.");
                                    turnLoop = false;
                                }
                                if (!booleanCard) {
                                    previousCardNumArray.remove(previousCardNumArray.size() - 1);
                                    previousCardNum = previousCardNumArray.get(previousCardNumArray.size() - 1);
                                    System.out.println("Your card has not been successfully thrown out. Try again.");
                                }
                            }
                            if (playerChoice == 3) {
                                System.out.println("You are picking up a card, and passing.");
                                ArrayList<STcard> cardPickedUp = deck.pickUpCard(numOfCardsInDeckLeft);
                                STcard cardPickedUpObject = cardPickedUp.remove(0);
                                String cardDetailsPickedUp = cardPickedUpObject.toString();
                                humanHand.add(cardDetailsPickedUp);
                                turnLoop = false;
                            }
                        }
                    }
                    if (characterIDNumSingle == '1') {
                        turnLoop = true;
                        System.out.println("Computer player's 1 turn");

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

    private String[] isCardValid(String cardID, String cardCategory, double previousCardNum) {
        String cardIDSplit[] = cardID.split("[|]");
        System.out.println(cardIDSplit[1]);
        System.out.println(cardIDSplit[2]);
        if (Objects.equals(cardIDSplit[1], " SUPERTRUMP CARD ")) {
            String[] superTrumpArray = new String[3];
            if (Objects.equals(cardIDSplit[2], " Name: The Miner ")) {
                cardCategory = "Card Economic Value";
                superTrumpArray[0] = cardCategory;
                superTrumpArray[1] = "true";
                superTrumpArray[2] = "SUPERTRUMP CARD";
                return superTrumpArray;
            }
            if (Objects.equals(cardIDSplit[2], " Name: The Petrologist ")) {
                cardCategory = "Card Crustal Abundance";
                superTrumpArray[0] = cardCategory;
                superTrumpArray[1] = "true";
                superTrumpArray[2] = "SUPERTRUMP CARD";
                return superTrumpArray;
            }
            if (Objects.equals(cardIDSplit[2], " Name: The Gemmologist ")) {
                cardCategory = "Card Hardness";
                superTrumpArray[0] = cardCategory;
                superTrumpArray[1] = "true";
                superTrumpArray[2] = "SUPERTRUMP CARD";
                return superTrumpArray;
            }
            if (Objects.equals(cardIDSplit[2], " Name: The Mineralogist ")) {
                cardCategory = "Card Cleavage";
                superTrumpArray[0] = cardCategory;
                superTrumpArray[1] = "true";
                superTrumpArray[2] = "SUPERTRUMP CARD";
                return superTrumpArray;
            }
            if (Objects.equals(cardIDSplit[2], " Name: The Geophysicist ")) {
                cardCategory = "Specific Gravity";
                superTrumpArray[0] = cardCategory;
                superTrumpArray[1] = "true";
                superTrumpArray[2] = "SUPERTRUMP CARD";
                return superTrumpArray;
            }
            if (Objects.equals(cardIDSplit[2], " Name: The Geologist ")) {
                System.out.println("1. Card Hardness");
                System.out.println("2. Card Gravity");
                System.out.println("3. Card Cleagage");
                System.out.println("4. Card Crustal Abundance");
                System.out.println("5. Card Economic Value");
                System.out.printf("Select the new category >> ");
                Scanner input = new Scanner(System.in);
                int categoryChoice = input.nextInt();
                while (categoryChoice < 1 && categoryChoice > 5) {
                    System.out.println("Invalid input! Try again.");
                    System.out.printf("Select category >> ");
                    categoryChoice = input.nextInt();
                }
                switch (categoryChoice) {
                    case 1: {
                        cardCategory = "Card Hardness";
                        superTrumpArray[0] = cardCategory;
                        superTrumpArray[1] = "true";
                        superTrumpArray[2] = "SUPERTRUMP CARD";
                        return superTrumpArray;
                    }
                    case 2: {
                        cardCategory = "Card Gravity";
                        superTrumpArray[0] = cardCategory;
                        superTrumpArray[1] = "true";
                        superTrumpArray[2] = "SUPERTRUMP CARD";
                        return superTrumpArray;
                    }
                    case 3: {
                        cardCategory = "Card Cleavage";
                        superTrumpArray[0] = cardCategory;
                        superTrumpArray[1] = "true";
                        superTrumpArray[2] = "SUPERTRUMP CARD";
                        return superTrumpArray;
                    }
                    case 4: {
                        cardCategory = "Card Crustal Abundance";
                        superTrumpArray[0] = cardCategory;
                        superTrumpArray[1] = "true";
                        superTrumpArray[2] = "SUPERTRUMP CARD";
                        return superTrumpArray;
                    }
                    case 5: {
                        cardCategory = "Card Economic Value";
                        superTrumpArray[0] = cardCategory;
                        superTrumpArray[1] = "true";
                        superTrumpArray[2] = "SUPERTRUMP CARD";
                        return superTrumpArray;
                    }
                }
            }
        }
        switch (cardCategory) {
            case "Card Hardness": {
                String categoryIndividual = cardIDSplit[2];
                if (Objects.equals(categoryIndividual.substring(12, 13), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 12);
                    int hardnessInt = Integer.parseInt(hardnessNum);
                    boolean booleanResult = hardnessInt >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = hardnessInt;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(13, 14), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 13);
                    int hardnessInt = Integer.parseInt(hardnessNum);
                    boolean booleanResult = hardnessInt >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = hardnessInt;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "b"};
                }
                if (Objects.equals(categoryIndividual.substring(14, 15), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 14);
                    double hardnessDouble = Double.parseDouble(hardnessNum);
                    boolean booleanResult = hardnessDouble >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = hardnessDouble;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "b"};
                }
                if (Objects.equals(categoryIndividual.substring(16, 17), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 16);
                    String hardnessSum = hardnessNum.replace("-", " ");
                    String hardnessSumArray[] = hardnessSum.split(" ");
                    String hardnessSumOne = hardnessSumArray[0];
                    double num1 = Double.parseDouble(hardnessSumOne);
                    String hardnessSumTwo = hardnessSumArray[1];
                    double num2 = Double.parseDouble(hardnessSumTwo);
                    double hardnessNumTotalAverage = (num1 + num2) / 2;
                    boolean booleanResult = hardnessNumTotalAverage >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = hardnessNumTotalAverage;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(18, 19), " ")) {
                    String hardnessNum = categoryIndividual.substring(11, 18);
                    String hardnessSum = hardnessNum.replace("-", " ");
                    String hardnessSumArray[] = hardnessSum.split(" ");
                    String hardnessSumOne = hardnessSumArray[0];
                    double num1 = Double.parseDouble(hardnessSumOne);
                    String hardnessSumTwo = hardnessSumArray[1];
                    double num2 = Double.parseDouble(hardnessSumTwo);
                    double hardnessNumTotalAverage = (num1 + num2) / 2;
                    boolean booleanResult = hardnessNumTotalAverage >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = hardnessNumTotalAverage;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }

            }
            case "Card Gravity": {
                String categoryIndividual = cardIDSplit[3];
                if (Objects.equals(categoryIndividual.substring(13, 14), " ")) {
                    String gravityNum = categoryIndividual.substring(11, 13);
                    double gravityNumDouble = Double.parseDouble(gravityNum);
                    boolean booleanResult = gravityNumDouble >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = gravityNumDouble;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(14, 15), " ")) {
                    String gravityNum = categoryIndividual.substring(11, 14);
                    double gravityNumDouble = Double.parseDouble(gravityNum);
                    boolean booleanResult = gravityNumDouble >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = gravityNumDouble;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(17, 18), " ")) {
                    String gravityNum = categoryIndividual.substring(11, 17);
                    String gravitySum = gravityNum.replace("-", " ");
                    String gravitySumArray[] = gravitySum.split(" ");
                    String gravitySumOne = gravitySumArray[0];
                    double num1 = Double.parseDouble(gravitySumOne);
                    String gravitySumTwo = gravitySumArray[1];
                    double num2 = Double.parseDouble(gravitySumTwo);
                    double gravityNumTotalAverage = (num1 + num2) / 2;
                    boolean booleanResult = gravityNumTotalAverage >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = gravityNumTotalAverage;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
            }
            case "Card Cleavage": {
                String categoryIndividual = cardIDSplit[4];
                if (Objects.equals(categoryIndividual.substring(15, 16), " ")) {
                    //String cleavageString = categoryIndividual.substring(11, 15);
                    int cleavageNum = 1;
                    boolean booleanResult;
                    if (cleavageNum >= previousCardNum) booleanResult = true;
                    else booleanResult = false;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = cleavageNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(17, 18), " ")) {
                    String cleavageString = categoryIndividual.substring(11, 17);
                    String[] cleavageArray = cleavageString.split(" ");
                    String cleavageStringNum = cleavageArray[0];
                    int cleavageInt = Integer.parseInt(cleavageStringNum);
                    String cleavageCondition = cleavageArray[1];
                    if (Objects.equals(cleavageCondition, "poor")) {
                        int cleavageNum = 3 + cleavageInt;
                        boolean booleanResult = cleavageNum >= previousCardNum;
                        String booleanString = Boolean.toString(booleanResult);
                        previousCardNum = cleavageNum;
                        String previousCardString = Double.toString(previousCardNum);
                        return new String[]{booleanString, previousCardString, "a"};
                    }
                    if (Objects.equals(cleavageCondition, "good")) {
                        int cleavageNum = 4 + cleavageInt;
                        boolean booleanResult = cleavageNum >= previousCardNum;
                        String booleanString = Boolean.toString(booleanResult);
                        previousCardNum = cleavageNum;
                        String previousCardString = Double.toString(previousCardNum);
                        return new String[]{booleanString, previousCardString, "a"};
                    }
                }
                if (Objects.equals(categoryIndividual.substring(20, 21), " ")) {
                    String cleavageString = categoryIndividual.substring(11, 20);
                    String[] cleavageArray = cleavageString.split(" ");
                    String cleavageStringNum = cleavageArray[0];
                    int cleavageInt = Integer.parseInt(cleavageStringNum);
                    String cleavageCondition = cleavageArray[1];
                    if (Objects.equals(cleavageCondition, "perfect")) {
                        int cleavageNum = 50 + cleavageInt;
                        boolean booleanResult = cleavageNum >= previousCardNum;
                        String booleanString = Boolean.toString(booleanResult);
                        previousCardNum = cleavageNum;
                        String previousCardString = Double.toString(previousCardNum);
                        return new String[]{booleanString, previousCardString, "a"};
                    }
                }
                if (Objects.equals(categoryIndividual.substring(22, 23), " ")) {
                    //String cleavageString = categoryIndividual.substring(11, 22);
                    int cleavageNum = 2;
                    boolean booleanResult;
                    if (cleavageNum >= previousCardNum) booleanResult = true;
                    else booleanResult = false;
                    String booleanString;
                    booleanString = Boolean.toString(booleanResult);
                    previousCardNum = cleavageNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(23, 24), " ")) {
                    String cleavageString = categoryIndividual.substring(11, 23);
                    String[] cleavageArrayWhole = cleavageString.split("[-]");
                    String[] cleavageArraySplitOne = cleavageArrayWhole[0].split(" ");
                    String[] cleavageArraySplitTwo = cleavageArrayWhole[1].split(" ");
                    String cleavageStringNumOne = cleavageArraySplitOne[0];
                    int cleavageIntOne = Integer.parseInt(cleavageStringNumOne);
                    String cleavageConditionOne = cleavageArraySplitOne[1];
                    //String cleavageStringNumTwo = cleavageArraySplitTwo[0];
                    //int cleavageIntTwo = Integer.parseInt(cleavageStringNumTwo);
                    String cleavageConditionTwo = cleavageArraySplitTwo[1];
                    if (Objects.equals(cleavageConditionOne, "Good")) {
                        if (Objects.equals(cleavageConditionTwo, "Poor")) {
                            double cleavageNum = (4 + cleavageIntOne) + .5;
                            boolean booleanResult = cleavageNum >= previousCardNum;
                            String booleanString = Boolean.toString(booleanResult);
                            previousCardNum = cleavageNum;
                            String previousCardString = Double.toString(previousCardNum);
                            return new String[]{booleanString, previousCardString, "a"};
                        }
                    }
                }
                if (Objects.equals(categoryIndividual.substring(26, 27), " ")) {
                    String cleavageString = categoryIndividual.substring(11, 26);
                    String[] cleavageArrayWhole = cleavageString.split("[-]");
                    String[] cleavageArraySplitOne = cleavageArrayWhole[0].split(" ");
                    String[] cleavageArraySplitTwo = cleavageArrayWhole[1].split(" ");
                    String cleavageStringNumOne = cleavageArraySplitOne[0];
                    int cleavageIntOne = Integer.parseInt(cleavageStringNumOne);
                    String cleavageConditionOne = cleavageArraySplitOne[1];
                    String cleavageStringNumTwo = cleavageArraySplitTwo[0];
                    int cleavageIntTwo = Integer.parseInt(cleavageStringNumTwo);
                    String cleavageConditionTwo = cleavageArraySplitTwo[1];
                    if (Objects.equals(cleavageConditionOne, "Perfect")) {
                        if (Objects.equals(cleavageConditionTwo, "Good")) {
                            double cleavageNum = (50 + (10 + cleavageIntOne)) + (cleavageIntTwo);
                            boolean booleanResult = cleavageNum >= previousCardNum;
                            String booleanString = Boolean.toString(booleanResult);
                            previousCardNum = cleavageNum;
                            String previousCardString = Double.toString(previousCardNum);
                            return new String[]{booleanString, previousCardString, "a"};
                        }
                    }
                }
            }
            case "Card Crustal Abundance": {
                String categoryIndividual = cardIDSplit[5];
                if (Objects.equals(categoryIndividual.substring(22, 23), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 22);
                    int crustalAbundanceNum = 3;
                    boolean booleanResult;
                    if (crustalAbundanceNum >= previousCardNum) booleanResult = true;
                    else booleanResult = false;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(23, 24), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 23);
                    int crustalAbundanceNum = 5;
                    boolean booleanResult;
                    if (crustalAbundanceNum >= previousCardNum) booleanResult = true;
                    else booleanResult = false;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(24, 25), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 24);
                    int crustalAbundanceNum = 2;
                    boolean booleanResult = crustalAbundanceNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(28, 29), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 28);
                    int crustalAbundanceNum = 4;
                    boolean booleanResult = crustalAbundanceNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(29, 30), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 29);
                    int crustalAbundanceNum = 6;
                    boolean booleanResult;
                    if (crustalAbundanceNum >= previousCardNum) booleanResult = true;
                    else booleanResult = false;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(30, 31), " ")) {
                    //String crustalAbundanceString = categoryIndividual.substring(20, 30);
                    int crustalAbundanceNum = 1;
                    boolean booleanResult = crustalAbundanceNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = crustalAbundanceNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
            }
            case "Card Economic Value": {
                String categoryIndividual = cardIDSplit[6];
                System.out.println(cardIDSplit[6]);
                if (Objects.equals(categoryIndividual.substring(19, 20), " ")) {
                    int economicValueNum = 2;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString;
                    booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(21, 22), " ")) {
                    int economicValueNum = 4;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString;
                    booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(23, 24), " ")) {
                    //String economicValueString = categoryIndividual.substring(17, 23);
                    int economicValueNum = 1;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString;
                    booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(24, 25), " ")) {
                    //String economicValueString = categoryIndividual.substring(17, 24);
                    int economicValueNum = 3;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(25, 26), " ")) {
                    //String economicValueString = categoryIndividual.substring(17, 25);
                    int economicValueNum = 5;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
                if (Objects.equals(categoryIndividual.substring(26, 27), " ")) {
                    //String economicValueString = categoryIndividual.substring(17, 26);
                    int economicValueNum = 6;
                    boolean booleanResult = economicValueNum >= previousCardNum;
                    String booleanString = Boolean.toString(booleanResult);
                    previousCardNum = economicValueNum;
                    String previousCardString = Double.toString(previousCardNum);
                    return new String[]{booleanString, previousCardString, "a"};
                }
            }
        }
        return null;
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
        String idNumString;
        String cardIDNum;
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
                System.out.println(idNumString);
                if (Objects.equals(idNumString.substring(0, 9), "Card ID: ")) {
                    if (Objects.equals(idNumString.substring(10, 11), " ")) {
                        idNumSingle = idNumString.substring(9, 10);
                    } else {
                        idNumSingle = idNumString.substring(9, 11);
                    }
                    if (Objects.equals(idNumSingle, cardIDNum)) {
                        if (Objects.equals(idNumSingle, cardIDNum)) {
                            if (Objects.equals(idNumSingle, "55") || Objects.equals(idNumSingle, "56") ||
                                    Objects.equals(idNumSingle, "57") || Objects.equals(idNumSingle, "58") ||
                                    Objects.equals(idNumSingle, "59") || Objects.equals(idNumSingle, "60")) {
                                String[] cardDetailSuperTrumpArray = new String[4];
                                for (int z = 0; z < cardDetailSuperTrumpArray.length; z++) {
                                    String cardDetailStuff = humanDeckStringSplitUp[y];
                                    cardDetailSuperTrumpArray[z] = cardDetailStuff;
                                    y++;
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
                                cardDetailMineralString = cardDetailMineralString.replace(" |  ]", " ");
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
            y = 0;
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

