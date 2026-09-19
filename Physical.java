public class Physical implements Award {

    private String[] physicalPrizes = {
        "toaster", "gameboard game", "VCR", "earbuds", "mystery box"
    };

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