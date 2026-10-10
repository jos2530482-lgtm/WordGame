import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {
    
    public static void main(String[] args) {
        
        JFrame aframe = new JFrame("Word Game");
        aframe.setSize(350, 200);
        aframe.setVisible(true);
        aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Current Players: ");
        aframe.add(label);
    }
}