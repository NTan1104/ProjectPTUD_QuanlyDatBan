package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.drawer.DrawerNV;
import gui.tabbed.WindowsTabbed;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class homeNV extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelBody;
    @SuppressWarnings("unused")
    private Main loginFrame;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

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
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);

        // Set size and properties
        java.net.URL iconURL = getClass().getResource("bbqIcon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        // PanelBody setup
        panelBody = new JPanel();
        panelBody.setBackground(Color.WHITE);
        panelBody.setBounds(0, 0, 1920, 1080);
        getContentPane().add(panelBody);
        panelBody.setLayout(new BorderLayout(0, 0));

        // Tabbed
        WindowsTabbed.getInstance().install(this, panelBody);

        // Khởi tạo VLCJ media player
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        // Gọi playVideo trong constructor
    }

    // Phương thức để phát video
    public void playVideo(String mediaPath) {
        panelBody.removeAll(); // Xóa nội dung cũ trong panelBody
        panelBody.add(mediaPlayerComponent, BorderLayout.CENTER); // Thêm VLC player vào panelBody
        panelBody.revalidate();
        panelBody.repaint();
        // Phát video
        mediaPlayerComponent.mediaPlayer().media().play(mediaPath);
    }

    // Phương thức để dừng video
    public void stopVideo() {
        if (mediaPlayerComponent != null) {
            mediaPlayerComponent.mediaPlayer().controls().stop();
            panelBody.remove(mediaPlayerComponent);
            panelBody.revalidate();
            panelBody.repaint();
        }
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
            frame.setVisible(true); // Hiển thị JFrame sau khi constructor hoàn tất
            frame.playVideo("video/2424767-uhd_3840_2160_24fps.mp4"); // Thay bằng đường dẫn video của bạn
        });
    }
}