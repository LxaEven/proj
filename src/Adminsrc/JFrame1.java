package Adminsrc;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import login.*;
import main.DBConnect;



public final class JFrame1 extends JFrame{
    public JFrame1() throws SQLException {

            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                    UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
                    break;
                }
            }
        initializeDatabaseConnection();
        initialize();
    }

    public Connection connection;

    private JPanel mainPanel;
    private JPanel midPanel; 
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private JLabel profileLabel;
    private DefaultTableModel tableModel;

    // Other Panels
    private Home homePanel;
    private Enroll enrollPanel;
    private view_sts viewPanel;
    private StudentUpdate updatePanel;
    private delete deletePanel;


    // Home
    private JMenu homeMenu;
    private JMenuItem newMenuItem;
    private JMenuItem profileMenuItem;
    private JMenuItem logOutMenuItem;

    //Student
    private JMenu studentMenu;
    private JMenuItem viewStudent;
    private JMenuItem addStudent;
    private JMenuItem updateStudent;
    private JMenuItem deleteStudent;

    //course
    private JMenu courseMenu;
    private JMenuItem viewCourse;
    private JMenuItem addCourse;
    private JMenuItem updateCourse;
    private JMenuItem deleteCourse;

    //department
    private JMenu departmentMenu;
    private JMenuItem viewDepartment;
    private JMenuItem addDepartment;
    private JMenuItem updateDepartment;
    private JMenuItem deleteDepartment;

    //course
    private JMenu scoreMenu;
    private JMenuItem viewScore;
    private JMenuItem addScore;
    private JMenuItem updateScore;


    //setting
    private JMenu settingMenu;
    private JMenuItem ViewAminMenu;
    private JMenuItem AddAminMenu;
    private JMenuItem DeleteAminMenu;
    private JMenuItem exitMenu;




    private void initializeDatabaseConnection() {
        try {
            DBConnect.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }
 
    public void initialize() throws SQLException {
        setTitle("Admin");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        mainPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        mainPanel = new JPanel(new BorderLayout());
        cardLayout = new CardLayout(10,10);
        midPanel = new JPanel(cardLayout); // Initialize midPanel with CardLayout

       // mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        mainPanel.setBackground(Color.blue);

        // Middle panel (Content Area)
        midPanel.setBackground(Color.black);
        midPanel.setPreferredSize(new Dimension(600, 600));
        mainPanel.add(midPanel, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.green);
        menuBar.setLayout(new GridLayout(1, 5));


        homePanel = new Home(connection);
        enrollPanel = new Enroll(connection);
        viewPanel = new view_sts(connection);
        deletePanel = new delete(connection);
        updatePanel = new StudentUpdate(connection);

        // Home Panel
        midPanel.add(homePanel.homePanel(), "Home");
        midPanel.add(new ViewProfile(connection).createViewProfilePanel(), "ViewProfile");
        


        // Student Panel
        midPanel.add(viewPanel.ViewStudentPanel(), "ViewSts");
        midPanel.add(enrollPanel.getEnrollPanel(), "Enroll");
        midPanel.add(updatePanel.createUpdateStudentPanel(), "UpdateSts");
        midPanel.add(deletePanel.createDeleteStudentPanel(), "DeleteSts");

        // Course Panel
        midPanel.add(new View_courses(connection).ViewCoursePanel(), "ViewCourse");
        midPanel.add(new AddCourse(connection).getAddCoursePanel(), "AddCourse");
        midPanel.add(new UpdateCourse(connection).getUpdateCoursePanel(), "UpdateCourse");
        midPanel.add(new DeleteCourse(connection).createDeleteCoursePanel(), "DeleteCourse");

        // Department Panel
        midPanel.add(new View_department(connection).ViewDepartmentPanel(), "ViewDepartment");
        midPanel.add(new AddDepartment(connection).getAddDepartmentPanel(), "AddDepartment");
        midPanel.add(new UpdateDepartment(connection).getUpdateDepartmentPanel(), "UpdateDepartment");
        midPanel.add(new DeleteDepartment(connection).getDeleteDepartmentPanel(), "DeleteDepartment");
     
        // Score Panel
        midPanel.add(new ViewScore(connection).ViewScorePanel(), "ViewScore");
        midPanel.add(new AddScore(connection).getScorePanel(), "AddScore");
        midPanel.add(new UpdateScore(connection).createUpdateScorePanel(), "UpdateScore");

        // Setting Panel
        midPanel.add(new ViewAdmin(connection).ViewAdminPanel(), "ViewAdmin");
        midPanel.add(new AddAdmin(connection).getAddAdminPanel(), "AddAdmin");
        midPanel.add(new DeleteAdmin(connection).getDeleteAdminPanel(), "DeleteAdmin");


        

        // Home
        homeMenu =  new JMenu("Home");
        ImageIcon homeIcon = new ImageIcon("Icon/home.png");
        homeMenu.setIcon(homeIcon);

        newMenuItem = new JMenuItem("Home");
        newMenuItem.addActionListener(e -> cardLayout.show(midPanel, "Home"));
        profileMenuItem = new JMenuItem("Profile");
        profileMenuItem.addActionListener(e -> cardLayout.show(midPanel, "ViewProfile"));
        logOutMenuItem = new JMenuItem("Log out");
        logOutMenuItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(midPanel,"Are you sure you want to logout?","Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                MainPanel logout = new MainPanel();
                logout.setVisible(true);


            }
        });

        // Student
        studentMenu =  new JMenu("Student");
        ImageIcon studentIcon = new ImageIcon("Icon/student.png");
        studentMenu.setIcon(studentIcon);
        viewStudent = new JMenuItem("View");
        viewStudent.addActionListener(e -> cardLayout.show(midPanel, "ViewSts"));
        
        addStudent = new JMenuItem("Enroll");
        addStudent.addActionListener(e -> cardLayout.show(midPanel, "Enroll"));
        updateStudent = new JMenuItem("Update");
        updateStudent.addActionListener(e -> cardLayout.show(midPanel, "UpdateSts"));
        deleteStudent = new JMenuItem("Delete");
        deleteStudent.addActionListener(e -> cardLayout.show(midPanel, "DeleteSts"));



        // Course
        courseMenu =  new JMenu("Course");
        ImageIcon courseIcon = new ImageIcon("Icon/course.png");
        courseMenu.setIcon(courseIcon);
        viewCourse = new JMenuItem("View");
        viewCourse.addActionListener(e -> cardLayout.show(midPanel, "ViewCourse"));
        addCourse = new JMenuItem("Add");
        addCourse.addActionListener(e -> cardLayout.show(midPanel, "AddCourse"));
        updateCourse = new JMenuItem("Update");
        updateCourse.addActionListener(e -> cardLayout.show(midPanel, "UpdateCourse"));
        deleteCourse = new JMenuItem("Delete");
        deleteCourse.addActionListener(e -> cardLayout.show(midPanel, "DeleteCourse"));

        // Department
        departmentMenu =  new JMenu("Department");
        ImageIcon departmentIcon = new ImageIcon("Icon/department.png");
        departmentMenu.setIcon(departmentIcon);
        viewDepartment = new JMenuItem("View");
        viewDepartment.addActionListener(e -> cardLayout.show(midPanel, "ViewDepartment"));
        addDepartment = new JMenuItem("Add");
        addDepartment.addActionListener(e -> cardLayout.show(midPanel, "AddDepartment"));
        updateDepartment = new JMenuItem("Update");
        updateDepartment.addActionListener(e -> cardLayout.show(midPanel, "UpdateDepartment"));
        deleteDepartment = new JMenuItem("Delete");
        deleteDepartment.addActionListener(e -> cardLayout.show(midPanel, "DeleteDepartment"));


        // Score
        scoreMenu =  new JMenu("Score");
        ImageIcon scoreIcon = new ImageIcon("Icon/score.png");
        scoreMenu.setIcon(scoreIcon);
        viewScore = new JMenuItem("View");
        viewScore.addActionListener(e -> cardLayout.show(midPanel, "ViewScore"));
        addScore = new JMenuItem("Add"); 
        addScore.addActionListener(e -> cardLayout.show(midPanel, "AddScore"));
        updateScore = new JMenuItem("Update");
        updateScore.addActionListener(e -> cardLayout.show(midPanel, "UpdateScore"));


        // Setting
        settingMenu =  new JMenu("Setting");
        ImageIcon settingIcon = new ImageIcon("Icon/course.png");
        settingMenu.setIcon(settingIcon);
        ViewAminMenu = new JMenuItem("All Admin");
        ViewAminMenu.addActionListener(e -> cardLayout.show(midPanel, "ViewAdmin"));
        AddAminMenu = new JMenuItem("Add Admin");
        AddAminMenu.addActionListener(e -> cardLayout.show(midPanel, "AddAdmin"));
        DeleteAminMenu = new JMenuItem("Delete Admin");
        DeleteAminMenu.addActionListener(e -> cardLayout.show(midPanel, "DeleteAdmin"));
        exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener(e -> {
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
                System.out.println("Program ended");
                System.exit(0);
            }
        });


        JMenuItem[] item = {homeMenu, studentMenu, newMenuItem, courseMenu, profileMenuItem, logOutMenuItem, viewStudent, addStudent, updateStudent, deleteStudent,viewCourse, updateCourse, addCourse, deleteCourse, viewDepartment, updateDepartment, addDepartment, deleteDepartment, ViewAminMenu, AddAminMenu, DeleteAminMenu, exitMenu, viewScore, addScore, updateScore, settingMenu, scoreMenu, departmentMenu, courseMenu};
        for (JMenuItem item1 : item) {
            item1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            item1.setFont(new Font("Arial", Font.PLAIN, 15));
        }
        ImageIcon[] icons = {homeIcon, studentIcon, courseIcon, departmentIcon, settingIcon, scoreIcon};
        for (ImageIcon icon : icons) {
            icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        }


        homeMenu.add(newMenuItem);
        homeMenu.add(profileMenuItem); 
        homeMenu.add(logOutMenuItem);

        studentMenu.add(viewStudent);
        studentMenu.add(addStudent);
        studentMenu.add(updateStudent); 
        studentMenu.add(deleteStudent);

        courseMenu.add(viewCourse);
        courseMenu.add(addCourse);
        courseMenu.add(updateCourse);
        courseMenu.add(deleteCourse);

        departmentMenu.add(viewDepartment);
        departmentMenu.add(addDepartment);
        departmentMenu.add(updateDepartment);
        departmentMenu.add(deleteDepartment);

        settingMenu.add(ViewAminMenu);
        settingMenu.add(AddAminMenu);
        settingMenu.add(DeleteAminMenu);
        settingMenu.add(exitMenu);

        scoreMenu.add(viewScore);
        scoreMenu.add(addScore);
        scoreMenu.add(updateScore);

        menuBar.add(homeMenu);
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(departmentMenu);
        menuBar.add(scoreMenu);
        menuBar.add(settingMenu);
        add(menuBar, BorderLayout.SOUTH);




        // Top panel
        JPanel top_panel = new JPanel();
        top_panel.setBackground(Color.white);
        top_panel.setLayout(new GridLayout(1, 3));
        top_panel.setLayout(new FlowLayout(FlowLayout.LEADING,10,0));



        // Profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(200, 200, 255));
        profilePanel.setPreferredSize(new Dimension(350, 150));
        profilePanel.setLayout(new GridLayout(1,2));

        // Profile picture
        profilePanel.setLayout(new FlowLayout(FlowLayout.LEADING,20,25));
        JPanel profileImage = new JPanel();
        profileImage.setBackground(new Color(200, 200, 255));
        profileImage.setPreferredSize(new Dimension(100, 100));
        // JLabel profileLabel = new JLabel(profileIcon);
        profileLabel = new JLabel(showAdminImage());
        String imagePath = AdminLogin.showAdminImage(this);
        profileLabel.setIcon(getCircularImageIcon(imagePath, 95));
        profileImage.add(profileLabel);


        // User name Panel
        JPanel adminPanel = new JPanel();
        adminPanel.setBackground(new Color(200, 200, 255));
        adminPanel.setPreferredSize(new Dimension(190, 90));
        adminPanel.setLayout(new GridLayout(2,1));

        JLabel AdminLabel = new JLabel("Admin");
        AdminLabel.setFont(new Font("Arial", Font.BOLD, 25));
        AdminLabel.setForeground(Color.blue);
        AdminLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel userNameLabel = new JLabel(fetchUsername());
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userNameLabel.setForeground(Color.blue);
        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        adminPanel.add(AdminLabel,BorderLayout.CENTER);
        adminPanel.add(userNameLabel);
        profilePanel.add(profileImage, BorderLayout.CENTER);
        profilePanel.add(adminPanel);

        top_panel.add(profilePanel, BorderLayout.WEST);

        JLabel label1 = new JLabel("Student Management System");
        label1.setFont(new Font("Arial", Font.BOLD, 35));
        label1.setForeground(Color.blue);

        ImageIcon labelIcon = new ImageIcon("Icon/Logo.png");
        labelIcon.setImage(labelIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        label1.setIcon(labelIcon);
        label1.setIconTextGap(20);

        top_panel.add(label1, BorderLayout.CENTER);



        // Change font size for Menu and MeniItem
        Font f = new Font("Times New Romand", Font.PLAIN, 20);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("CheckBoxMenuItem.font", f);
        UIManager.put("RadioButtonMenuItem.font", f);


        


        

        add(top_panel, BorderLayout.NORTH);
        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);
 

    }
    private String fetchUsername() {
        String username = AdminLogin.showProfileName(this);
    
        return username;
    }
    private ImageIcon showAdminImage() {
        ImageIcon image = null;
        String imagePath = AdminLogin.showAdminImage(this);
        image = new ImageIcon(imagePath);
        return image;
    }

    public void refreshProfileImage() {
        String newImagePath = AdminLogin.showAdminImage(this); // Fetch updated image
        profileLabel.setIcon(getCircularImageIcon(newImagePath, 95)); // Update icon
        profileLabel.repaint(); // Force repaint
    }

    public ImageIcon getCircularImageIcon(String imagePath, int size) {
        try {
            // Load image
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Resize the image
            Image resizedImage = originalImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            BufferedImage circleBuffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        
            Graphics2D g2 = circleBuffer.createGraphics();
        
            // Enable anti-aliasing for smooth edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
            // Create a circular shape
            g2.setClip(new Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(resizedImage, 0, 0, null);

            // Draw black border
            g2.setClip(null);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(3)); // Adjust the thickness of the border
            g2.draw(new Ellipse2D.Float(1.5f, 1.5f, size - 3, size - 3)); // Adjust the position to fit the border within the image

            g2.dispose();

            return new ImageIcon(circleBuffer);
        } catch (IOException e) {
                e.printStackTrace();
            return null;
        }
    }

    
    

    

}

