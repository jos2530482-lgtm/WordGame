import java.util.Scanner;
public class Turn {

    public boolean takeTurn(Players player, Hosts host) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(host.getNameFirst() + " " + host.getNameLast() +
        ": " + player.getNameFirst() + " " + player.getNameLast());

        System.out.println("Please enter a number between 0 and 100.");
        int guess = scanner.nextInt();

        Numbers randomNumber = new Numbers();
        boolean sameNumber = randomNumber.compareNumber(guess);
        //if player wins random number is drawn to see if player wins cash or prize.
        if (sameNumber) {
            int prizeType = (int) (Math.random() * 2);
            if (prizeType == 0) {
                Money moneyAward = new Money();
                int winnings = moneyAward.displayWinnings(player, true);
                player.setMoney(player.getMoney() + winnings);
            } else {
                Physical physicalAward = new Physical();
                int winnings = physicalAward.displayWinnings(player, true);
                player.setMoney(player.getMoney() + winnings);
            }
            System.out.println(player);
            return true;
        //if player loses random number is drawn to see if player loses cash or prize.
        } else {
            int prizeType = (int) (Math.random() * 2);
            if (prizeType == 0) {
                Money moneyAward = new Money();
                int winnings = moneyAward.displayWinnings(player, false);
                player.setMoney(player.getMoney() + winnings);
            } else {
                Physical physicalAward = new Physical();
                int winnings = physicalAward.displayWinnings(player, false);
                player.setMoney(player.getMoney() + winnings);
            }
            System.out.println(player);
            return false;
        }
    }
}