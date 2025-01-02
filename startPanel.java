import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class startPanel extends JPanel {

    public startPanel(JPanel mainPanel) {

        setLayout(new BorderLayout());
        // Title Label
        JLabel titleLabel = new JLabel("Click to continue...", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "login");
            }
        });
        // Add the startPanel to the mainPanel
        mainPanel.add(this, "start");
    
    }
}
