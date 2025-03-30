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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

public class PanelQLyHD extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel detailPanel;
    private JButton btnNewButton;
    private RoundedScrollPane scrollPane;
    public PanelQLyHD() {
        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);
        
        // Cài đặt font FlatLaf
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        // Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 1535, 27);
        add(lblTitle);

        String[] columnNames = { "Mã hóa đơn", "Bàn", "Thời gian", "Khách hàng", "Trạng thái", "Tổng tiền", "Giá giảm", "Đã trả",};
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa bất kỳ ô nào
            }
        };
        table = new JTable(tableModel);
        JTableHeader header = table.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        int[] columnWidths = { 100,25, 140, 120, 115, 100, 80, 100}; 
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
        for (int i = 0; i <= 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centreRenderer);
        }
        for (int i = 5; i <= 7; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

        Object[][] data = {
                { "HD001","B1", "29/3/2025", "Khách lẻ", "Đã thanh toán", 5600000, 550000, 4500000},
                { "HD002","B2", "29/3/2025", "Giang", "Chưa thanh toán", 400000, 50000, 0}
            };

            for (Object[] row : data) {
                tableModel.addRow(row);
            }


        scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(13, 70, 1480, 750);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);
        
     // Tải ảnh gốc
        ImageIcon originalIcon = new ImageIcon(PanelQLyHD.class.getResource("/img/icons8-view-details-50.png"));

        // Chỉnh kích thước icon về 40x40
        Image scaledImage = originalIcon.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Tạo JButton với icon mới
        btnNewButton = new JButton(""); // Gán giá trị cho biến instance thay vì tạo biến cục bộ mới
        btnNewButton.setIcon(resizedIcon);
        btnNewButton.setBounds(1492, 95, 30, 36);
        add(btnNewButton);

        // Tạo JPanel chi tiết (ẩn ban đầu)
        detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.setBounds(993, 70, 500, 750);
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
        JTextField txtMaHD = new JTextField();
        txtMaHD.setBounds(130, 120, 200, 25);
        txtMaHD.setEditable(false);

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
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 440, 450, 260);

     // Nút In Hóa Đơn
        JButton btnInHoaDon = new JButton("In Hóa Đơn");
        btnInHoaDon.setBounds(180, 710, 150, 30);
        detailPanel.add(btnInHoaDon);

        
        detailPanel.add(lblMaHD);
        detailPanel.add(txtMaHD);
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
        detailPanel.add(scrollPane);

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
            btnNewButton.setBounds(1492, 95, 30, 36);
            scrollPane.setBounds(13, 70, 1480, 750);
            // Đưa nút về vị trí ban đầu
        } else {
            setComponentZOrder(detailPanel, 0); // Đưa panel lên trên khi hiển thị
            detailPanel.setVisible(true);
            btnNewButton.setBounds(963, 95, 30, 36);
            scrollPane.setBounds(13, 70, 950, 750);
            detailPanel.setBounds(995, 70, 500, 750);
        }
        
        repaint();
        revalidate();
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

}
