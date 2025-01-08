import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class loginScreen extends JPanel {

    public loginScreen(JPanel mainPanel) {

        setLayout(new BorderLayout());
        
        
        JLabel titleLabel = new JLabel("Log in as:", SwingConstants.CENTER);
        titleLabel.setBounds(20, 10, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        

        

        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(100, 30));
        JButton studentButton = new JButton("Student");
        studentButton.setPreferredSize(new Dimension(100, 30));

        
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Admin");
            }
        });
        
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login.showLoginScreen();
                if(Login.loginSuccess){
                    CardLayout c3 = (CardLayout) mainPanel.getLayout();
                    c3.show(mainPanel, "student");
                }
                
            }
        });
        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setPreferredSize(new Dimension(100, 30));
        CloseProgram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Do you want to Exit the program?", 
                    "Exit the program", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.NO_OPTION) {
                    CardLayout c4 = (CardLayout) mainPanel.getLayout();
                    c4.show(mainPanel, "login");
                } else {
                    System.exit(0);
                }
            }
        });

        JPanel buttonPanel = new JPanel( new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(titleLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(adminButton, gbc);
        gbc.gridx++;
        buttonPanel.add(studentButton, gbc);
        JPanel buttonPanel2 = new JPanel( new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel2.add(CloseProgram, gbc);
        
        add(buttonPanel, BorderLayout.CENTER);
        add(buttonPanel2, BorderLayout.SOUTH);        
    }

}