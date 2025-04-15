package gui.panelForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import dao.DAO_KhachHang;
import entity.KhachHang;
import gui.tabbed.CheckBoxTableHeaderRenderer;
import gui.tabbed.TableHeaderAlignment;

public class panelSearchKH extends JPanel {
    private JTable table;
    private JLabel lbTitle;
    private JSeparator jSeparator1;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField txtSearch;
    private JComboBox<String> cbSearchCriteria;

    public panelSearchKH() {
        initComponents();
        init();
        loadDataToTable(); // Load dữ liệu khi khởi tạo
    }

    private void initComponents() {
        setSize(1535, 850);
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        lbTitle = new JLabel("TÌM KIẾM KHÁCH HÀNG");
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        UIManager.put("Panel.background", new Color(247, 248, 252)); // Trắng xám nhạt nhẹ nhàng
        UIManager.put("Button.background", new Color(52, 102, 255)); // Xanh dương nhẹ cho nút
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219)); // Xám nhạt khi vô hiệu
        UIManager.put("Label.foreground", new Color(17, 24, 39)); // Xám đen đậm cho chữ
        UIManager.put("Component.borderColor", new Color(229, 231, 235)); // Viền xám nhạt
        
        // Thanh tìm kiếm với placeholder
        String placeholder = "Tìm kiếm khách hàng tại đây";
        txtSearch = new JTextField();
        txtSearch.setBounds(30, 20, 238, 35);
        txtSearch.setText(placeholder);
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals(placeholder)) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                    txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText(placeholder);
                    txtSearch.setForeground(Color.GRAY);
                    txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }
            }
        });

        // Nút tìm kiếm bên trong
        JButton searchButton = new JButton();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/search.png"));
            Image scaledIcon = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            searchButton.setIcon(new ImageIcon(scaledIcon));
        } catch (Exception e) {
            searchButton.setText("🔍");
        }
        searchButton.setBorder(null);
        searchButton.setBackground(txtSearch.getBackground());
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        txtSearch.setLayout(new BorderLayout());
        txtSearch.add(searchButton, BorderLayout.EAST);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            txtSearch.getBorder(),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        add(txtSearch);

        // ComboBox tiêu chí tìm kiếm
        cbSearchCriteria = new JComboBox<>();
        cbSearchCriteria.setBounds(278, 20, 150, 35);
        cbSearchCriteria.addItem("Mã khách hàng");
        cbSearchCriteria.addItem("Tên khách hàng");
        cbSearchCriteria.addItem("Số điện thoại");
        add(cbSearchCriteria);

        jSeparator1 = new JSeparator();
        jSeparator1.setBounds(1535, 58, 0, 2);

        RoundedScrollPane scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(30, 200, 1474, 622);
        scrollPane.setBackground(Color.WHITE);
        table = new JTable();

        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "#", "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính"
                }
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                Class[] types = new Class[] {
                        Boolean.class,
                        Object.class,
                        Object.class,
                        Object.class,
                        Object.class,
                        Object.class
                };
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0;
            }
        });

        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);

        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        // Giả sử CheckBoxTableHeaderRenderer và TableHeaderAlignment đã được định nghĩa
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

        setLayout(null);
        add(scrollPane);

        JLabel lblNewLabel = new JLabel("Tìm kiếm khách hàng");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(18, 10, 1507, 27);
        add(lblNewLabel);

        // Sử dụng RoundedPane thay vì JPanel
        RoundedPane panel = new RoundedPane(20); // Bo tròn với bán kính 20
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        panel.setBounds(30, 58, 1474, 97);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Mã khách hàng");
        lblNewLabel_1.setBounds(82, 13, 97, 19);
        panel.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(201, 7, 180, 25);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng");
        lblNewLabel_1_1.setBounds(82, 66, 97, 19);
        panel.add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(201, 60, 180, 25);
        panel.add(textField_1);

        JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
        lblNewLabel_1_2.setBounds(549, 10, 80, 13);
        panel.add(lblNewLabel_1_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(651, 7, 180, 25);
        panel.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(651, 60, 180, 25);
        panel.add(textField_3);

        JLabel lblNewLabel_1_2_1 = new JLabel("Giới tính");
        lblNewLabel_1_2_1.setBounds(549, 63, 80, 13);
        panel.add(lblNewLabel_1_2_1);
        
        searchButton.addActionListener(e -> {
            String query = txtSearch.getText();
            String criteria = cbSearchCriteria.getSelectedItem().toString();
            if (!query.equals(placeholder) && !query.isEmpty()) {
                try {
                    DAO_KhachHang dao = new DAO_KhachHang();
                    List<KhachHang> result = dao.searchKhachHang(query, criteria);
                    
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Xóa dữ liệu cũ
                    int stt = 1;
                    for (KhachHang kh : result) {
                        model.addRow(new Object[] {
                            Boolean.FALSE,
                            stt++,
                            kh.getMaKH(),
                            kh.getTenKH(),
                            kh.getSdt(),
                            kh.getGioiTinh()
                        });
                    }
                    if (result.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void init() {
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:70;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
    }

    // Hàm load dữ liệu từ DAO vào bảng
    private void loadDataToTable() {
        try {
            DAO_KhachHang dao = new DAO_KhachHang();
            List<KhachHang> list = dao.getAllKhachHang();
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            int stt = 1;
            for (KhachHang kh : list) {
                model.addRow(new Object[] {
                    Boolean.FALSE,
                    stt++,
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getSdt(),
                    kh.getGioiTinh()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Class RoundedScrollPane giữ nguyên
    class RoundedScrollPane extends JScrollPane {
        private int cornerRadius;

        public RoundedScrollPane(Component view, int radius) {
            super(view);
            this.cornerRadius = radius;
            setOpaque(false);
            setBorder(new EmptyBorder(5, 5, 5, 5));
            getViewport().setOpaque(false);
            getViewport().setBackground(new Color(0, 0, 0, 0));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
            g2.dispose();
        }
    }

    // Class RoundedPane được thêm vào
    class RoundedPane extends JPanel {
        private int cornerRadius;

        public RoundedPane(int radius) {
            this.cornerRadius = radius;
            setOpaque(false);
            setBorder(new EmptyBorder(5, 5, 5, 5));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));
            g2.dispose();
        }
    }
}