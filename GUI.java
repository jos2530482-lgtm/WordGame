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

        ArrayList<String> currentPlayers = new ArrayList<>();
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Current Players: ");
        JLabel hostLabel = new JLabel("Host: ");
        JLabel newPhraseLabel = new JLabel("New Phrase: ");
        JButton newPlayerButton = new JButton("Add Player");
        JButton newHostButton = new JButton("Set Host");

        aFrame.setLayout (new FlowLayout());
        aFrame.add(label);
        aFrame.add(newPlayerButton);
        aFrame.add(hostLabel);
        aFrame.add(newHostButton);
        aFrame.add(newPhraseLabel);
        JButton newPhraseButton = new JButton("Set New Phrase");
        aFrame.add(newPhraseButton);
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
                    currentPlayers.add(playerName);
                    label.setText("Current Players: " + String.join(", ", currentPlayers));
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
                Hosts currentHost = new Hosts(firstName, lastName, true);
                hostLabel.setText("Host: " + currentHost.getNameFirst() + " " + currentHost.getNameLast());

                String phrase = JOptionPane.showInputDialog("Enter the phrase for this game.");
                if (phrase == null || phrase.isEmpty()) {
                    return;
                }
                Phrases.setGamePhrase(phrase);
                Phrases gamePlay = new Phrases();
                gamePlay.makePlayingPhrase();
                newPhraseLabel.setText("New Phrase: " + gamePlay.getPlayingPhrase());
            }
        });

        aFrame.setVisible(true);
    }
}