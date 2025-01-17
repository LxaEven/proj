package login;
import javax.swing.*;

public class Main {
    private static JFrame frame;

    public static void main(String[] args) {
        setupMainUI();
    }

    private static void setupMainUI() {
        // Create the main frame
        frame = new JFrame("Welcome To Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add the main panel to the frame
        frame.add(new MainPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}