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
                CardLayout c3 = (CardLayout) mainPanel.getLayout();
                c3.show(mainPanel, "student");
            }
        });
        JPanel buttonPanel = new JPanel( new FlowLayout(FlowLayout.LEADING));
        buttonPanel.add(adminButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(studentButton);
        add(buttonPanel, BorderLayout.WEST);
        
    }

}
