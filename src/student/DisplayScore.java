package student;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import login.MainPanel;
import main.*;

public class DisplayScore extends JPanel {
    private MainPanel mainPanel;
    private GridBagConstraints gbc = new GridBagConstraints();

    public DisplayScore(JPanel mainPanel) {
        setLayout(new BorderLayout());
        studentDisplay();
    }

    public void studentDisplay() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student INNER JOIN department ON student.department = department.department_id")) {

            
                tableModel.addColumn("No");
                tableModel.addColumn("ID");
                tableModel.addColumn("First Name");
                tableModel.addColumn("Last Name");
                tableModel.addColumn("Gender");
                tableModel.addColumn("Date of Birth");
                tableModel.addColumn("Department");
                tableModel.addColumn("Score");

            
            while (rs.next()) {
                int No = tableModel.getRowCount() + 1;
                int id = rs.getInt("student_id");
                String studentfirstName = rs.getString("student_firstname");
                String studentlastName = rs.getString("student_lastname");
                String studentGender = rs.getString("gender");
                String studentBirth = rs.getString("student_birth");
                String studentdepartment = rs.getString("department_name");
                String studentScore = rs.getString("student_score");
        tableModel.addRow(new Object[]{No, "e2022"+String.format("%03d", id), studentfirstName, studentlastName, studentGender,  studentBirth, studentdepartment, studentScore}
        
        );
    }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Error: " + e.getMessage());
        }

        
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.CYAN);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setBackground(new Color(173, 216, 230));
        table.setFillsViewportHeight(true);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(600, 30));
        String placeholder = "Enter your search here...";
        searchField.setText(placeholder);

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText().trim();
                if (searchText.isEmpty() || searchText.equals(placeholder)) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholder)) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText(placeholder);
                }
            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (searchText.isEmpty() || searchText.equals(placeholder)) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(new Color(173, 216, 230));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(searchLabel, gbc);
        gbc.gridx++;
        searchPanel.add(searchField, gbc);
        gbc.gridx++;
        searchPanel.add(searchButton, gbc);

        JPanel tablePanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        tablePanel.add(searchPanel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablePanel.add(scrollPane, gbc);
        tablePanel.setBackground(new Color(173, 216, 230));
        add(tablePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
