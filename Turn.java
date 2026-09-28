import java.util.Scanner;
public class Turn {

    public boolean takeTurn(Players player, Hosts host) {

        Scanner input = new Scanner(System.in);
        System.out.println(host.getNameFirst() + " " + host.getNameLast() +
        ": " + player.getNameFirst() + " " + player.getNameLast() + 
        " please guess a letter.");

        Phrases phrase = new Phrases();
        
        System.out.println("Phrase = " + phrase.getPlayingPhrase());
        System.out.println("Guess one letter at a time.");
        String letter = input.nextLine();

        try{
            if (!letter.matches("[a-zA-Z]+")) {
                System.out.println("Enter a single letter.");
                return false;
            }
            boolean samePhrase = phrase.findLetters(letter);
            if (phrase.getLetterFound()) { 
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
            }
            if (samePhrase) {
                System.out.println("Congratulations winner!");
                return true;
            }
            return false;
            
        } catch (MultipleLettersException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}