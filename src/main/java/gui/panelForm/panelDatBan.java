package gui.panelForm;

import javax.swing.*;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.io.File;

public class panelDatBan extends JPanel {
    private static final long serialVersionUID = 1L;

    private JPanel mainPanel; // Nội dung chính
    private JPanel drawerPanel; // Khu vực nút chọn tầng
    private boolean isDrawerVisible; // Trạng thái hiển thị của drawer
    private JLabel lblMessage; // Nhãn hướng dẫn
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    public panelDatBan() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setSize(new Dimension(1535, 850));

        // Color theme
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        // Trạng thái ban đầu của Drawer
        isDrawerVisible = false;

        // Tạo Drawer nhỏ bên trái
        drawerPanel = new JPanel();
        drawerPanel.setLayout(new BoxLayout(drawerPanel, BoxLayout.Y_AXIS));
        drawerPanel.setPreferredSize(new Dimension(120, getHeight()));
        drawerPanel.setBackground(new Color(236, 240, 241)); // Màu xám nhạt

        // Các nút chọn tầng
        JButton btnTang1 = createDrawerButton("Tầng 1");
        JButton btnTang2 = createDrawerButton("Tầng 2");
        JButton btnTang3 = createDrawerButton("Tầng 3");

        // Thêm các nút vào drawerPanel
        drawerPanel.add(Box.createVerticalStrut(20)); // Khoảng cách trên cùng
        drawerPanel.add(btnTang1);
        drawerPanel.add(Box.createVerticalStrut(10));
        drawerPanel.add(btnTang2);
        drawerPanel.add(Box.createVerticalStrut(10));
        drawerPanel.add(btnTang3);

        // Lắng nghe sự kiện bấm nút
        btnTang1.addActionListener(e -> handleFloorSelection(1));
        btnTang2.addActionListener(e -> handleFloorSelection(2));
        btnTang3.addActionListener(e -> handleFloorSelection(3));

        // Tạo nút toggle Drawer (☰) ở góc trên bên trái
        JButton toggleDrawerButton = new JButton();
        toggleDrawerButton.setBackground(new Color(128, 128, 128)); // Màu xanh đậm
        toggleDrawerButton.setPreferredSize(new Dimension(50, 50));
        toggleDrawerButton.setBorder(BorderFactory.createEmptyBorder());

        // Thay đổi icon nút home
        ImageIcon originalIcon = new ImageIcon(panelDatBan.class.getResource("/gui/home.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        toggleDrawerButton.setIcon(scaledIcon);
        toggleDrawerButton.addActionListener(e -> toggleDrawer());

        // Panel chứa nút home
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(192, 192, 192)); // Màu xanh đậm
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));
        topPanel.add(toggleDrawerButton, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        // Nội dung chính
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2)); // Viền nhẹ

        // Thêm nhãn hướng dẫn
        lblMessage = new JLabel("Chọn tầng để xem bàn", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 20));
        lblMessage.setForeground(new Color(127, 140, 141)); // Màu xám tinh tế
        mainPanel.add(lblMessage, BorderLayout.CENTER);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add(mainPanel, BorderLayout.CENTER);
    }
    public void playVideo(String mediaPath) {
        // Xóa tất cả nội dung cũ trong mainPanel
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout()); // Đảm bảo BorderLayout hoạt động đúng

        if (mediaPlayerComponent != null) {
            File videoFile = new File(mediaPath);
            if (videoFile.exists()) {
                // Đặt kích thước của mediaPlayerComponent bằng với kích thước của mainPanel
                mediaPlayerComponent.setPreferredSize(mainPanel.getSize());

                // Thêm mediaPlayerComponent vào mainPanel
                mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);

                // Bắt đầu phát video
                mediaPlayerComponent.mediaPlayer().media().play(mediaPath);
                
                // Cố gắng tắt tỷ lệ khung hình để video lấp đầy không gian
                mediaPlayerComponent.mediaPlayer().video().setAspectRatio("16:9");
                mediaPlayerComponent.mediaPlayer().video().setScale(3.2f);
            } else {
                System.out.println("File video không tồn tại: " + mediaPath);
            }
        }

        // Làm mới giao diện sau khi thêm video
        mainPanel.revalidate();
        mainPanel.repaint();
    }


    private JButton createDrawerButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(120, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(52, 152, 219)); // Màu xanh nhẹ
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185)); // Đổi màu khi hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 152, 219)); // Quay về màu cũ
            }
        });

        return button;
    }

    private void handleFloorSelection(int floor) {
        // Xóa nội dung cũ
        mainPanel.removeAll();

        if (floor == 1) {
            panelTang1 tang1Panel = new panelTang1();
            mainPanel.add(tang1Panel, BorderLayout.CENTER);
        } else if (floor == 2) {
            panelTang2 tang2Panel = new panelTang2();
            mainPanel.add(tang2Panel, BorderLayout.CENTER);
        } else if (floor == 3) {
            panelTang3 tang3Panel = new panelTang3();
            mainPanel.add(tang3Panel, BorderLayout.CENTER);
        }

        // Làm mới giao diện sau khi thay đổi nội dung
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void toggleDrawer() {
        if (isDrawerVisible) {
            remove(drawerPanel);
        } else {
            add(drawerPanel, BorderLayout.WEST);
        }
        isDrawerVisible = !isDrawerVisible;
        revalidate();
        repaint();
    }
}
