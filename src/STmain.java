import java.util.Objects;
import java.util.Scanner;

/**
 * Created by outba on 9/2/2016.
 */
public class STmain {
    private static final int NEW_GAME = 1;
    private static final int END_GAME = 2;
    private static final int INSTRUCTIONS = 3;

    public static void main(String[] args) {
        int numberOpt;
        STgame game;
        boolean gameIsOn = true;
        while (gameIsOn) {
            showMenu();
            numberOpt = placeInput();
            switch (numberOpt) {
                case NEW_GAME: {
                    game = startNewGame();
                    game.startPlayingGame();
                }
                case END_GAME: {
                    endGame();
                }
                case INSTRUCTIONS: {
                    showInstructions();
                }
            }
        }
    }

    private static int placeInput() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Input your number: ");
        int number = input.nextInt();
        while (number != 1 && number != 2 && number !=3) {
            System.out.printf("You have entered an invalid number. Please enter another number: ");
            number = input.nextInt();
        }
        return number;
    }

    private static void showInstructions() {
        Scanner input = new Scanner(System.in);
        System.out.println("To be added.");
        System.out.println("Enter r to return.");
        String value = input.next();
        while (!Objects.equals(value, "r")) {
            System.out.println("Error, try again.");
            System.out.println("Enter r to return.");
            value = input.next();
        }
    }

    private static void endGame() {
        System.exit(0);
    }

    private static STgame startNewGame() {
        int numOfPlayers = getNumOfPlayers();
        STgame game = new STgame(numOfPlayers);
        game.selectDealer();
        game.dealRandomCardsToEachPlayer();
        game.assignHumanPlayerID();
        game.assignComputerPlayersID();
        STplayer humanPlayer = game.getHumanPlayer();
        showPlayer(humanPlayer);
        gettingComputerPlayers(numOfPlayers, game);
        return game;
    }

    private static void gettingComputerPlayers(int numOfPlayers, STgame game) {
        switch (numOfPlayers) {
            case 3: {
                STplayer computerPlayer1 = game.getComputerPlayer1();
                STplayer computerPlayer2 = game.getComputerPlayer2();
                showComputer(computerPlayer1);
                showComputer(computerPlayer2);
                break;
            }
            case 4: {
                STplayer computerPlayer1 = game.getComputerPlayer1();
                STplayer computerPlayer2 = game.getComputerPlayer2();
                STplayer computerPlayer3 = game.getComputerPlayer3();
                showComputer(computerPlayer1);
                showComputer(computerPlayer2);
                showComputer(computerPlayer3);
                break;
            }
            case 5: {
                STplayer computerPlayer1 = game.getComputerPlayer1();
                STplayer computerPlayer2 = game.getComputerPlayer2();
                STplayer computerPlayer3 = game.getComputerPlayer3();
                STplayer computerPlayer4 = game.getComputerPlayer4();
                showComputer(computerPlayer1);
                showComputer(computerPlayer2);
                showComputer(computerPlayer3);
                showComputer(computerPlayer4);
                break;
            }
        }
    }

    private static void showComputer(STplayer computerPlayer) {
        System.out.println("This computer's ID is | " + computerPlayer);
    }

    private static void showPlayer(STplayer humanPlayer) {
        System.out.println("\nYour player ID is | " + humanPlayer);
    }

    private static int getNumOfPlayers() {
        int numOfPlayers;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter the number of players that will be in this game (including yourself): ");
        numOfPlayers = input.nextInt();
        while (numOfPlayers < 3 || numOfPlayers > 5) {
            System.out.printf("There can only be a minimum of 3 players, and a maximum of 5 players. Enter a valid number: ");
            numOfPlayers = input.nextInt();
        }
        return numOfPlayers;
    }

    private static void showMenu() {
        System.out.println("***************************************************************************************");
        System.out.println("*             Welcome to Mineral Supertrumps, a game about minerals!                  *");
        System.out.println("*             Here are your options:                                                  *");
        System.out.println("*                                                                                     *");
        System.out.println("*             1. Start game                                                           *");
        System.out.println("*             2. Exit game                                                            *");
        System.out.println("*             3. Show Instructions                                                    *");
        System.out.println("***************************************************************************************");
    }
}
