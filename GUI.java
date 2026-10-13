import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class GUI {

    public static void main(String[] args) {

        JFrame aFrame = new JFrame("Word Game");
        aFrame.setSize(500, 500);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setLayout(new FlowLayout());

        ArrayList<Players> currentPlayers = new ArrayList<>();
        Money moneyAward = new Money();
        Physical physicalAward = new Physical();
        final Hosts[] currentHost = new Hosts[1];

        JLabel label = new JLabel("Current Players: ");
        JLabel hostLabel = new JLabel("Host: ");
        JLabel newPhraseLabel = new JLabel("New Phrase: ");

        JButton newPlayerButton = new JButton("Add Player");
        JButton newHostButton = new JButton("Set Host");
        JButton startGameButton = new JButton("Start Game");
        JButton newPhraseButton = new JButton("Set New Phrase");

        aFrame.add(label);
        aFrame.add(newPlayerButton);
        aFrame.add(hostLabel);
        aFrame.add(newHostButton);
        aFrame.add(newPhraseLabel);
        aFrame.add(newPhraseButton);
        aFrame.add(startGameButton);

        // ADD PLAYER BUTTON
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String playerName = JOptionPane.showInputDialog(
                    aFrame, "Enter player name:"
                );

                if (playerName != null && !playerName.trim().isEmpty()) {
                    Players newPlayer = new Players(playerName.trim());
                    currentPlayers.add(newPlayer);
                    label.setText("Players: " + currentPlayers);
                }
            }
        });

        // button that allows the user to set a host and enter a phrase for the game
        newHostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String firstName = JOptionPane.showInputDialog(
                    aFrame, "Enter host first name:"
                );

                if (firstName == null || firstName.trim().isEmpty()) {
                    return;
                }

                String lastName = JOptionPane.showInputDialog(
                    aFrame, "Enter host last name:"
                );

                if (lastName == null || lastName.trim().isEmpty()) {
                    return;
                }

                currentHost[0] = new Hosts(
                    firstName.trim(), lastName.trim(), true
                );

                hostLabel.setText(
                    "Host: " + currentHost[0].getNameFirst()
                    + " " + currentHost[0].getNameLast()
                );

                String phrase = JOptionPane.showInputDialog(
                    aFrame, "Enter the phrase for this game:"
                );

                if (phrase == null || phrase.trim().isEmpty()) {
                    return;
                }

                Phrases.setGamePhrase(phrase.trim());

                Phrases gamePlay = new Phrases();
                gamePlay.makePlayingPhrase();

                newPhraseLabel.setText(
                    "New Phrase: " + gamePlay.getPlayingPhrase()
                );
            }
        });

        // button that allows the user to set a new phrase before starting the game
        newPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String newPhrase = JOptionPane.showInputDialog(
                    aFrame, "Enter new phrase:"
                );

                if (newPhrase == null || newPhrase.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                        aFrame, "Phrase cannot be empty."
                    );
                    return;
                }

                Phrases.setGamePhrase(newPhrase.trim());

                Phrases gamePlay = new Phrases();
                gamePlay.makePlayingPhrase();

                newPhraseLabel.setText(
                    "New Phrase: " + gamePlay.getPlayingPhrase()
                );
            }
        });

        // button that starts the game
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if (currentPlayers.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        aFrame, "Please add a player first."
                    );
                    return;
                }

                if (currentHost[0] == null) {
                    JOptionPane.showMessageDialog(
                        aFrame, "Please set a host first."
                    );
                    return;
                }

                boolean playAnotherRound = true;

                while (playAnotherRound) {

                    Phrases gamePlay = new Phrases();

                    if (gamePlay.getGamePhrase() == null
                            || gamePlay.getGamePhrase().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            aFrame, "Please set a game phrase first."
                        );
                        return;
                    }

                    gamePlay.makePlayingPhrase();

                    newPhraseLabel.setText(
                        "Phrase: " + gamePlay.getPlayingPhrase()
                    );

                    boolean gameWon = false;

                    // keeps the game running until a player guesses the phrase correctly.
                    while (!gameWon) {

                        for (Players player : currentPlayers) {

                            String letter = JOptionPane.showInputDialog(
                                aFrame,
                                currentHost[0].getNameFirst() + " "
                                + currentHost[0].getNameLast()
                                + "'s turn for "
                                + player.getNameFirst()
                                + "\n\nPhrase: "
                                + gamePlay.getPlayingPhrase()
                                + "\n\nEnter one letter:"
                            );

                            if (letter == null) {
                                return;
                            }

                            letter = letter.trim();

                            if (!letter.matches("[a-zA-Z]")) {
                                JOptionPane.showMessageDialog(
                                    aFrame,
                                    "Please enter exactly one letter."
                                );
                                continue;
                            }

                            try {

                                boolean phraseComplete =
                                    gamePlay.findLetters(letter);

                                boolean correct =
                                    gamePlay.getLetterFound();

                                // uses random to give prize or money reward.
                                if (Math.random() < 0.5) {

                                    int change =
                                        moneyAward.displayWinnings(
                                            player, correct
                                        );

                                    player.setMoney(
                                        player.getMoney() + change
                                    );

                                    if (correct) {
                                        JOptionPane.showMessageDialog(
                                            aFrame,
                                            player.getNameFirst()
                                            + " wins $50!\nBalance = $"
                                            + player.getMoney()
                                        );
                                    } else {
                                        JOptionPane.showMessageDialog(
                                            aFrame,
                                            player.getNameFirst()
                                            + " loses $50.\nBalance = $"
                                            + player.getMoney()
                                        );
                                    }

                                } else {

                                    String prize =
                                        physicalAward.getPrize();

                                    if (correct) {
                                        JOptionPane.showMessageDialog(
                                            aFrame,
                                            player.getNameFirst()
                                            + " wins a " + prize + "!"
                                        );
                                    } else {
                                        JOptionPane.showMessageDialog(
                                            aFrame,
                                            player.getNameFirst()
                                            + " does not win a physical prize."
                                        );
                                    }
                                }

                                // displays the result of the guess to the phrase.
                                if (correct) {
                                    JOptionPane.showMessageDialog(
                                        aFrame,
                                        "Correct! The letter " + letter
                                        + " is in the phrase.\n\nPhrase: "
                                        + gamePlay.getPlayingPhrase()
                                    );
                                } else {
                                    JOptionPane.showMessageDialog(
                                        aFrame,
                                        "Sorry, the letter " + letter
                                        + " is not in the phrase.\n\nPhrase: "
                                        + gamePlay.getPlayingPhrase()
                                    );
                                }

                                newPhraseLabel.setText(
                                    "Phrase: " + gamePlay.getPlayingPhrase()
                                );

                                // checks guess against phrase.
                                if (phraseComplete) {
                                    JOptionPane.showMessageDialog(
                                        aFrame,
                                        "Congratulations, "
                                        + player.getNameFirst()
                                        + "! You solved it."
                                    );

                                    gameWon = true;
                                    break;
                                }

                            } catch (MultipleLettersException ex) {

                                JOptionPane.showMessageDialog(
                                    aFrame, ex.getMessage()
                                );
                            }
                        }
                    }

                    // asks user if they want to play again and set new phrase.
                    int playAgain = JOptionPane.showConfirmDialog(
                        aFrame,
                        "Do you want to play again?",
                        "Play Again",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (playAgain == JOptionPane.YES_OPTION) {

                        String newPhrase = JOptionPane.showInputDialog(
                            aFrame, "Enter a new phrase:"
                        );

                        if (newPhrase == null
                                || newPhrase.trim().isEmpty()) {

                            JOptionPane.showMessageDialog(
                                aFrame,
                                "No new phrase entered. Ending game."
                            );

                            playAnotherRound = false;

                        } else {

                            Phrases.setGamePhrase(newPhrase.trim());
                        }

                    } else {

                        playAnotherRound = false;

                        JOptionPane.showMessageDialog(
                            aFrame, "Thanks for playing!");
                    }
                }
            }
        });

        aFrame.setVisible(true);
    }
}