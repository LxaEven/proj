import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewScore extends JPanel {
    public ViewScore(JPanel mainPanel) {

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "Web#11*03";

        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student > View Score");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JLabel tableLabel = new JLabel("Student Score", SwingConstants.CENTER);
        tableLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(tableLabel, BorderLayout.CENTER);
        DefaultTableModel tableModel = new DefaultTableModel() {
            
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        JTable table = new JTable(tableModel);

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {

            
            tableModel.addColumn("student_id");
            tableModel.addColumn("student_firstname");
            tableModel.addColumn("student_lastname");
            tableModel.addColumn("phone_number");
            tableModel.addColumn("student_birth");
            tableModel.addColumn("student_score");

            
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String studentfirstName = rs.getString("student_firstname");
                String studentlastName = rs.getString("student_lastname");
                String studentPhoneNumber = rs.getString("phone_number");
                String studentBirth = rs.getString("student_birth");
                float studentScore = rs.getFloat("student_score");
                tableModel.addRow(new Object[]{id, studentfirstName, studentlastName, studentPhoneNumber, studentBirth, studentScore});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Error: " + e.getMessage());
        }
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        
        JScrollPane scrollPanel = new JScrollPane(table);
        add(scrollPanel, BorderLayout.CENTER);


        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "student");
                
            }
        });
        JPanel buttonPanel = new JPanel( new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        buttonPanel.add(backButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
