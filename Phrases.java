/* Phrases class 
 */
public class Phrases {
    private static String gamePhrase;
    private static String playingPhrase;
    private boolean letterFound;
    
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
        Phrases.playingPhrase = playingPhrase;
    }
    public boolean getLetterFound() {
        return letterFound;
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
        letterFound = false;
        int index = gamePhrase.toLowerCase().indexOf(singleLetter.toLowerCase());
        while (index != -1) {
            playingPhrase = playingPhrase.substring(0, index)
            + gamePhrase.substring(index, index + 1)
            + playingPhrase.substring(index + 1);
            
            letterFound = true;
            index = gamePhrase.toLowerCase().indexOf(
                singleLetter.toLowerCase(), index + 1);
        }
        System.out.println(playingPhrase);
        return !playingPhrase.contains("_");
    }
}

        /*     index = gamePhrase.indexOf(singleLetter, index + 1);
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
