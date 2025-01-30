package Adminsrc;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.DBConnect;

public class ViewAdmin extends JPanel {
    private Connection connection;

    public ViewAdmin(Connection connection) {
        this.connection = connection;
    }
    JPanel ViewAdminPanel() {
         // Table Setup
         DefaultTableModel tableModel = new DefaultTableModel();
         tableModel.addColumn("Admin ID");
         tableModel.addColumn("Admin Name");
         tableModel.addColumn("Admin Gender");
         tableModel.addColumn("Admin Email");
         tableModel.addColumn("Admin Password");

         JTable table = new JTable(tableModel);
 
         JPanel panel = new JPanel();
         panel.setLayout(new BorderLayout());
         panel.add(table, BorderLayout.SOUTH);
         JScrollPane scrollPane = new JScrollPane(table);
         panel.add(scrollPane, BorderLayout.CENTER);

         //======================================================================

        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setFillsViewportHeight(true);
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 13));
        searchLabel.setPreferredSize(new Dimension(50, 30));
        JTextField searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(600, 30));
        String placeholder = "Enter your search here...";
        searchField.setText(placeholder);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 13));
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setFocusPainted(false);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                if (searchText.trim().length() == 0) {
                    rowSorter.setRowFilter(null); 
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); 
                }
            }
        });

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                searchField.setText(placeholder);
            }
        });

        
    
        JPanel SearchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        SearchPanel.add(searchLabel, gbc);
        gbc.gridx++;
        SearchPanel.add(searchField, gbc);
        gbc.gridx++;
        SearchPanel.add(searchButton, gbc);

        panel.add(SearchPanel, BorderLayout.NORTH);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                try (Connection conn = DBConnect.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM admin_account")) {

                    tableModel.setRowCount(0);

                    // Populate table with data
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{
                        rs.getString("admin_id"),
                        rs.getString("admin_name"),
                        rs.getString("admin_gender"),
                        rs.getString("admin_email"),
                        rs.getString("admin_password")
                        });
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        return panel;
    }
}
