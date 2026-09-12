import java.util.Scanner;
public class Turn {
    public boolean takeTurn(Players player, Hosts host) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(host.getNameFirst() + " " + host.getNameLast() +
        " is taking their turn against " + player.getNameFirst() + " " + player.getNameLast());
        System.out.println("Please enter a number between 0 and 100.");
        int guess = scanner.nextInt();
        Numbers randomNumber = new Numbers();
        randomNumber.generateNumber();
        boolean sameNumber = randomNumber.compareNumber(guess);
        if (sameNumber) {
            System.out.println(player.getNameFirst() + " " + player.getNameLast() +
            " has won the game!");
            return true;
        } else {
            System.out.println(player.getNameFirst() + " " + player.getNameLast() +
            " has lost the game.");
            return false;
        }
    }