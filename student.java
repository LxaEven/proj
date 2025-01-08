import javax.swing.*;
import com.formdev.flatlaf.*;
import java.awt.*;
import java.awt.event.*;

public class student extends JPanel {

    public student(JPanel mainPanel){
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font("Arial", Font.BOLD, 13));
        ViewProfile.setPreferredSize(new Dimension(160, 30));
        ViewProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewProfile");
            }
        });


        JButton ViewScore = new JButton("View Score");
        ViewScore.setFont(new Font("Arial", Font.BOLD, 13));
        ViewScore.setPreferredSize(new Dimension(160, 30));
        ViewScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewScore");
            }
        });


        JButton ViewCourse = new JButton("View Course");
        ViewCourse.setFont(new Font("Arial", Font.BOLD, 13));
        ViewCourse.setPreferredSize(new Dimension(160, 30));
        ViewCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewCourse");
            }
        });


        JButton ChangePassword = new JButton("Change Password");
        ChangePassword.setFont(new Font("Arial", Font.BOLD, 13));
        ChangePassword.setPreferredSize(new Dimension(160, 30));
        ChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ChangePassword");
            }
        });


        JButton Logout = new JButton("Logout");
        Logout.setFont(new Font("Arial", Font.BOLD, 13));
        Logout.setPreferredSize(new Dimension(160, 30));
        Logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Do you want to log out?", 
                    "Log Out", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    CardLayout c4 = (CardLayout) mainPanel.getLayout();
                    c4.show(mainPanel, "loginScreen");
                } else {
                    System.out.println("Stayed logged in");
                }
            }
        });


        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setFont(new Font("Arial", Font.BOLD, 13));
        CloseProgram.setPreferredSize(new Dimension(160, 30));
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
                    c4.show(mainPanel, "student");
                } else {
                    System.out.println("Program ended");
                    System.exit(0);
                }
            }
        });

        JButton darkMode = new JButton("Dark Mode");
        darkMode.setFont(new Font("Arial", Font.BOLD, 12));
        darkMode.setPreferredSize(new Dimension(130, 30));
        darkMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel( new FlatDarkLaf() );
                    SwingUtilities.updateComponentTreeUI(mainPanel);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }
        });

        JButton lightMode = new JButton("Light Mode");
        lightMode.setFont(new Font("Arial", Font.BOLD, 12));
        lightMode.setPreferredSize(new Dimension(130, 30));
        lightMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel( new FlatLightLaf() );
                    SwingUtilities.updateComponentTreeUI(mainPanel);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }
        });


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(titleLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(ViewProfile, gbc);
        gbc.gridy++;
        buttonPanel.add(ViewScore, gbc);
        gbc.gridy++;
        buttonPanel.add(ViewCourse, gbc);
        gbc.gridy++;
        buttonPanel.add(ChangePassword, gbc);
        gbc.gridy++;
        buttonPanel.add(Logout, gbc);
        gbc.gridy++;
        buttonPanel.add(CloseProgram, gbc);
        buttonPanel.setBackground(Color.GRAY);
        add(buttonPanel, BorderLayout.WEST);

        JPanel ModePanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(20, 20, 20, 20);
        ModePanel.setPreferredSize(new Dimension(180, 50));
        gbc.gridy = 0;
        gbc.gridx = 0;
        ModePanel.add(darkMode, gbc);
        gbc.gridx++;
        ModePanel.add(lightMode, gbc);
        ModePanel.setBackground(Color.GRAY);
        add(ModePanel, BorderLayout.NORTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.GRAY);
        eastPanel.setPreferredSize(new Dimension(50, 300));
        add(eastPanel, BorderLayout.EAST);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(200, 50));
        add(southPanel, BorderLayout.SOUTH);
    
    }
}
