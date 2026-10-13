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
    public String getPrize() {
        return physicalPrizes[getRandomPrize()];
    }

    @Override
    public int displayWinnings(Players player, boolean correct) {
        
        String prize = getPrize();
        if (correct) {
            System.out.println(player + " wins a " + prize);
        } else {
            System.out.println(player + " does not win a " + prize);
        }
        return 0;
    }
}