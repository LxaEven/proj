import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class startPanel extends JPanel {

    public startPanel(JPanel mainPanel) {

        setLayout(new BorderLayout());
        // Title Label
        JLabel titleLabel = new JLabel("Student Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setPreferredSize(new Dimension(100, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        JButton tologin = new JButton("next");
        tologin.addActionListener((ActionEvent e) -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "login");
        });
        add(tologin, BorderLayout.SOUTH);
    
    }
}
