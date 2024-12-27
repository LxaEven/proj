import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class startPanel extends JPanel {

    public startPanel(JPanel mainPanel) {

        setLayout(new BorderLayout());
        // Title Label
        JLabel titleLabel = new JLabel("Student Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JButton tologin = new JButton("next");
        tologin.setPreferredSize(new Dimension(100, 30));
        tologin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "login");
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(tologin);
        add(buttonPanel, BorderLayout.SOUTH);
    
    }
}
