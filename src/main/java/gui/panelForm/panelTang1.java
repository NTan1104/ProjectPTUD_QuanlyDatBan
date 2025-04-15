package gui.panelForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.table.DefaultTableModel;

public class panelTang1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private RoundedPanelWithShadow panel_ChiTietBan; // Panel chi tiết bàn
    private JTable table;
    private JTable table_1;

    public panelTang1() {
        // Thiết lập giao diện FlatLaf với theme FlatMacLightLaf
    	setSize(new Dimension(1535, 850));
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatMacLightLaf.setup();

        // Tùy chỉnh màu sắc qua UIManager
        UIManager.put("Panel.background", new Color(247, 248, 252)); // Trắng xám nhạt nhẹ nhàng
        UIManager.put("Button.background", new Color(52, 102, 255)); // Xanh dương nhẹ cho nút
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219)); // Xám nhạt khi vô hiệu
        UIManager.put("Label.foreground", new Color(17, 24, 39)); // Xám đen đậm cho chữ
        UIManager.put("Component.borderColor", new Color(229, 231, 235)); // Viền xám nhạt

        
        // Thiết kế panel chính
        setLayout(null);
        setSize(1535, 825);
        setBackground(UIManager.getColor("Panel.background"));

        // Panel Chi Tiết Bàn (bên phải) với bóng đổ
        panel_ChiTietBan = new RoundedPanelWithShadow(30, new Color(255, 255, 255), 10, 5, new Color(0, 0, 0, 50));
        panel_ChiTietBan.setBounds(1055, 10, 470, 755); // Thu nhỏ và căn giữa
        panel_ChiTietBan.setLayout(null);
        panel_ChiTietBan.setVisible(false);
        add(panel_ChiTietBan);

        JLabel lblChiTiet = new JLabel("CHI TIẾT BÀN");
        lblChiTiet.setFont(new Font("Dialog", Font.BOLD, 26));
        lblChiTiet.setForeground(UIManager.getColor("Label.foreground"));
        lblChiTiet.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiTiet.setBounds(0, 0, 470, 50);
        panel_ChiTietBan.add(lblChiTiet);
        
        JLabel lblsoBan = new JLabel("SỐ BÀN:");
        lblsoBan.setHorizontalAlignment(SwingConstants.LEFT);
        lblsoBan.setFont(new Font("Dialog", Font.BOLD, 15));
        lblsoBan.setBounds(10, 60, 428, 20);
        panel_ChiTietBan.add(lblsoBan);
        
        JLabel lblTnKhchHng = new JLabel("TÊN KHÁCH HÀNG:");
        lblTnKhchHng.setHorizontalAlignment(SwingConstants.LEFT);
        lblTnKhchHng.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTnKhchHng.setBounds(10, 81, 428, 20);
        panel_ChiTietBan.add(lblTnKhchHng);
        
        JLabel lblSLng = new JLabel("SỐ LƯỢNG:");
        lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
        lblSLng.setFont(new Font("Dialog", Font.BOLD, 15));
        lblSLng.setBounds(10, 100, 428, 20);
        panel_ChiTietBan.add(lblSLng);
        
        JLabel lblDanhSchMn = new JLabel("DANH SÁCH MÓN ĂN");
        lblDanhSchMn.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSchMn.setForeground(Color.BLACK);
        lblDanhSchMn.setFont(new Font("Dialog", Font.BOLD, 20));
        lblDanhSchMn.setBounds(0, 106, 470, 50);
        panel_ChiTietBan.add(lblDanhSchMn);
        
        table_1 = new JTable();
        table_1.setColumnSelectionAllowed(true);
        table_1.setCellSelectionEnabled(true);
        table_1.setBounds(0, 166, 460, 394);
        panel_ChiTietBan.add(table_1);
        
        JButton btnNewButton = new JButton("GỌI MÓN");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 20));
        btnNewButton.setBounds(26, 657, 160, 43);
        panel_ChiTietBan.add(btnNewButton);
        
        JButton btnThanhTon = new JButton("THANH TOÁN");
        btnThanhTon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThanhTon.setFont(new Font("Dialog", Font.BOLD, 20));
        btnThanhTon.setBounds(238, 657, 200, 43);
        panel_ChiTietBan.add(btnThanhTon);
        
        JLabel lblTngTin = new JLabel("TỔNG TIỀN:");
        lblTngTin.setHorizontalAlignment(SwingConstants.LEFT);
        lblTngTin.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTngTin.setBounds(60, 559, 227, 20);
        panel_ChiTietBan.add(lblTngTin);
        
        JLabel lblTngTinCn = new JLabel("TỔNG TIỀN CẦN TRẢ:");
        lblTngTinCn.setHorizontalAlignment(SwingConstants.LEFT);
        lblTngTinCn.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTngTinCn.setBounds(60, 622, 227, 20);
        panel_ChiTietBan.add(lblTngTinCn);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Dialog", Font.BOLD, 10));
        comboBox.setBounds(200, 589, 126, 23);
        panel_ChiTietBan.add(comboBox);
        
        JLabel lblPhiuGimGi = new JLabel("PHIẾU GIẢM GIÁ");
        lblPhiuGimGi.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhiuGimGi.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPhiuGimGi.setBounds(60, 589, 126, 20);
        panel_ChiTietBan.add(lblPhiuGimGi);
        

        // Panel Bàn (bên trái) với bóng đổ nhẹ
        RoundedPanelWithShadow panel_Ban = new RoundedPanelWithShadow(30, new Color(250, 251, 255), 5, 3, new Color(0, 0, 0, 30));
        panel_Ban.setBounds(10, 10, 1032, 755); // Thu nhỏ và căn giữa
        panel_Ban.setLayout(null);

        // Sự kiện chuột để ẩn panel_ChiTietBan
        panel_Ban.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel_ChiTietBan.setVisible(false);
            }
        });

        add(panel_Ban);

        // Tạo các panel bàn với hiệu ứng đẹp hơn
        RoundedPanelWithShadow panelBan1 = createBanPanel("BÀN 1", "CÒN TRỐNG", 85, 110);
        panel_Ban.add(panelBan1);

        RoundedPanelWithShadow panelBan2 = createBanPanel("BÀN 2", "ĐANG SỬ DỤNG", 404, 110);
        panel_Ban.add(panelBan2);

        RoundedPanelWithShadow panelBan3 = createBanPanel("BÀN 3", "CÒN TRỐNG", 723, 110);
        panel_Ban.add(panelBan3);

        RoundedPanelWithShadow panelBan4 = createBanPanel("BÀN 4", "ĐANG SỬ DỤNG", 85, 504);
        panel_Ban.add(panelBan4);

        RoundedPanelWithShadow panelBan5 = createBanPanel("BÀN 5", "CÒN TRỐNG", 404, 504);
        panel_Ban.add(panelBan5);

        RoundedPanelWithShadow panelBan6 = createBanPanel("BÀN 6", "BẢO TRÌ", 723, 504);
        panel_Ban.add(panelBan6);

    }

    private RoundedPanelWithShadow createBanPanel(String title, String status, int x, int y) {
        RoundedPanelWithShadow panel = new RoundedPanelWithShadow(30, new Color(255, 255, 255), 8, 4, new Color(0, 0, 0, 40));
        panel.setBounds(x, y, 201, 222);
        panel.setLayout(null);

        // Tải và thay đổi kích thước ảnh
        ImageIcon originalIcon = new ImageIcon(panelTang1.class.getResource("/gui/panelForm/dining-table.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Thêm JLabel chứa hình ảnh bàn ăn
        JLabel lblImage = new JLabel(scaledIcon);
        lblImage.setBounds(60, 40, 80, 80);
        panel.add(lblImage);

        // Tên bàn
        JLabel lblBanTitle = new JLabel(title);
        lblBanTitle.setForeground(UIManager.getColor("Label.foreground"));
        lblBanTitle.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 18));
        lblBanTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanTitle.setBounds(0, 10, 201, 30);
        panel.add(lblBanTitle);

        // Trạng thái bàn
        JLabel lblStatus = new JLabel(status);
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
        lblStatus.setForeground(status.equals("CÒN TRỐNG") ? new Color(16, 185, 129) : new Color(239, 68, 68)); // Xanh ngọc và đỏ tươi
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(0, 130, 201, 30);
        panel.add(lblStatus);

        // Thiết lập nút dựa trên trạng thái
        String buttonText = status.equals("CÒN TRỐNG") ? "ĐẶT BÀN" : 
            (status.equals("ĐANG SỬ DỤNG") || status.equals("BẢO TRÌ")) ? "XEM CHI TIẾT" : 
            "ĐẶT BÀN"; // Mặc định cho các trạng thái khác (như "ĐÃ ĐẶT")
        JButton btnDatBan = new JButton(buttonText);
        btnDatBan.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 12));
        btnDatBan.setForeground(UIManager.getColor("Button.foreground"));
        btnDatBan.setBackground(status.equals("CÒN TRỐNG") ? UIManager.getColor("Button.background") : 
                               status.equals("ĐANG SỬ DỤNG") ? UIManager.getColor("Button.background") : // Màu cho "ĐANG SỬ DỤNG"
                               UIManager.getColor("Button.disabledBackground")); // Màu cho "BẢO TRÌ"
        btnDatBan.setFocusPainted(false);
        btnDatBan.setBounds(40, 170, 120, 35);
        btnDatBan.setBorderPainted(false);
        btnDatBan.setCursor(Cursor.getPredefinedCursor(status.equals("BẢO TRÌ") ? Cursor.DEFAULT_CURSOR : Cursor.HAND_CURSOR));
        btnDatBan.setEnabled(!status.equals("BẢO TRÌ")); // Vô hiệu hóa khi "BẢO TRÌ"

        // Hiệu ứng hover cho nút (chỉ khi nút không bị vô hiệu hóa)
        if (!status.equals("BẢO TRÌ")) {
            btnDatBan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) {
                    btnDatBan.setBackground(status.equals("CÒN TRỐNG") ? new Color(37, 99, 235) : 
                                           status.equals("ĐANG SỬ DỤNG") ? new Color(37, 99, 235) : 
                                           new Color(66, 135, 245)); // Xanh đậm hơn khi hover
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    btnDatBan.setBackground(status.equals("CÒN TRỐNG") ? UIManager.getColor("Button.background") : 
                                           status.equals("ĐANG SỬ DỤNG") ? UIManager.getColor("Button.background") :  
                                           UIManager.getColor("Button.disabledBackground"));
                }
            });
        }

        // Sự kiện bấm nút
        btnDatBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status.equals("ĐANG SỬ DỤNG")) {
                    displayBanDetails(title, status);
                    panel_ChiTietBan.setVisible(true);
                }
            }
        });

        panel.add(btnDatBan);
        return panel;
    }

    private void displayBanDetails(String title, String status) {
    	panel_ChiTietBan.removeAll(); // Xóa các thành phần cũ trong panel trước khi thêm mới
    	
        // Cập nhật lblsoBan với title của bàn được chọn
        JLabel lblChiTiet = new JLabel("CHI TIẾT BÀN");
        lblChiTiet.setFont(new Font("Dialog", Font.BOLD, 26));
        lblChiTiet.setForeground(UIManager.getColor("Label.foreground"));
        lblChiTiet.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiTiet.setBounds(0, 0, 470, 50);
        panel_ChiTietBan.add(lblChiTiet);
    	JLabel lblsoBan = new JLabel("SỐ BÀN: " + title);
        lblsoBan.setHorizontalAlignment(SwingConstants.LEFT);
        lblsoBan.setFont(new Font("Dialog", Font.BOLD, 15));
        lblsoBan.setBounds(10, 60, 428, 20);
        panel_ChiTietBan.add(lblsoBan);
        
        String tenKH = "Nguyễn Tân";
        int soLuongKH = 10;
        JLabel lblTnKhchHng = new JLabel("TÊN KHÁCH HÀNG: "+tenKH);
        lblTnKhchHng.setHorizontalAlignment(SwingConstants.LEFT);
        lblTnKhchHng.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTnKhchHng.setBounds(10, 81, 428, 20);
        panel_ChiTietBan.add(lblTnKhchHng);
        JLabel lblSLng = new JLabel("SỐ LƯỢNG: "+soLuongKH);
        lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
        lblSLng.setFont(new Font("Dialog", Font.BOLD, 15));
        lblSLng.setBounds(10, 100, 428, 20);
        panel_ChiTietBan.add(lblSLng);
        JLabel lblTngTin = new JLabel("TỔNG TIỀN:");
        lblTngTin.setHorizontalAlignment(SwingConstants.LEFT);
        lblTngTin.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTngTin.setBounds(60, 559, 227, 20);
        panel_ChiTietBan.add(lblTngTin);
        
        JLabel lblTngTinCn = new JLabel("TỔNG TIỀN CẦN TRẢ:");
        lblTngTinCn.setHorizontalAlignment(SwingConstants.LEFT);
        lblTngTinCn.setFont(new Font("Dialog", Font.BOLD, 15));
        lblTngTinCn.setBounds(60, 622, 227, 20);
        panel_ChiTietBan.add(lblTngTinCn);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Dialog", Font.BOLD, 10));
        comboBox.setBounds(200, 589, 126, 23);
        panel_ChiTietBan.add(comboBox);
        
        JLabel lblPhiuGimGi = new JLabel("PHIẾU GIẢM GIÁ");
        lblPhiuGimGi.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhiuGimGi.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPhiuGimGi.setBounds(60, 589, 126, 20);
        panel_ChiTietBan.add(lblPhiuGimGi);
        JButton btnNewButton = new JButton("GỌI MÓN");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 20));
        btnNewButton.setBounds(26, 657, 160, 43);
        panel_ChiTietBan.add(btnNewButton);
        
        JButton btnThanhTon = new JButton("THANH TOÁN");
        btnThanhTon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThanhTon.setFont(new Font("Dialog", Font.BOLD, 20));
        btnThanhTon.setBounds(238, 657, 200, 43);
        btnThanhTon.setBackground(new Color(0, 255, 0));
        btnThanhTon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnThanhTon.setBackground(new Color(50, 205, 50)); // Xanh lá đậm hơn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnThanhTon.setBackground(new Color(0, 255, 0)); // Quay lại xanh lá nhạt
            }
        });
        panel_ChiTietBan.add(btnThanhTon);
        //Table 
        JLabel lblDanhSchMn = new JLabel("DANH SÁCH MÓN ĂN");
        lblDanhSchMn.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSchMn.setForeground(Color.BLACK);
        lblDanhSchMn.setFont(new Font("Dialog", Font.BOLD, 20));
        lblDanhSchMn.setBounds(0, 106, 470, 50);
        panel_ChiTietBan.add(lblDanhSchMn);

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"Phở bò", 2, 50000, 100000},
                {"Bánh mì", 1, 20000, 20000},
                {"Trà đá", 3, 5000, 15000},
            },
            new String[] {
                "Tên món ăn", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 166, 460, 394); // Vị trí và kích thước
        panel_ChiTietBan.add(scrollPane);
        // Thêm nút đóng với hình ảnh và kích thước vừa phải
        JButton btnClose = new JButton();
        btnClose.setBounds(425, 15, 40, 40); // Kích thước vừa phải
        btnClose.setFocusPainted(false);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false); // Loại bỏ nền mặc định

        // Tải và thay đổi kích thước icon đóng
        ImageIcon closeIcon = new ImageIcon(panelTang1.class.getResource("/gui/panelForm/close.png")); // Đường dẫn tới icon
        Image scaledCloseImage = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnClose.setIcon(new ImageIcon(scaledCloseImage));

        // Hiệu ứng hover cho nút đóng
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setBackground(new Color(229, 231, 235));
                btnClose.setOpaque(true);
                btnClose.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setBackground(null);
                btnClose.setOpaque(false);
                btnClose.setContentAreaFilled(false);
            }
        });

        btnClose.addActionListener(e -> panel_ChiTietBan.setVisible(false));
        panel_ChiTietBan.add(btnClose);

        panel_ChiTietBan.revalidate();
        panel_ChiTietBan.repaint();
    }

    // Lớp tùy chỉnh RoundedPanel với bóng đổ
    private static class RoundedPanelWithShadow extends JPanel {
        private final int arc;
        private final int shadowSize;
        private final int shadowOffset;
        private final Color shadowColor;

        public RoundedPanelWithShadow(int arc, Color bgColor, int shadowSize, int shadowOffset, Color shadowColor) {
            this.arc = arc;
            this.shadowSize = shadowSize;
            this.shadowOffset = shadowOffset;
            this.shadowColor = shadowColor;
            setOpaque(false);
            setBackground(bgColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Vẽ bóng đổ
            g2.setColor(shadowColor);
            g2.fill(new RoundRectangle2D.Double(shadowOffset, shadowOffset, getWidth() - shadowSize, getHeight() - shadowSize, arc, arc));

            // Vẽ panel chính
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, arc, arc));

            g2.dispose();
        }
    }
}