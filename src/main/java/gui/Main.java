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
    private homeNV trangChinh;
    private Home home;
    private JPanel loginPanel;

    public Main() {
        init();
    }

    public void init() {
        java.net.URL iconURL = getClass().getResource("bbqIcon.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        setUndecorated(true);
        setSize(UIScale.scale(new Dimension(1365, 768)));
        setLocationRelativeTo(null);

        home = new Home();
        loginPanel = home;

        setContentPane(loginPanel);
        trangChinh = new homeNV(this);
        trangChinh.setVisible(false);
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatMacDarkLaf.setup();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                home.initOverlay(Main.this);
                home.play(0);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                home.stop();
            }
        });
    }

    public void switchToHomeNV() {
        setContentPane(trangChinh);
        trangChinh.setVisible(true);
        revalidate();
        repaint();
    }

    public void switchToLogin() {
    	FlatMacDarkLaf.setup();
        setContentPane(loginPanel);
        revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> {
            home.initOverlay(Main.this);
            home.play(0); 
        });
    }
    public static void main(String[] args) {

        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}