package Adminsrc;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
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
             //MainPanel frame01 = new MainPanel();
               // frame01.setVisible(true);
           

        });
    }
}
