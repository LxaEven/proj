package login;
import javax.swing.*;
import java.awt.*;

public class ProjectPanel extends JPanel {
    public ProjectPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        JLabel welcomeLabel = new JLabel("Welcome to student management system...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.BLUE);

        add(welcomeLabel, BorderLayout.CENTER);
    }
}