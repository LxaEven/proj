import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
public class project_i3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student management system");
        frame.setSize(10*30, 10*20);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Hello World");
        label.setBounds(10*10, 5*10, 10*10, 25);
        panel.add(label);
        JButton button = new JButton("Enter");
        button.setBounds(100, 100, 80, 25);
        panel.add(button);

        // Add action listener to the button
        button.addActionListener(e -> System.out.println("Button clicked"));


        // Add key listener to the frame to listen for Enter key press
        frame.getRootPane().setDefaultButton(button);
        frame.setVisible(true);

        

    }
}