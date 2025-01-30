package Adminsrc;

import java.awt.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import login.*;
import main.DBConnect;


public class ViewProfile extends JPanel {
    private Connection connection;
    public ViewProfile(Connection connection) {
        this.connection = connection;
    }

    public JPanel createViewProfilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.CYAN);

        // Fonts for UI elements
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Input fields
        JTextField txtAdminId = new JTextField();
        JTextField txtAdminName = new JTextField();
        JComboBox<String> cbAdminGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtAdminEmail = new JTextField();
        JTextField txtAdminPassword = new JTextField();
        JTextField txtProfileImage = new JTextField();

        // Buttons
        JButton btnEdit = new JButton("Edit");
        JButton btnUpdate = new JButton("Update");
        JButton btnCancel = new JButton("Cancel");
        JButton btnBrowseImage = new JButton("Insert Image Profile");
        JButton btnRefresh = new JButton("Refresh");

        // Set fonts for input fields and buttons
        JTextField[] inputFields = {txtAdminId, txtAdminName, txtAdminEmail, txtAdminPassword, txtProfileImage};
        for (JTextField field : inputFields) {
            cbAdminGender.setFont(inputFont);
            field.setFont(inputFont);
        }

        for (JTextField field : inputFields) {
            cbAdminGender.setEnabled(false);
            field.setEditable(false); // Disable input fields initially
        }

        btnEdit.setFont(buttonFont);
        btnEdit.setBackground(Color.CYAN);
        btnCancel.setFont(buttonFont);
        btnCancel.setBackground(Color.RED);
        btnCancel.setEnabled(false); 
        btnUpdate.setFont(buttonFont);
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setEnabled(false);
        btnBrowseImage.setFont(buttonFont);
        btnBrowseImage.setBackground(Color.ORANGE);
        btnBrowseImage.setEnabled(false);
        btnRefresh.setFont(buttonFont);
        btnRefresh.setBackground(Color.BLUE);

        // Create and style labels
        JLabel lblAdminId = new JLabel("Admin ID:");
        JLabel lblAdminName = new JLabel("Name:");
        JLabel lblAdminGender = new JLabel("Gender:");
        JLabel lblAdminEmail = new JLabel("Email:");
        JLabel lblAdminPassword = new JLabel("Password:");
        JLabel lblProfileImage = new JLabel("Image:");

        JLabel[] labels = {lblAdminId, lblAdminName, lblAdminGender, lblAdminEmail, lblAdminPassword, lblProfileImage};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Add components to the panel
        panel.add(lblAdminId);
        panel.add(txtAdminId);
        panel.add(lblAdminName);
        panel.add(txtAdminName);
        panel.add(lblAdminGender);
        panel.add(cbAdminGender);
        panel.add(lblAdminEmail);
        panel.add(txtAdminEmail);
        panel.add(lblAdminPassword);
        panel.add(txtAdminPassword);
        panel.add(lblProfileImage);
        panel.add(txtProfileImage);

        panel.add(btnEdit);
        panel.add(btnUpdate);
        panel.add(btnCancel); 
        panel.add(btnBrowseImage);
        panel.add(btnRefresh);


        
        // Add ActionListener for "Edit" button
        btnEdit.addActionListener(e -> {
            for (JTextField field : inputFields) {
                cbAdminGender.setEnabled(true);
                field.setEditable(true);
            }
            btnUpdate.setEnabled(true); 
            btnEdit.setEnabled(false); 
            btnCancel.setEnabled(true);
            btnBrowseImage.setEnabled(true);
        });

        // Add ActionListener for "Cancel" button
        btnCancel.addActionListener(e -> {
            for (JTextField field : inputFields) {
                cbAdminGender.setEnabled(false);
                field.setEditable(false);
            }
            btnUpdate.setEnabled(false); 
            btnEdit.setEnabled(true); 
            btnCancel.setEnabled(false);
            btnBrowseImage.setEnabled(false);
        });


        btnRefresh.addActionListener(e -> {
            JFrame1 currentFrame = (JFrame1) SwingUtilities.getWindowAncestor(panel);
            if (currentFrame != null) {
                currentFrame.dispose(); // Close the current frame
            }
            JFrame1 newFrame = null;
            try {
                newFrame = new JFrame1();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } // Create a new frame
            newFrame.refreshProfileImage(); // Refresh the profile image in the new frame
            newFrame.setVisible(true); // Show the new frame
        });

        // Auto Show Info
            String AdminShow = AdminLogin.showAdminInfo(null);
            String Showquery = "SELECT * FROM admin_account WHERE admin_email = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(Showquery)) {
                pst.setString(1, AdminShow);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    txtAdminId.setText(rs.getString("admin_id"));
                    txtAdminName.setText(rs.getString("admin_name"));
                    cbAdminGender.setSelectedItem(rs.getString("admin_gender"));
                    txtAdminEmail.setText(rs.getString("admin_email"));
                    txtAdminPassword.setText(rs.getString("admin_password"));
                    txtProfileImage.setText(rs.getString("profile_image"));

                } 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }


        // Update Action
        btnUpdate.addActionListener(e -> {
            String query = "UPDATE admin_account SET admin_name = ?, admin_gender = ?, admin_email = ?, admin_password = ?, profile_image = ? WHERE admin_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, txtAdminName.getText());
                pst.setString(2, cbAdminGender.getSelectedItem().toString());
                pst.setString(3, txtAdminEmail.getText());
                pst.setString(4, txtAdminPassword.getText());
                pst.setString(5, txtProfileImage.getText());
                pst.setString(6,  txtAdminId.getText());

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(panel, "Admin updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No records were updated!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }


        });

        JLabel lblImagePreview = new JLabel(); // To show the image preview
        lblImagePreview.setPreferredSize(new Dimension(70, 70)); // Set image size
        lblImagePreview.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblImagePreview);
        

        btnBrowseImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a Profile Picture");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                
                // Set the image in JLabel
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                lblImagePreview.setIcon(imageIcon);
        
                // Set the image path in the text field (to save in DB)
                txtProfileImage.setText(imagePath);
            }
        });

        




        return panel;
    }


}
