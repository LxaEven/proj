import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewScore extends JPanel {
    public ViewScore(JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student > View Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        try {
            Class.forName("student_data");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_data", "root", "Web#11*03");
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery("select * from student");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "student");
            }
        });
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
