import java.util.Scanner;

public class Hosts extends Person {

    public Hosts(String firstName, String lastName) {
        super(firstName, lastName);

        Scanner input = new Scanner(System.in);
        System.out.println(getNameFirst() + ", Enter a phrase for the game: ");
        String phrase = input.nextLine();

        Phrases.setGamePhrase(phrase);
        Phrases gamePlay = new Phrases();
        gamePlay.makePlayingPhrase();
    }
}