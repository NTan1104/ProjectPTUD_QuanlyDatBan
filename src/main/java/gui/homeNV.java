package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import gui.drawer.DrawerNV;
//import gui.drawer.DrawerQL;
import gui.tabbed.WindowsTabbed;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class homeNV extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelBody;
    @SuppressWarnings("unused")
    private Main loginFrame;

    public homeNV(Main loginFrame) {
        getContentPane().setBackground(Color.WHITE);
        this.loginFrame = loginFrame;
        // Color theme
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        // Drawer
        GlassPanePopup.install(this);
        DrawerNV myDrawerBuilder = new DrawerNV(this, loginFrame);
//		DrawerQL myDrawerBuilder = new DrawerQL();
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        // Set size
        java.net.URL iconURL = getClass().getResource("bbqIcon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        
        panelBody = new JPanel();
        panelBody.setBackground(Color.WHITE);
        panelBody.setBounds(0, 0, 1920, 1080);
        getContentPane().add(panelBody);
        setLocationRelativeTo(null);

        // Tabbed
        WindowsTabbed.getInstance().install(this, panelBody);
        panelBody.setLayout(new BorderLayout(0, 0));

        // JLabel với hình ảnh
        JLabel lblNewLabel_2 = new JLabel("");
        ImageIcon originalIconBody = new ImageIcon(homeNV.class.getResource("/gui/food-words-steak-appetite-meal.jpg"));
        Image scaledImageBody = originalIconBody.getImage().getScaledInstance(1936, 1097, Image.SCALE_SMOOTH);
        lblNewLabel_2.setIcon(new ImageIcon(scaledImageBody));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        panelBody.add(lblNewLabel_2, BorderLayout.CENTER);

        
    }

    public void setPanelBody(JPanel panel) {
        panelBody.removeAll(); 
        panelBody.add(panel, BorderLayout.CENTER); 
        panelBody.revalidate();
        panelBody.repaint();
    } 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main loginFrame = new Main();
            homeNV frame = new homeNV(loginFrame);
            frame.setVisible(true);
        });
    }
}