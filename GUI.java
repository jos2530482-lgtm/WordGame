import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class GUI {

    public static void playSound(String soundFile) {
        try {
            File file = new File("week10audio/" + soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
    public static void animateSweat(JFrame frame) {

    ImageIcon sweatIcon = new ImageIcon("week10pics/sweat.jpg");

    JLabel sweatLabel = new JLabel(sweatIcon);

    sweatLabel.setBounds(-150, 200,
        sweatIcon.getIconWidth(), sweatIcon.getIconHeight());

    frame.add(sweatLabel);
    frame.setComponentZOrder(sweatLabel, 0);

    Timer timer = new Timer(15, new ActionListener() {
        int x = -150;

        @Override
        public void actionPerformed(ActionEvent e) {
            x += 5;
            sweatLabel.setLocation(x, 200);

            if (x > frame.getWidth()) {
                ((Timer) e.getSource()).stop();
                frame.remove(sweatLabel);
                frame.repaint();
            }
        }
    });

    timer.start();
}
    public static void main(String[] args) {

        JFrame aFrame = new JFrame("Word Game");
        aFrame.setSize(500, 500);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setLayout(new FlowLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic('A');
        JMenuItem layoutItem = new JMenuItem("Layout");
        JMenuItem attributionItem = new JMenuItem("Attribution");
        
        
        JMenuItem addPlayerItem = new JMenuItem("Add Player");
        JMenuItem addHostItem = new JMenuItem("Set Host");
        
        aboutMenu.add(layoutItem);
        aboutMenu.add(attributionItem);
        gameMenu.setMnemonic('G');
        gameMenu.add(addPlayerItem);
        
        gameMenu.add(addHostItem);
        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        aFrame.setJMenuBar(menuBar);

        ArrayList<Players> currentPlayers = new ArrayList<>();
        Money moneyAward = new Money();
        Physical physicalAward = new Physical();
        final Hosts[] currentHost = new Hosts[1];

        JLabel label = new JLabel("Current Players: ");
        JLabel hostLabel = new JLabel("Host: ");
        JLabel newPhraseLabel = new JLabel("New Phrase: ");
        JLabel prizeImageLabel = new JLabel();
        JTextArea messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        JCheckBox saveMessageCheckBox = new JCheckBox("Save Messages");
        saveMessageCheckBox.setToolTipText(
            "Check to save messages in the text area."
            + " Uncheck to clear messages when a new message is displayed."
        );

        // keeps messages if box is checked.
        java.util.function.Consumer<String> displayMessage = (message) -> {
            if (saveMessageCheckBox.isSelected()) {
                messageArea.append(message + "\n");
            } else {
                messageArea.setText(message + "\n");
            }
        };

        JButton newPhraseButton = new JButton("Set New Phrase");
        JButton startGameButton = new JButton("Start Game");

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        JPanel phrasePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel startGamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startGamePanel.setPreferredSize(new java.awt.Dimension(350, 100));

        infoPanel.setLayout(new GridLayout(5, 1));
        infoPanel.add(label);
        infoPanel.add(hostLabel);
        infoPanel.add(newPhraseLabel);

        phrasePanel.add(newPhraseLabel);

        aFrame.add(infoPanel);
        aFrame.add(phrasePanel);
        aFrame.add(prizeImageLabel);
        aFrame.add(newPhraseButton);
        startGamePanel.add(startGameButton);
        aFrame.add(startGamePanel);
        aFrame.add(saveMessageCheckBox);
        aFrame.add(scrollPane);

        // button that adds a new player to current players.
        addPlayerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String playerName = JOptionPane.showInputDialog(
                    aFrame, "Enter player name:"
                );
                if (playerName != null && !playerName.trim().isEmpty()) {
                    Players newPlayer = new Players(playerName.trim());
                    currentPlayers.add(newPlayer);
                    StringBuilder playerList = new StringBuilder("Current Players: ");
                    for (int i = 0; i < currentPlayers.size(); i++) {
                        if (i > 0) {
                            playerList.append(", ");
                        }
                        playerList.append(currentPlayers.get(i).getNameFirst());
                    }
                    label.setText(playerList.toString());
                }
            }
        });

        // button that allows the user to set a host and enter a phrase for the game
        addHostItem.addActionListener(new ActionListener() {
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
                if (lastName == null) {
                    return;
                }
                lastName = lastName.trim();
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
                    displayMessage.accept(
                        "Phrase cannot be empty.\n"
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
        // layout menu. displays reason for layouts.
        layoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(
                    aFrame,
                    "I wanted the display to be more vertical than horizontal, so I used FlowLayout for the main"
                    + "frame and the phrase panel. I wanted the phrase and the start game button to be centered, "
                    + "so I used FlowLayout for those panels. I wanted the player and host information to be displayed"
                    + " in a list format, so I used GridLayout for the info panel.",
                    "Layout",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        // Attribution list.
        attributionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(
                    aFrame,
                    "Image Attributions:\n\n"
                    + "Earbuds: Beats Solo Buds - Target\n"
                    + "https://www.target.com/p/beats-solo-buds-true-wireless-bluetooth-earbuds-matte-black/-/A-92146817\n\n"
                    + "Game Board: Premium Custom Boxes\n"
                    + "https://www.premiumcustomboxes.com/board-game-boxes/\n\n"
                    + "Mystery Box: ILL Customz\n"
                    + "https://illcustomz.com/products/mystery-box/\n\n"
                    + "Toaster: Hamilton Beach - Target\n"
                    + "https://www.target.com/p/hamilton-beach-2-slice-toaster-stainless-steel/-/A-52062134\n\n"
                    + "VCR: Tape Players\n"
                    + "https://tapeplayers.com/products/vcr-bundle-play-and-digitize-vhs-to-dvd\n\n\n"
                    + "Sweat Animation: Awkward Smile Wipe Sweat Emoticon Pack\n"
                    + "Created by 588ku - PNGTree\n"
                    + "https://www.pngtree.com/freepng/awkward-smile-wipe-sweat-emoticon-pack_5935288.html\n\n"
                    + "Sound Attributions:\n\n"
                    + "Winner Sound (winner.wav)\n"
                    + "\"snd_cheer.wav\" by komanderkyle\n"
                    + "Freesound.org - CC0\n"
                    + "https://freesound.org/people/komanderkyle/sounds/482219/\n\n"
                    + "Start Game Sound (start.wav)\n"
                    + "\"let's go!.wav\" by Reitanna\n"
                    + "Freesound.org - CC0\n"
                    + "https://freesound.org/people/Reitanna/sounds/241581/\n\n"
                    + "Prize Sound (prize.wav)\n"
                    + "\"funny yay.wav\" by Reitanna\n"
                    + "Freesound.org - CC0\n"
                    + "https://freesound.org/people/Reitanna/sounds/323703/\n\n"
                    + "Incorrect Guess Sound (incorrect.wav)\n"
                    + "\"Wrong Answer Buzzer\" by -Andreas\n"
                    + "Freesound.org - CC0\n"
                    + "https://freesound.org/people/-Andreas/sounds/650842/\n\n"
                    + "Correct Guess Sound (correct.wav)\n"
                    + "\"correct.wav\" by Mudkip2016\n"
                    + "Freesound.org - CC0 1.0 Universal\n"
                    + "https://freesound.org/people/Mudkip2016/sounds/423930/",
                    "Attributions",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // button that starts the game
        startGameButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                playSound("start.wav");
                if (currentPlayers.isEmpty()) {
                    displayMessage.accept(
                        "Please add a player first.\n"
                    );
                    return;
                }
                if (currentHost[0] == null) {
                    displayMessage.accept(
                        "Please set a host first.\n"
                    );
                    return;
                }
                
                boolean playAnotherRound = true;

                while (playAnotherRound) {
                    Phrases gamePlay = new Phrases();
                    if (gamePlay.getGamePhrase() == null
                            || gamePlay.getGamePhrase().isEmpty()) {
                        displayMessage.accept(
                            "Please set a game phrase first.\n"
                        );
                        return;
                    }

                    gamePlay.makePlayingPhrase();
                    newPhraseLabel.setText(
                        "Phrase: " + gamePlay.getPlayingPhrase()
                    );
                    boolean gameWon = false;

                    // Keep playing until a player solves the phrase.
                    while (!gameWon) {
                        for (Players player : currentPlayers) {
                            String letter;
                            // Keep asking this player until input is valid.
                            while (true) {
                                letter = JOptionPane.showInputDialog(
                                    aFrame,
                                    currentHost[0].getNameFirst() + " "
                                    + currentHost[0].getNameLast()
                                    + ": player "
                                    + player.getNameFirst()
                                    + "\n\nPhrase: "
                                    + gamePlay.getPlayingPhrase()
                                    + "\n\nEnter one letter:"
                                );
                                if (letter == null) {
                                    return;
                                }
                                letter = letter.trim();
                                if (letter.matches("[a-zA-Z]")) {
                                    break;
                                }
                                displayMessage.accept(
                                    "Invalid input. Enter a single letter.\n"
                                );
                            }
                            try {
                                boolean phraseComplete =
                                    gamePlay.findLetters(letter);
                                boolean correct =
                                    gamePlay.getLetterFound();

                                // Randomly award money or a physical prize.
                                if (Math.random() < 0.5) {
                                    int change =
                                        moneyAward.displayWinnings(
                                            player, correct
                                        );
                                    player.setMoney(
                                        player.getMoney() + change
                                    );
                                    if (correct) {
                                        playSound("correct.wav");
                                        JOptionPane.showMessageDialog(
                                            aFrame,
                                            player.getNameFirst()
                                            + " wins $50!\nBalance = $"
                                            + player.getMoney()
                                        );
                                    } else {
                                        playSound("incorrect.wav");
                                        if (Math.random() < 0.5) {
                                            animateSweat(aFrame);
                                        }
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
                                    String imageFile = "";

                                    switch (prize) {
                                        case "toaster" -> imageFile = "toaster.JPG";
                                        case "gameboard game" -> imageFile = "gameBoard.JPG";
                                        case "VCR" -> imageFile = "VCR.JPG";
                                        case "earbuds" -> imageFile = "earBuds.JPG";
                                        case "mystery box" -> imageFile = "mysteryBox.JPG";
                                    }

                                    if (correct) {
                                        playSound("prize.wav");
                                        ImageIcon prizeImage = new ImageIcon(
                                            "week10pics/" + imageFile
                                        );
                                        System.out.println("Image path: week10pics/" + imageFile);
                                        System.out.println("Image loaded: " + prizeImage.getIconWidth()
                                        + " x " + prizeImage.getIconHeight());
                                        prizeImageLabel.setIcon(prizeImage);
                                        aFrame.revalidate();
                                        aFrame.repaint();

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
                                // Display the result of the guess.
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
                                // Check whether the phrase is solved.
                                if (phraseComplete) {
                                    playSound("winner.wav");
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

                                messageArea.append(ex.getMessage() + "\n");
                            }

                            // Stop giving turns once the phrase is solved.
                            if (gameWon) {
                                break;
                            }
                        }
                    }
                    // Ask whether the user wants to play another round.
                    int playAgain = JOptionPane.showConfirmDialog(
                        aFrame,
                        "Do you want to play again?",
                        "Play Again",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (playAgain == JOptionPane.YES_OPTION) {
                        messageArea.setText("");
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
                            aFrame, "Thanks for playing!"
                        );
                    }
                }
            }
        });
        aFrame.setVisible(true);
    }
}