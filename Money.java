/* This Money class now contains the money win/lose fields and displays
    the player and money won or lost. */
public class Money implements Award {
    private int win = 50;
    private int lose = 50;

    @Override
    public int displayWinnings(Players player, boolean correct) {
        if (correct) {
            System.out.println(player + " wins $" + win);
            return win;
        } else {
            System.out.println(player + " loses $" + lose);
            return -lose;
        }
    }
}