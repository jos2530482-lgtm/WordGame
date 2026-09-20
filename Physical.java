/* Physical class introduces physical prizes to be won instead the cash prizes. */
public class Physical implements Award {
    //array of prizes to be won.
    private String[] physicalPrizes = {
        "toaster", "gameboard game", "VCR", "earbuds", "mystery box"
    };
    //draws random number and displays player name and prize and whether win or lose.
    public int getRandomPrize() {
        int randomNumber = (int) (Math.random() * physicalPrizes.length);
        return randomNumber;
    }
    @Override
    public int displayWinnings(Players player, boolean correct) {
        int randomPrizeIndex = getRandomPrize();
        if (correct) {
            System.out.println(player + " wins a " + physicalPrizes[randomPrizeIndex]);
        } else {
            System.out.println(player + " does not win a " + physicalPrizes[randomPrizeIndex]);
        }
        return 0;
    }
}