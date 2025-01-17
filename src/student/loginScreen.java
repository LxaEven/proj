package student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class loginScreen extends JPanel {

    public loginScreen(JPanel mainPanel) {

        setLayout(new BorderLayout());
        
        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JLabel titleLabel = new JLabel("Log in as:", SwingConstants.CENTER);
        titleLabel.setBounds(20, 10, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        

        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(100, 30));
        adminButton.setFocusPainted(false);
        JButton studentButton = new JButton("Student");
        studentButton.setPreferredSize(new Dimension(100, 30));
        studentButton.setFocusPainted(false);

        
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Admin");
            }
        });
        
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Login.showLoginScreen();
                // if(Login.loginSuccess){
                    JOptionPane.showMessageDialog(mainPanel, "Login sucessfully");
                    CardLayout c3 = (CardLayout) mainPanel.getLayout();
                    c3.show(mainPanel, "student");
                //}
                
            }
        });
        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setPreferredSize(new Dimension(100, 30));
        CloseProgram.setFocusPainted(false);
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
        gbc.gridy++;
        buttonPanel.add(studentButton, gbc);
        gbc.gridy++;
        buttonPanel.add(CloseProgram, gbc);
        JPanel LogoPanel = new JPanel(new GridBagLayout());
        LogoPanel.setBackground(Color.CYAN);
        // {
        //     private Image backgroundImage = new ImageIcon("image\\chromakophia.jpg").getImage();

        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        //     }
        // };
        LogoPanel.setPreferredSize(new Dimension(400, 200));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        LogoPanel.add(logoLabel, gbc);
        
        add(buttonPanel, BorderLayout.CENTER);
        add(LogoPanel, BorderLayout.WEST);       
    }

}
