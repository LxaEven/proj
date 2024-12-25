import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class login extends JPanel {

    public login(JPanel mainPanel) {

        setLayout(new BorderLayout());
        // Title Label
        JLabel titleLabel = new JLabel("Student Management System", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 100, 30);
        add(titleLabel);

        

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
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Teacher");
            }
        });
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Student");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(adminButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(studentButton);
        add(buttonPanel, BorderLayout.CENTER);
        
    }

}
