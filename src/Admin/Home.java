import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Home extends JPanel {
    private Connection connection;
    private JPanel panel; // Panel to hold dashboard elements
    private JPanel container; // Separate panel for Refresh button

    public Home(Connection connection) {
        this.connection = connection;
    }
    JPanel homePanel() {
        panel = new JPanel();
        panel.setBackground(Color.white);

        container = new JPanel(new BorderLayout());  // Separate container for the Refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(120, 40));
        refreshButton.addActionListener(e -> refreshDashboard());

        container.add(refreshButton, BorderLayout.SOUTH);

        refreshDashboard(); // Populate dashboard initially

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(container, BorderLayout.SOUTH); // Keep Refresh button separate

        return mainPanel;
    }

    private void refreshDashboard() {
        panel.removeAll(); // Clear existing panels

        // Fetch updated data
        int totalStudents = getStudentCount();
        int totalFemales = getFemaleCount();
        int totalCourses = getCourseCount();
        int totalDepartments = getDepartmentCount();

        // Recreate dashboard panels with updated data
        panel.add(createPanel("Students", totalStudents, "D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/student_total.png"));
        panel.add(createPanel("Females", totalFemales, "D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/Female.png"));
        panel.add(createPanel("Courses", totalCourses, "D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/courses.png"));
        panel.add(createPanel("Departments", totalDepartments, "D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/department.png"));

        panel.revalidate(); // Recalculate layout
        panel.repaint();   // Refresh the UI
    }



    // Create panel for dashboard
    private static JPanel createPanel(String title, int count, String iconPath) {
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
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        IconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        IconLabel.setIcon(new ImageIcon(img));

        JButton moreInfoButton = new JButton("More info");
        moreInfoButton.addActionListener(e -> {
            CardLayout c = (CardLayout) panel.getLayout();
            c.show(panel, "View");
        });

        panel.add(IconLabel, BorderLayout.EAST);
        panel.add(countLabel, BorderLayout.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(moreInfoButton, BorderLayout.SOUTH);

        return panel;
    }

    // Get total student count
    private static int getStudentCount() {
         String query = "SELECT COUNT(*) AS total FROM student";
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

    // Get total female count
    private static int getFemaleCount() {
        String query = "SELECT COUNT(*) AS total FROM student WHERE gender = 'Female'";
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

    // Get total course count
    private static int getCourseCount() {
        String query = "SELECT COUNT(subject) AS total FROM course";
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

    // Get total department count
    private static int getDepartmentCount() {
        String query = "SELECT COUNT(*) AS total FROM department";
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
}
