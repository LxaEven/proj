import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends JPanel {
    public ChangePassword(JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JTextField oldPassword = new JTextField();
        oldPassword.setPreferredSize(new Dimension(200, 30));

        JLabel newPasswordLabel = new JLabel("New Password:");
        JTextField newPassword = new JTextField();
        newPassword.setPreferredSize(new Dimension(200, 30));

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPasswordValue = oldPassword.getText();
                String newPasswordValue = newPassword.getText();
                
                String url = "jdbc:mysql://localhost:3306/mydb";
                String username = "root";
                String password = "Web#11*03";
                try (Connection conn = DriverManager.getConnection(url, username, password);
                     Statement stmt = conn.createStatement()) {
                    String query = "UPDATE student SET password = '" + newPasswordValue + "' WHERE password = '" + oldPasswordValue + "'";
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(mainPanel, "Password changed successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error: " + ex.getMessage());
                }
            }
        });

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
        ChangePassword.setBackground(Color.BLACK);
        ChangePassword.setForeground(Color.WHITE);
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
                    c4.show(mainPanel, "login");
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
                    c4.show(mainPanel, "ChangePassword");
                } else {
                    System.out.println("Program ended");
                    System.exit(0);
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
        buttonPanel.setBackground(Color.CYAN);
        add(buttonPanel, BorderLayout.WEST);
        JPanel formPanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(oldPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(oldPassword, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(newPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(newPassword, gbc);
        gbc.gridy++;
        formPanel.add(submitButton, gbc);
        add(formPanel, BorderLayout.CENTER);
    }
}
