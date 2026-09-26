/* Numbers class generates a random number with a setter and 
a getter. It also has a method to compare the random number with 
a user guess with an output message to the user depending on the 
result of the comparison.
 */
public class Phrases {
    private static String gamePhrase;
    private String playingPhrase;
    
    public String getGamePhrase() {
        return gamePhrase;
    }
    public static void setGamePhrase(String gamePhrase) {
        Phrases.gamePhrase = gamePhrase;
    }
    public String getPlayingPhrase() {
        return playingPhrase;
    }
    public void setPlayingPhrase(String playingPhrase) {
        this.playingPhrase = playingPhrase;
    }
    public void makePlayingPhrase() {
        playingPhrase = "";
        for (int i=0; i<gamePhrase.length(); i++) {

            if (gamePhrase.charAt(i) == ' ') {
                playingPhrase += " ";
            } else {
                playingPhrase += "_";
            }
        }
    }
    public boolean findLetters(String singleLetter) throws MultipleLettersException {
        if (singleLetter.length() > 1) {
            throw new MultipleLettersException();
        }
        int index = gamePhrase.indexOf(singleLetter);
        while (index != -1) {
            playingPhrase = playingPhrase.substring(0, index)
            + gamePhrase.substring(index, index + 1)
            + playingPhrase.substring(index + 1);

            index = gamePhrase.indexOf(singleLetter, index + 1);
        }
            System.out.println(playingPhrase);

            if (!playingPhrase.contains("_")) {
                System.out.println("Correct!");
                return true;
            } else {
                return false;
            }
        }
    }
    /*public boolean compareNumber(int guess) {
    if (guess == randomNum) {
        System.out.println("Congratulations, you guessed the number!");
        return true;
    } else if (guess > randomNum) {
        System.out.println("I'm sorry. That guess was too high.");
        return false;
    } else {
        System.out.println("I'm sorry. That guess was too low.");
        return false;
    }
}*/
