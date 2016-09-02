import java.util.Scanner;

/**
 * Created by outba on 9/2/2016.
 */
public class STmain {
    private static final int NEW_GAME = 1;
    private static final int END_GAME = 2;

    public static void main(String[] args) {
        showMenu();
        placeInput();
    }

    private static void placeInput() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Input your number: ");
        int number = input.nextInt();
        while (number != 1 && number != 2) {
            System.out.printf("You have entered an invalid number. Please enter another number: ");
            number = input.nextInt();
        }
        switch (number) {
            case NEW_GAME: {
                startNewGame();
            }
            case END_GAME: {
                endGame();
            }
        }
    }

    private static void endGame() {
        System.exit(0);
    }


    private static void startNewGame() {
        int numOfPlayers = getNumOfPlayers();
        STgame game = new STgame(numOfPlayers);
        game.selectDealer();
        game.dealRandomCardsToEachPlayer();
    }

    private static int getNumOfPlayers() {
        int numOfPlayers;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter the number of players you want to play: ");
        numOfPlayers = input.nextInt();
        return numOfPlayers;
    }

    private static void showMenu() {
        System.out.println("***************************************************************************************");
        System.out.println("*             Welcome to Mineral Supertrumps, a game about minerals!                  *");
        System.out.println("*             Here are your options:                                                  *");
        System.out.println("*                                                                                     *");
        System.out.println("*             1. Start game                                                           *");
        System.out.println("*             2. Exit game                                                            *");
        System.out.println("***************************************************************************************");
    }
}
