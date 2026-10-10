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
        
        JButton newPlayerButton = new JButton("Add Player");

        aFrame.setLayout (new FlowLayout());
        aFrame.add(label);
        aFrame.add(newPlayerButton);
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
        aFrame.setVisible(true);
    }
}