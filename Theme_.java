import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

JCheckBox darkModeToggle = new JCheckBox("Dark Mode");
darkModeToggle.addActionListener(e -> {
    try {
        if (darkModeToggle.isSelected()) {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } else {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        SwingUtilities.updateComponentTreeUI(frame); // Update all components
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
