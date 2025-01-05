import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class student extends JPanel {

    public student(JPanel mainPanel){
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JButton ViewScore = new JButton("View Score");
        ViewScore.setFont(new Font("Arial", Font.BOLD, 15));
        ViewScore.setPreferredSize(new Dimension(800, 50));
        ViewScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewScore");
            }
        });

        JButton ViewCourse = new JButton("View Course");
        ViewCourse.setFont(new Font("Arial", Font.BOLD, 15));
        ViewCourse.setPreferredSize(new Dimension(800, 50));
        ViewCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewCourse");
            }
        });

        JButton ChangePassword = new JButton("Change Password");
        ChangePassword.setFont(new Font("Arial", Font.BOLD, 15));
        ChangePassword.setPreferredSize(new Dimension(800, 50));
        ChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ChangePassword");
            }
        });

        JButton Logout = new JButton("Logout");
        Logout.setFont(new Font("Arial", Font.BOLD, 15));
        Logout.setPreferredSize(new Dimension(800, 50));
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
        CloseProgram.setFont(new Font("Arial", Font.BOLD, 15));
        CloseProgram.setPreferredSize(new Dimension(800, 50));
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
                    System.exit(0);
                }
            }
        });
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(ViewScore, gbc);
        gbc.gridy++;
        buttonPanel.add(ViewCourse, gbc);
        gbc.gridy++;
        buttonPanel.add(ChangePassword, gbc);
        gbc.gridy++;
        buttonPanel.add(Logout, gbc);
        gbc.gridy++;
        buttonPanel.add(CloseProgram, gbc);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
