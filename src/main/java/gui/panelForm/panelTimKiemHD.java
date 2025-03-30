package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
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

public class panelTimKiemHD extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtMaHD, txtTenKH ;
    private JComboBox<String> cbThoiGian, cbTrangThai,cbGia;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField txtMBn;

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
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(20, 50, 1500, 110);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        txtMaHD = new JTextField(10);
        setupPlaceholder(txtMaHD, "Mã hóa đơn");
        txtMaHD.setBounds(108, 20, 194, 25);

        txtTenKH = new JTextField(10);
        setupPlaceholder(txtTenKH, "Tên khách hàng");
        txtTenKH.setBounds(108, 66, 194, 25);

        txtMBn = new JTextField(10);
        setupPlaceholder(txtMBn, "Mã bàn");
        txtMBn.setBounds(352, 20, 168, 25);




        String[] thoiGianOptions = {"Toàn thời gian", "Hôm nay", "Tuần này", "Tháng này"};
        cbThoiGian = new JComboBox<>(thoiGianOptions);
        cbThoiGian.setBounds(633, 20, 168, 25);

        String[] trangThaiOptions = {"Tất cả", "Chưa thanh toán", "Đã thanh toán"};
        cbTrangThai = new JComboBox<>(trangThaiOptions);
        cbTrangThai.setBounds(917, 20, 161, 25);
        
        String[] giaOptions = {"Tất cả", "Cao nhất", "Thấp nhất"};
        cbGia = new JComboBox<>(giaOptions);
        cbGia.setBounds(1178, 20, 175, 25);

        JButton btnSearch = new JButton(" Tìm kiếm");
        btnSearch.setBounds(1390, 22, 89, 21);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tìm kiếm: " + txtMaHD.getText());
            }
        });
        searchPanel.setLayout(null);

        searchPanel.add(txtMaHD);
        searchPanel.add(txtTenKH);
        searchPanel.add(txtMBn);
        JLabel label = new JLabel("Thời gian:");
        label.setBounds(550, 26, 73, 13);
        searchPanel.add(label);
        searchPanel.add(cbThoiGian);
        JLabel label_1 = new JLabel("Trạng thái:");
        label_1.setBounds(829, 26, 78, 13);
        searchPanel.add(label_1);
        searchPanel.add(cbTrangThai);
        searchPanel.add(btnSearch);
        searchPanel.add(cbGia);
        add(searchPanel);
        
        lblNewLabel = new JLabel("Mã hóa đơn:");
        lblNewLabel.setBounds(10, 26, 73, 13);
        searchPanel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Tên khách hàng:");
        lblNewLabel_1.setBounds(10, 72, 103, 13);
        searchPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_3 = new JLabel("Bàn:");
        lblNewLabel_3.setBounds(322, 26, 45, 13);
        searchPanel.add(lblNewLabel_3);
        
        
        JLabel lblNewLabel_2 = new JLabel("Giá:");
        lblNewLabel_2.setBounds(1112, 26, 45, 13);
        searchPanel.add(lblNewLabel_2);
        



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
        table.getColumnModel().getColumn(0).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centreRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer); 
        table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer); 
        Object[][] data = {
                {1, "HD001","B1", "29/3/2025", "Khách lẻ", "Đã thanh toán", 5600000, 550000, 4500000},
                {2, "HD002","B2", "29/3/2025", "Giang", "Chưa thanh toán", 400000, 50000, 0}
            };

            for (Object[] row : data) {
                tableModel.addRow(row);
            }


        RoundedScrollPane scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(21, 170, 1500, 650);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);
        
        
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

}

