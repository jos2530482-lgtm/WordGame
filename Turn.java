import java.util.Scanner;
public class Turn {

private int win = 50;
private int lose = 50;

    public boolean takeTurn(Players player, Hosts host) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(host.getNameFirst() + " " + host.getNameLast() +
        ": " + player.getNameFirst() + " " + player.getNameLast());

        System.out.println("Please enter a number between 0 and 100.");
        int guess = scanner.nextInt();

        Numbers randomNumber = new Numbers();
        boolean sameNumber = randomNumber.compareNumber(guess);

        if (sameNumber) {
            player.setMoney(player.getMoney() + win);
            System.out.println(player);
            return true;
        } else {
            player.setMoney(player.getMoney() - lose);
            System.out.println(player);
            return false;
        }
    }
}