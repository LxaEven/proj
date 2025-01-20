import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Home extends JPanel {
    private Connection connection;

    public Home(Connection connection) {
        this.connection = connection;
    }
    JPanel homePanel() {
 
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        // JPanel StudentPanel = new JPanel();
        // StudentPanel.setLayout(new BorderLayout());
        // StudentPanel.setBackground(Color.cyan);
        // StudentPanel.setPreferredSize(new Dimension(200, 150));
        // StudentPanel.setBorder(BorderFactory. createLineBorder(Color. black));

        // JLabel StudentLabel = new JLabel("Student");
        // StudentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // StudentLabel.setHorizontalAlignment(JLabel.CENTER);
        // StudentPanel.add(StudentLabel, BorderLayout.SOUTH);

        // JLabel Sts_pic = new JLabel();
        // ImageIcon icon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/student_total.png");
        // Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        // Sts_pic.setIcon(new ImageIcon(img));
        // StudentPanel.add(Sts_pic, BorderLayout.EAST);

        // panel.add(StudentPanel, BorderLayout.CENTER);

        // Database values
        int totalStudents = getStudentCount();
        // int totalFemales = getFemaleCount();
        // int totalCourses = getCourseCount();

        // Create Panels
        panel.add(createPanel("Students", totalStudents));
        // panel.add(createPanel("Females", totalFemales));
        // panel.add(createPanel("Courses", totalCourses));

        


        return panel;
    }

    // Create panel for dashboard
    private static JPanel createPanel(String title, int count) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 200));
        panel.setBackground(new Color(200, 200, 255));
        panel.setLayout(new BorderLayout());

        JLabel countLabel = new JLabel(String.valueOf(count), JLabel.CENTER);
        countLabel.setFont(new Font("Arial", Font.BOLD, 60));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel IconLabel = new JLabel();
        ImageIcon icon = new ImageIcon("image//student_total.png");
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        IconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        IconLabel.setIcon(new ImageIcon(img));

        JButton moreInfoButton = new JButton("More info");
        moreInfoButton.addActionListener(e -> {
            CardLayout c4 = (CardLayout) panel.getLayout();
            c4.show(panel, "View");
        });

        panel.add(IconLabel, BorderLayout.EAST);
        panel.add(countLabel, BorderLayout.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(moreInfoButton, BorderLayout.SOUTH);

        return panel;
    }

    // Get total student count
    private static int getStudentCount() {
         String query = "SELECT COUNT(*) AS total FROM students";
        try (Connection conn = DBConnect.getConnection();
        Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // // Get total female count
    // private static int getFemaleCount() {
    //     String query = "SELECT COUNT(*) AS total FROM Students WHERE gender = 'Female'";
    //     try (Connection conn = DBConnect.getConnection();
    //          Statement stmt = conn.createStatement();
    //          ResultSet rs = stmt.executeQuery(query)) {
    //         if (rs.next()) {
    //             return rs.getInt("total");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return 0;
    // }

    // // Get total course count
    // private static int getCourseCount() {
    //     String query = "SELECT COUNT(DISTINCT course) AS total FROM Students";
    //     try (Connection conn = DBConnect.getConnection();
    //          Statement stmt = conn.createStatement();
    //          ResultSet rs = stmt.executeQuery(query)) {
    //         if (rs.next()) {
    //             return rs.getInt("total");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return 0;
    // }
}
