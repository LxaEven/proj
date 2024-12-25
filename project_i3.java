import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class project_i3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student management system");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new CardLayout());
        login loginPanel = new login(mainPanel);
        startPanel startPanel = new startPanel(mainPanel);

        mainPanel.add(startPanel, "start");
        mainPanel.add(loginPanel, "login");

        frame.add(mainPanel);
        frame.setVisible(true);
        

    }
}        