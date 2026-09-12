import java.util.Scanner;

/* GamePlay class Uses Person class to ask user for name with optional
last name. Uses Numbers class to generate a number between 1 and 100. 
Asks user to guess a random number. Uses loop to make user guess until correct */
public class GamePlay {

    private Players player;
    
    public static void main(String[] args) {

        GamePlay game = new GamePlay();
        Scanner scanner = new Scanner(System.in);

        Hosts host = new Hosts("Bob", "Barker");
        host.randomizeNum();

        System.out.println("Please enter your first name.");
        String firstName = scanner.nextLine();
        System.out.println("Would you like to enter a last name? (y/n)");
        String lastName = scanner.nextLine();

        if (lastName.equalsIgnoreCase("y")) {
            System.out.println("Please enter your last name.");
            lastName = scanner.nextLine();
            game.player = new Players(firstName, lastName);
        } else {
            lastName = "";
            game.player = new Players(firstName);
        }

        Turn turn = new Turn();
        boolean sameNumber = false;
        while (sameNumber == false) {
            sameNumber = turn.takeTurn(game.player, host);
        }
    }
}