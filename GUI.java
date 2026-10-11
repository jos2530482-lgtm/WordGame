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

        ArrayList<Players> currentPlayers = new ArrayList<>();
        final Hosts[] currentHost = new Hosts[1];

        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Current Players: ");
        JLabel hostLabel = new JLabel("Host: ");
        JLabel newPhraseLabel = new JLabel("New Phrase: ");
        JButton newPlayerButton = new JButton("Add Player");
        JButton newHostButton = new JButton("Set Host");
        JButton startGameButton = new JButton("Start Game");
        JButton newPhraseButton = new JButton("Set New Phrase");
        

        aFrame.setLayout (new FlowLayout());
        aFrame.add(label);
        aFrame.add(newPlayerButton);
        aFrame.add(hostLabel);
        aFrame.add(newHostButton);
        aFrame.add(newPhraseLabel);
        aFrame.add(newPhraseButton);
        aFrame.add(startGameButton);

        startGameButton.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (currentPlayers.isEmpty()) {
                    JOptionPane.showMessageDialog(aFrame, "add a player");
                    return;
                }
                if (currentHost[0] == null) {
                    JOptionPane.showMessageDialog(aFrame, "set a host");
                    return;
                }
                Phrases gamePlay = new Phrases();
                if (gamePlay.getGamePhrase() == null || gamePlay.getGamePhrase().isEmpty()) {
                    JOptionPane.showMessageDialog(aFrame, "set game phrase");
                    return;
                }
                gamePlay.makePlayingPhrase();
                boolean gameWon = false;
                while (!gameWon) {
                    for (Players player : currentPlayers) {
                        String letter = JOptionPane.showInputDialog(
                            aFrame, currentHost[0].getNameFirst() + " " + 
                            currentHost[0].getNameLast() + "'s turn for " + 
                            player.getNameFirst() + "\n\nPhrase: " + gamePlay.getPlayingPhrase() + 
                            "\n\nEnter a letter:");
                            if (letter == null) {
                                return;
                            }
                                letter = letter.trim();
                                if (!letter.matches("[a-zA-Z]")) {
                                    JOptionPane.showMessageDialog(aFrame, "Please enter a valid letter.");
                                    continue;
                                }
                                try {
                                    boolean phraseComplete = gamePlay.findLetters(letter);

                                    if (gamePlay.getLetterFound()) {
                                        JOptionPane.showMessageDialog(aFrame, "Correct! The letter " + letter + " is in the phrase. \n\n" + " Phrase: " + gamePlay.getPlayingPhrase());
                                    } else {
                                        JOptionPane.showMessageDialog(aFrame, "Sorry, the letter " + letter + " is not in the phrase. \n\n" + "Phrase: " + gamePlay.getPlayingPhrase());
                                    }
                                    newPhraseLabel.setText("Phrase: " + gamePlay.getPlayingPhrase());
                                    if (phraseComplete) {
                                        JOptionPane.showMessageDialog(aFrame, "Congratulations, " + "! You solved it.");
                                        gameWon = true;
                                        break;
                                    }
                                    }catch (MultipleLettersException ex) {
                                    JOptionPane.showMessageDialog(aFrame,ex.getMessage());
                               }
                    }
                    }
            }
        });
        newPhraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String newPhrase = JOptionPane.showInputDialog("Enter new phrase:");
                if (newPhrase != null && !newPhrase.isEmpty()) {
                    Phrases.setGamePhrase(newPhrase);
                    Phrases gamePlay = new Phrases();
                    gamePlay.makePlayingPhrase();
                    newPhraseLabel.setText("New Phrase: " + gamePlay.getPlayingPhrase());
                }
            }
        });
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String playerName = JOptionPane.showInputDialog("Enter player name:");
                if (playerName != null && !playerName.isEmpty()) {
                    Players newPlayer = new Players(playerName);
                    currentPlayers.add(newPlayer);
                    label.setText("Players: " + currentPlayers);
                }
            }
        });
        newHostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String firstName = JOptionPane.showInputDialog("Enter host first name:");
                if (firstName == null || firstName.isEmpty()) {
                    return;
                }
                String lastName = JOptionPane.showInputDialog("Enter host last name:");
                if (lastName == null || lastName.isEmpty()) {
                    return;
                }
                currentHost[0] = new Hosts(firstName, lastName, true);
                hostLabel.setText("Host: " + currentHost[0].getNameFirst() + " " + currentHost[0].getNameLast());

                String phrase = JOptionPane.showInputDialog("Enter the phrase for this game.");
                if (phrase == null || phrase.isEmpty()) {
                    return;
                }
                Phrases.setGamePhrase(phrase);
                Phrases gamePlay = new Phrases();
                if (gamePlay.getGamePhrase() == null || gamePlay.getGamePhrase().isEmpty()) {
                    JOptionPane.showMessageDialog(aFrame, "set game phrase");
                    return;
                }
                gamePlay.makePlayingPhrase();
                newPhraseLabel.setText("New Phrase: " + gamePlay.getPlayingPhrase());
            }
        });

        aFrame.setVisible(true);
    }
}