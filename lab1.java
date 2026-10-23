import javax.swing.*;
import java.awt.*;

public class lab1 {
    public static void main (String[] args){
        JFrame frame = new JFrame("Display Image");

        ImageIcon image = new ImageIcon("campusImage.jpeg");

        Image img = image.getImage();

        Image resizedImage = img.getScaledInstance(500, 400, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(resizedImage));

        frame.add(label);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
