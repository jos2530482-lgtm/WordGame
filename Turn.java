import java.util.Scanner;
public class Turn {

    public boolean takeTurn(Players player, Hosts host) {

        Scanner input = new Scanner(System.in);
        System.out.println(host.getNameFirst() + " " + host.getNameLast() +
        ": " + player.getNameFirst() + " " + player.getNameLast());

        Phrases phrase = new Phrases();
        
        System.out.println("Phrase = " + phrase.getPlayingPhrase());
        System.out.println("Guess one letter at a time.");
        String letter = input.nextLine();

        try{
            if (!letter.matches("[a-zA-Z]")) {
                System.out.println("Enter one letter only at a time.");
                return false;
            }
            boolean samePhrase = phrase.findLetters(letter);
            if (samePhrase) {
                Money cashAward = new Money();
                int winnings = cashAward.displayWinnings(player, true);
                player.setMoney(player.getMoney() + winnings);
                System.out.println(player);
                return true;
            }
        } catch (MultipleLettersException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}