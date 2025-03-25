package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private Home home;

    public Main() {
        init();
    }

    public void init() {
        java.net.URL iconURL = getClass().getResource("bbqIcon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setUndecorated(true);
        setSize(UIScale.scale(new Dimension(1365, 768)));
        setLocationRelativeTo(null);
        home = new Home();
        setContentPane(home);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                home.initOverlay(Main.this); // Truyền tham chiếu Main vào Home
                home.play(0);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                home.stop();
            }
        });
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}