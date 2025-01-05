import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class login extends JPanel {

    public login(JPanel mainPanel) {

        setLayout(new BorderLayout());
        
        
        JLabel titleLabel = new JLabel("Log in as:", SwingConstants.CENTER);
        titleLabel.setBounds(20, 10, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        

        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(100, 30));
        JButton teacherButton = new JButton("Teacher");
        teacherButton.setPreferredSize(new Dimension(100, 30));
        JButton studentButton = new JButton("Student");
        studentButton.setPreferredSize(new Dimension(100, 30));

        // Button Actions
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Admin");
            }
        });
        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c2 = (CardLayout) mainPanel.getLayout();
                c2.show(mainPanel, "Teacher");
            }
        });
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(
                    null, 
                    "Login successful", 
                    "Log in",
                    JOptionPane.INFORMATION_MESSAGE
                ); 
                CardLayout c3 = (CardLayout) mainPanel.getLayout();
                c3.show(mainPanel, "student"); 
            }
        });

        JPanel buttonPanel = new JPanel( new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(adminButton, gbc);
        gbc.gridx++;
        buttonPanel.add(teacherButton, gbc);
        gbc.gridx++;
        buttonPanel.add(studentButton, gbc);
        
        add(buttonPanel, BorderLayout.CENTER);        
    }

}
