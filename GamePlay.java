import java.util.Scanner;

/* GamePlay class Uses Person class to ask user for name with optional
last name. Uses Numbers class to generate a number between 1 and 100. 
Asks user to guess a random number. Uses loop to make user guess until correct */
public class GamePlay {

    //new instance variable used to make the game multiplayer
    private Players[] currentPlayers = new Players[3];
    
    public static void main(String[] args) {

        GamePlay game = new GamePlay();
        Scanner scanner = new Scanner(System.in);

        //loops name input for number of players in game.
        for (int i = 0; i < game.currentPlayers.length; i++) {

            System.out.println("Please enter your first name for player " + (i + 1) + ".");
            String firstName = scanner.nextLine();
            System.out.println("Would you like to enter a last name? (y/n)");
            String lastName = scanner.nextLine();

            if (lastName.equalsIgnoreCase("y")) {
                System.out.println("Please enter your last name.");
                lastName = scanner.nextLine();
                game.currentPlayers[i] = new Players(firstName, lastName);
            } else {
                lastName = "";
                game.currentPlayers[i] = new Players(firstName);
            }
        }
        boolean playAgain = true;
        while (playAgain) {
            Hosts host = new Hosts("Bob", "Barker");
            Turn turn = new Turn();
            boolean endGame = false;
            //Cycles through each player giving each one turn in order.
            while (!endGame) {
                for (int i = 0; i < game.currentPlayers.length; i++) {
                    endGame = turn.takeTurn(game.currentPlayers[i], host);
                    if (endGame) {
                        break;
                    }
                }
            }
            System.out.println("Play again? y/n ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }
        scanner.close();
    }
}
