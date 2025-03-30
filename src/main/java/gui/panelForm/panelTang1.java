package gui.panelForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

public class panelTang1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private RoundedPanel panel_ChiTietBan; // Panel chi tiết bàn

    public panelTang1() {
        // Thiết lập giao diện FlatLaf và font Roboto
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();

        // Bắt đầu thiết kế panel
        setLayout(null);
        setSize(1535, 825);
        setBackground(Color.WHITE);

        // Panel Chi Tiết Bàn (bên phải)
        panel_ChiTietBan = new RoundedPanel(null, 30); // Góc bo tròn 30px
        panel_ChiTietBan.setBackground(new Color(255, 228, 225)); // Nền đỏ nhạt
        panel_ChiTietBan.setBounds(1053, 0, 482, 825);
        panel_ChiTietBan.setLayout(null);
        panel_ChiTietBan.setVisible(false); // Ẩn mặc định
        add(panel_ChiTietBan);

        JLabel lblChiTiet = new JLabel("Chi Tiết Bàn");
        lblChiTiet.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblChiTiet.setForeground(new Color(139, 0, 0)); // Đỏ đậm
        lblChiTiet.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiTiet.setBounds(10, 10, 462, 50);
        panel_ChiTietBan.add(lblChiTiet);

        // Panel Bàn (bên trái)
        RoundedPanel panel_Ban = new RoundedPanel(null, 30); // Góc bo tròn 30px
        panel_Ban.setBounds(0, 0, 1052, 951);
        panel_Ban.setBackground(new Color(250, 128, 114)); // Nền đỏ nhạt
        panel_Ban.setLayout(null);
        add(panel_Ban);

        // Tạo các panel bàn
        RoundedPanel panelBan1 = createBanPanel("BÀN 1", "CÒN TRỐNG", 85, 110);
        panel_Ban.add(panelBan1);

        RoundedPanel panelBan2 = createBanPanel("BÀN 2", "ĐÃ ĐẶT", 404, 110);
        panel_Ban.add(panelBan2);

        RoundedPanel panelBan3 = createBanPanel("BÀN 3", "CÒN TRỐNG", 726, 110);
        panel_Ban.add(panelBan3);

        RoundedPanel panelBan4 = createBanPanel("BÀN 4", "ĐANG SỬ DỤNG", 85, 504);
        panel_Ban.add(panelBan4);

        RoundedPanel panelBan5 = createBanPanel("BÀN 5", "CÒN TRỐNG", 404, 504);
        panel_Ban.add(panelBan5);

        RoundedPanel panelBan6 = createBanPanel("BÀN 6", "CÒN TRỐNG", 726, 504);
        panel_Ban.add(panelBan6);
    }

    private RoundedPanel createBanPanel(String title, String status, int x, int y) {
        RoundedPanel panel = new RoundedPanel(null, 30); // Bo tròn góc 30px
        panel.setBackground(new Color(255, 245, 238)); // Nền đỏ nhạt hơn
        panel.setBounds(x, y, 201, 222);
        panel.setLayout(null);

        // Tải và thay đổi kích thước ảnh
        ImageIcon originalIcon = new ImageIcon(panelTang1.class.getResource("/gui/panelForm/dining-table.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Thêm JLabel chứa hình ảnh bàn ăn
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(60, 50, 80, 80); // Căn giữa hình ảnh
        panel.add(lblImage);

        // Tên bàn
        JLabel lblBanTitle = new JLabel(title);
        lblBanTitle.setForeground(new Color(139, 0, 0)); // Đỏ đậm
        lblBanTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblBanTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanTitle.setBounds(10, 10, 181, 30);
        panel.add(lblBanTitle);

        // Trạng thái bàn
        JLabel lblStatus = new JLabel(status);
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblStatus.setForeground(status.equals("CÒN TRỐNG") ? new Color(0, 128, 0) : Color.RED);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(10, 135, 181, 29);
        panel.add(lblStatus);

        // Nút ĐẶT BÀN
        JButton btnDatBan = new JButton("ĐẶT BÀN");
        btnDatBan.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDatBan.setForeground(Color.WHITE);
        btnDatBan.setBackground(status.equals("CÒN TRỐNG") ? new Color(220, 20, 60) : new Color(169, 169, 169)); // Xám nếu không được đặt
        btnDatBan.setFocusPainted(false);
        btnDatBan.setBounds(40, 175, 120, 30);
        btnDatBan.setBorderPainted(false);
        btnDatBan.setCursor(new Cursor(status.equals("CÒN TRỐNG") ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        btnDatBan.setEnabled(status.equals("CÒN TRỐNG")); // Vô hiệu hóa nếu không còn trống

        // Hiệu ứng hover (chỉ áp dụng nếu còn trống)
        if (status.equals("CÒN TRỐNG")) {
            btnDatBan.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnDatBan.setBackground(new Color(255, 69, 0)); // Hover: đỏ cam
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnDatBan.setBackground(new Color(220, 20, 60)); // Trở về màu gốc
                }
            });
        }

        // Sự kiện bấm nút "ĐẶT BÀN"
        btnDatBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status.equals("CÒN TRỐNG")) {
                    displayBanDetails(title, status);
                    panel_ChiTietBan.setVisible(true); // Hiện panel chi tiết bàn
                }
            }
        });

        panel.add(btnDatBan);
        return panel;
    }

    private void displayBanDetails(String title, String status) {
        panel_ChiTietBan.removeAll();

        JLabel lblTitle = new JLabel("Chi Tiết: " + title);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setForeground(new Color(139, 0, 0)); // Đỏ đậm
        lblTitle.setBounds(10, 20, 462, 30);
        panel_ChiTietBan.add(lblTitle);

        JLabel lblStatus = new JLabel("Trạng Thái: " + status);
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblStatus.setForeground(Color.BLACK);
        lblStatus.setBounds(10, 60, 462, 30);
        panel_ChiTietBan.add(lblStatus);

        panel_ChiTietBan.revalidate();
        panel_ChiTietBan.repaint();
    }
}