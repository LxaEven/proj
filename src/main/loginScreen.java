package main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class loginScreen extends JPanel {

    public loginScreen(MainPanel mainPanel) {

        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JLabel titleLabel = new JLabel("Log in as:", SwingConstants.CENTER);
        titleLabel.setBounds(20, 10, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        

        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(200, 50));
        adminButton.setFont(new Font("Arial", Font.PLAIN, 18));
        adminButton.setFocusPainted(false);
        adminButton.setBackground(new Color(144, 238, 144));
        JButton studentButton = new JButton("Student");
        studentButton.setPreferredSize(new Dimension(200, 50));
        studentButton.setFont(new Font("Arial", Font.PLAIN, 18));
        studentButton.setBackground(new Color(144, 238, 144));
        studentButton.setFocusPainted(false);

        
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.showScreen("AdminLogin");
            }
        });
        
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    mainPanel.showScreen("Login");
                
                
            }
        });
        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setPreferredSize(new Dimension(200, 50));
        CloseProgram.setFont(new Font("Arial", Font.PLAIN, 18));
        CloseProgram.setFocusPainted(false);
        CloseProgram.setBackground(new Color(255, 102, 102));
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
        buttonPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
            JPanel loginPanel = new JPanel(new GridBagLayout());
            loginPanel.setBackground(new Color(173, 216, 230));
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            loginPanel.add(adminButton, gbc);
            gbc.gridx++;
            loginPanel.add(studentButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(titleLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(loginPanel, gbc);
        gbc.gridy++;
        buttonPanel.add(CloseProgram, gbc);

        JPanel LogoPanel = new JPanel(new GridBagLayout());
        LogoPanel.setBackground(Color.CYAN);
        LogoPanel.setPreferredSize(new Dimension(400, 200));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        LogoPanel.add(logoLabel, gbc);
        
        add(buttonPanel, BorderLayout.CENTER);
        add(LogoPanel, BorderLayout.WEST);
        
    }

}
