import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
public class project_i3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project I3");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Hello World");
        label.setBounds(100, 50, 100, 25);
        panel.add(label);

        frame.setVisible(true);

    }
}