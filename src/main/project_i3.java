package main;
import javax.swing.*;

import adminsrc.Main;
import login.MainPanel;



public class project_i3 extends JFrame {

    public static void main(String[] args) {
        

        JFrame frame = new JFrame("Student management system");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        frame.add(new MainPanel());
        frame.setVisible(true);
        

    }

    public void showLoginScreen() {
        // TODO Auto-generated method stub
        MainPanel mainPanel = new MainPanel();
        mainPanel.showScreen("loginScreen");
    }
} 