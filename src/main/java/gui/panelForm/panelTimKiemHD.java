package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import gui.panelForm.PanelQLyHD.RoundedPane;
import gui.panelForm.PanelQLyHD.RoundedScrollPane;

public class panelTimKiemHD extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtMaHD, txtTenKH ;
    private JComboBox<String> cbThoiGian, cbTrangThai,cbGia;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField txtMBn;
    private JPanel detailPanel;
    private JButton btnNewButton;
    private RoundedScrollPane scrollPane;
	private JTextField txtSDT;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
    
    public panelTimKiemHD() {
        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);
        
        // Cài đặt font FlatLaf
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        // Thanh tìm kiếm ngang
        JPanel searchPanel = new RoundedPane(20);
        searchPanel.setBounds(20, 50, 1500, 110);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        txtMaHD = new JTextField(10);
        setupPlaceholder(txtMaHD, "Mã hóa đơn");
        txtMaHD.setBounds(108, 20, 194, 25);

        txtTenKH = new JTextField(10);
        setupPlaceholder(txtTenKH, "Tên khách hàng");
        txtTenKH.setBounds(780, 20, 194, 25) ;

        txtMBn = new JTextField(10);
        setupPlaceholder(txtMBn, "Mã bàn");
        txtMBn.setBounds(440, 20, 194, 25);

        txtSDT = new JTextField(10);
        setupPlaceholder(txtSDT, "Số điện thoại");
        txtSDT.setBounds(1090, 20, 194, 25);


        String[] thoiGianOptions = {"Toàn thời gian", "Hôm nay", "Tuần này", "Tháng này"};
        cbThoiGian = new JComboBox<>(thoiGianOptions);
        cbThoiGian.setBounds(108, 66, 194, 25);

        String[] trangThaiOptions = {"Tất cả", "Chưa thanh toán", "Đã thanh toán"};
        cbTrangThai = new JComboBox<>(trangThaiOptions);
        cbTrangThai.setBounds(440, 66, 194, 25);
        
        String[] giaOptions = {"Tất cả", "Cao nhất", "Thấp nhất"};
        cbGia = new JComboBox<>(giaOptions);
        cbGia.setBounds(780, 66, 194, 25);

        JButton btnSearch = new JButton(" Tìm kiếm");
        btnSearch.setBounds(1350, 22, 89, 30);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tìm kiếm: " + txtMaHD.getText());
            }
        });
        searchPanel.setLayout(null);

        searchPanel.add(txtMaHD);
        searchPanel.add(txtTenKH);
        searchPanel.add(txtMBn);
        searchPanel.add(txtSDT);
        JLabel label = new JLabel("Thời gian:");
        label.setBounds(10, 72, 103, 13);
        searchPanel.add(label);
        searchPanel.add(cbThoiGian);
        JLabel label_1 = new JLabel("Trạng thái:");
        label_1.setBounds(362, 72, 78, 13);
        searchPanel.add(label_1);
        searchPanel.add(cbTrangThai);
        searchPanel.add(btnSearch);
        searchPanel.add(cbGia);
        add(searchPanel);
        
        lblNewLabel = new JLabel("Mã hóa đơn:");
        lblNewLabel.setBounds(10, 26, 73, 13);
        searchPanel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Tên khách hàng:");
        lblNewLabel_1.setBounds (670, 26, 100, 13);
        searchPanel.add(lblNewLabel_1);
        
        lblNewLabel_3 = new JLabel("Bàn:");
        lblNewLabel_3.setBounds(362, 26, 45, 13);
        searchPanel.add(lblNewLabel_3);
        
        
        lblNewLabel_2 = new JLabel("Giá:");
        lblNewLabel_2.setBounds(670, 72, 45, 13);
        searchPanel.add(lblNewLabel_2);
        
        lblNewLabel_4 = new JLabel("SDT:");
        lblNewLabel_4.setBounds(1040, 26, 45, 13);
        searchPanel.add(lblNewLabel_4);


        // Tiêu đề
        JLabel lblTitle = new JLabel("TÌM KIẾM HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 1535, 27);
        add(lblTitle);

        String[] columnNames = {"STT", "Mã hóa đơn", "Bàn", "Thời gian", "Khách hàng", "Trạng thái", "Tổng tiền", "Giá giảm", "Đã trả"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa bất kỳ ô nào
            }
        };
        table = new JTable(tableModel);
        int[] columnWidths = {25, 100,25, 150, 120, 100, 100, 80, 100}; // Điều chỉnh theo nhu cầu
        for (int i = 0; i < columnWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setOpaque(false);
        table.setBackground(new Color(0, 0, 0, 0));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centreRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        centreRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Áp dụng cho các cột cần căn phải
        if (table.getColumnCount() >= 9) {
            for (int i = 0; i <= 5; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centreRenderer);
            }
            for (int i = 6; i <= 8; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            }
        }
        Object[][] data = {
                {1, "HD001","B1", "29/3/2025", "Khách lẻ", "Đã thanh toán", 5600000, 550000, 4500000},
                {2, "HD002","B2", "29/3/2025", "Giang", "Chưa thanh toán", 400000, 50000, 0}
            };

            for (Object[] row : data) {
                tableModel.addRow(row);
            }


        scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(13, 170, 1480, 650);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);
        
    
//        ImageIcon originalIcon = new ImageIcon(PanelQLyHD.class.getResource("/img/icons8-view-details-50.png"));
//
//        // Chỉnh kích thước icon về 40x40
//        Image scaledImage = originalIcon.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Tạo JButton với icon mới
        btnNewButton = new JButton(""); // Gán giá trị cho biến instance thay vì tạo biến cục bộ mới
//        btnNewButton.setIcon(resizedIcon);
        btnNewButton.setBounds(1492, 195, 30, 36);
        add(btnNewButton);
        
        detailPanel = new RoundedPane(30);
	    detailPanel.setLayout(null);
	    detailPanel.setBounds(993, 70, 500, 650);	
	    detailPanel.setBackground(Color.WHITE);
	    detailPanel.setVisible(false);
	
	    // Tiêu đề hóa đơn
	    JLabel lblTitle_1 = new JLabel("THÔNG TIN HÓA ĐƠN", SwingConstants.CENTER);
	    lblTitle_1.setFont(new Font("Arial", Font.BOLD, 18));
	    lblTitle_1.setBounds(100, 10, 300, 30);
	    detailPanel.add(lblTitle_1);
	
	    // Tên nhà hàng (cố định)
	    JLabel lblNhaHang = new JLabel("Nhà hàng: SERBUR");
	    lblNhaHang.setBounds(20, 50, 200, 25);
	    detailPanel.add(lblNhaHang);
	
	    // Địa chỉ (cố định)
	    JLabel lblDiaChi = new JLabel("Địa chỉ: 4 Nguyễn Văn Bảo, P5, Gò Vấp");
	    lblDiaChi.setBounds(20, 80, 300, 25);
	    detailPanel.add(lblDiaChi);
	
	    // Label và TextField cho thông tin hóa đơn
	    JLabel lblMaHD = new JLabel("Mã hóa đơn:");
	    lblMaHD.setBounds(20, 120, 100, 25);
	    JTextField txtMaHD1 = new JTextField();
	    txtMaHD1.setBounds(130, 120, 200, 25);
	    txtMaHD1.setEditable(false);
	
	    JLabel lblNhanVien = new JLabel("Nhân viên:");
	    lblNhanVien.setBounds(20, 160, 100, 25);
	    JTextField txtNhanVien = new JTextField();
	    txtNhanVien.setBounds(130, 160, 200, 25);
	    txtNhanVien.setEditable(false);
	
	    JLabel lblPhongBan = new JLabel("Phòng/Bàn:");
	    lblPhongBan.setBounds(20, 200, 100, 25);
	    JTextField txtPhongBan = new JTextField();
	    txtPhongBan.setBounds(130, 200, 200, 25);
	    txtPhongBan.setEditable(false);
	
	    JLabel lblKhachHang = new JLabel("Khách hàng:");
	    lblKhachHang.setBounds(20, 240, 100, 25);
	    JTextField txtKhachHang = new JTextField();
	    txtKhachHang.setBounds(130, 240, 200, 25);
	    txtKhachHang.setEditable(false);
	
	    // Số điện thoại khách hàng
	    JLabel lblSDT = new JLabel("SĐT:");
	    lblSDT.setBounds(20, 280, 100, 25);
	    JTextField txtSDT = new JTextField();
	    txtSDT.setBounds(130, 280, 200, 25);
	    txtSDT.setEditable(false);
	
	    JLabel lblThoiGian = new JLabel("Thời gian:");
	    lblThoiGian.setBounds(20, 320, 100, 25);
	    JTextField txtThoiGian = new JTextField();
	    txtThoiGian.setBounds(130, 320, 200, 25);
	    txtThoiGian.setEditable(false);
	
	    // Thuế 8% (đặt dưới thời gian)
	    JLabel lblThue = new JLabel("Thuế (8%):");
	    lblThue.setBounds(20, 360, 100, 25);
	    JTextField txtThue = new JTextField();
	    txtThue.setBounds(130, 360, 200, 25);
	    txtThue.setEditable(false);
	
	    // Tổng tiền (trước bảng món ăn)
	    JLabel lblTongTien = new JLabel("Tổng tiền:");
	    lblTongTien.setBounds(20, 400, 100, 25);
	    JTextField txtTongTien = new JTextField();
	    txtTongTien.setBounds(130, 400, 200, 25);
	    txtTongTien.setEditable(false);
	
	    // Bảng hiển thị danh sách món ăn
	    String[] columnNames_1 = { "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Thành tiền" };
	    tableModel = new DefaultTableModel(columnNames_1, 0) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
	
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Không cho chỉnh sửa bất kỳ ô nào
	        }
	    };
	    table = new JTable(tableModel);
	    table.getTableHeader().setReorderingAllowed(false); // Ngăn kéo thả cột
	    JScrollPane scrollPane1 = new JScrollPane(table);
	    scrollPane1.setBounds(20, 440, 450, 150);
	
	 // Nút In Hóa Đơn
	    JButton btnInHoaDon = new JButton("In Hóa Đơn");
	    btnInHoaDon.setBounds(180, 600, 150, 30);
	    detailPanel.add(btnInHoaDon);
	
	    
	    detailPanel.add(lblMaHD);
	    detailPanel.add(txtMaHD1);
	    detailPanel.add(lblNhanVien);
	    detailPanel.add(txtNhanVien);
	    detailPanel.add(lblPhongBan);
	    detailPanel.add(txtPhongBan);
	    detailPanel.add(lblKhachHang);
	    detailPanel.add(txtKhachHang);
	    detailPanel.add(lblSDT);
	    detailPanel.add(txtSDT);
	    detailPanel.add(lblThoiGian);
	    detailPanel.add(txtThoiGian);
	    detailPanel.add(lblThue);
	    detailPanel.add(txtThue);
	    detailPanel.add(lblTongTien);
	    detailPanel.add(txtTongTien);
	    detailPanel.add(scrollPane1);
	
	    add(detailPanel);
	
	    // Thêm sự kiện click cho nút
	    btnNewButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            toggleDetailPanel();
	        }
	    });
}

// Phương thức ẩn/hiện panel chi tiết
private void toggleDetailPanel() {
    if (detailPanel.isVisible()) {
        detailPanel.setVisible(false);
        setComponentZOrder(detailPanel, 0); // Đảm bảo panel xuống dưới khi ẩn
        setComponentZOrder(btnNewButton, 0);
        btnNewButton.setBounds(1492, 195, 30, 36);
        scrollPane.setBounds(13, 170, 1480, 650);
        // Đưa nút về vị trí ban đầu
    } else {
        setComponentZOrder(detailPanel, 0); // Đưa panel lên trên khi hiển thị
        detailPanel.setVisible(true);
        btnNewButton.setBounds(963, 195, 30, 36);
        scrollPane.setBounds(13, 170, 950, 650);
        detailPanel.setBounds(995, 170, 500, 650);
    }
    
    repaint();
    revalidate();
}
    private void setupPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    

class RoundedScrollPane extends JScrollPane {
    private int cornerRadius;

    public RoundedScrollPane(Component view, int radius) {
        super(view);
        this.cornerRadius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Thêm padding để không bị che mất góc
        getViewport().setOpaque(false);
        getViewport().setBackground(new Color(0, 0, 0, 0));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Vẽ nền bo góc toàn bộ
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền bo tròn
        g2.setColor(Color.GRAY); // Đổi màu viền nếu cần
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

        g2.dispose();
    }
    
}
class RoundedPane extends JPanel {
    private int cornerRadius;

    public RoundedPane(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Thêm padding để không bị che mất góc
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo góc
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền bo tròn
        g2.setColor(Color.GRAY);
        g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

        g2.dispose();
    }
}
    

}

