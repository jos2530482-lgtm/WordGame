import java.util.Scanner;

public class GamePlay {

    private Person person;
    public static void main(String[] args) {

        GamePlay game = new GamePlay();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your first name.");
        String firstName = scanner.nextLine();
        System.out.println("Would you like to enter a last name? (y/n)");
        String lastName = scanner.nextLine();

        if (lastName == "y" || lastName == "Y") {
            System.out.println("Please enter your last name.");
            lastName = scanner.nextLine();
            game.person = new Person(firstName, lastName);
        } else {
            lastName = "";
            game.person = new Person(firstName);
        }

        Numbers randomNumber = new Numbers();
        randomNumber.generateNumber();

        boolean sameNumber = false;
        while (sameNumber == false) {
            System.out.println("guess a number between 0 and 100.");
            int guess = scanner.nextInt();
            sameNumber = randomNumber.compareNumber(guess);
        }
    }
}

