package gui.panelForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import dao.DAO_KhachHang;
import entity.KhachHang;
import gui.tabbed.CheckBoxTableHeaderRenderer;
import gui.tabbed.TableHeaderAlignment;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;

public class panelQlyKhachhang extends JPanel {
    private JTable table;
    private JLabel lbTitle;
    private JButton btnThm;
    private JButton btnXa;
    private JButton btnSua;
    private DAO_KhachHang dao;

    public panelQlyKhachhang() {
        setSize(1535, 850);
        dao = new DAO_KhachHang(); // Khởi tạo DAO
        initComponents();
        init();
        try {
            loadData(); // Tải dữ liệu từ DB khi khởi tạo
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void initComponents() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        lbTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setBounds(10, 10, 1515, 38);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        UIManager.put("Button.background", new Color(52, 102, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219));

        RoundedScrollPane scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(30, 88, 1484, 730);
        scrollPane.setBackground(Color.WHITE);
        table = new JTable();

        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"#", "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính"}
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                Class[] types = new Class[] {Boolean.class, Integer.class, String.class, String.class, String.class, String.class};
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0;
            }
        });

        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        
        
     // Cài đặt tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // Center-align all headers
                c.setFont(new Font("Segoe UI", Font.BOLD, 13));
                return c;
            }
        });

        // Cài đặt renderer checkbox cho cột đầu tiên
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // Căn giữa dữ liệu các cột (trừ cột checkbox)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Đặt chiều rộng cột cố định
        if (table.getColumnModel().getColumnCount() > 0) {
            TableColumn column0 = table.getColumnModel().getColumn(0); // SELECT
            column0.setMinWidth(50);
            column0.setMaxWidth(100);
            column0.setPreferredWidth(100);

            TableColumn column1 = table.getColumnModel().getColumn(1); // STT
            column1.setMinWidth(50);
            column1.setMaxWidth(100);
            column1.setPreferredWidth(100);

            TableColumn column2 = table.getColumnModel().getColumn(2); // Mã khách hàng
            column2.setMinWidth(150);
            column2.setMaxWidth(300);
            column2.setPreferredWidth(300);

            TableColumn column3 = table.getColumnModel().getColumn(3); // Tên khách hàng
            column3.setMinWidth(250);
            column3.setMaxWidth(500);
            column3.setPreferredWidth(500);

            TableColumn column4 = table.getColumnModel().getColumn(4); // Số điện thoại
            column4.setMinWidth(150);
            column4.setMaxWidth(300);
            column4.setPreferredWidth(300);

            TableColumn column5 = table.getColumnModel().getColumn(5); // Giới tính
            column5.setMinWidth(100);
            column5.setMaxWidth(300);
            column5.setPreferredWidth(300);
        }	

        setLayout(null);

        add(scrollPane);
        add(lbTitle);

        btnThm = new JButton("Thêm");
        btnThm.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnThm.setBounds(1175, 45, 107, 33);
        btnThm.addActionListener(e -> openCreatePanel());
        add(btnThm);

        btnXa = new JButton("Xóa");
        btnXa.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnXa.setForeground(Color.WHITE);
        btnXa.setBackground(new Color(255, 69, 0));
        btnXa.setBounds(1292, 45, 107, 33);
        btnXa.addActionListener(e -> cmdDeleteActionPerformed());
        add(btnXa);

        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSua.setBackground(Color.GREEN);
        btnSua.setBounds(1407, 45, 107, 33);
        btnSua.addActionListener(e -> cmdEditActionPerformed());
        add(btnSua);
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

    private void loadData() throws Exception {
        List<KhachHang> list = dao.getAllKhachHang();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (KhachHang kh : list) {
            model.addRow(new Object[]{false, stt++, kh.getMaKH(), kh.getTenKH(), kh.getSdt(), kh.getGioiTinh()});
        }
    }

    private void openCreatePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 250));

        panelCreate create = new panelCreate();
        mainPanel.add(create, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);

        JButton btnThoat = new JButton("Thoát");
        btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThoat.setBackground(new Color(255, 99, 71)); 
        btnThoat.setForeground(Color.WHITE);

        JButton btnLuu = new JButton("Lưu");
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLuu.setBackground(new Color(76, 175, 80)); 
        btnLuu.setForeground(Color.WHITE);

        buttonPanel.add(btnThoat);
        buttonPanel.add(btnLuu);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };

        // Sử dụng SimplePopupBorder với mảng actions rỗng để không hiển thị nút mặc định
        SimplePopupBorder popupBorder = new SimplePopupBorder(mainPanel, "Thêm khách hàng", new String[]{}, null);

        GlassPanePopup.showPopup(popupBorder, option);

        // Thêm ActionListener cho các nút
        btnThoat.addActionListener(e -> GlassPanePopup.closePopupLast());
        btnLuu.addActionListener(e -> {
        	try {
                KhachHang kh = create.getData();
                if (kh != null) {
                    if (dao.checkMaKHExists(kh.getMaKH())) {
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Mã khách hàng đã tồn tại!");
                        return;
                    }
                    dao.addKhachHang(kh);
                    loadData();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thêm khách hàng thành công!");
                    GlassPanePopup.closePopupLast();
                }
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi thêm khách hàng: " + ex.getMessage());
            }
        });
    }

    private void cmdEditActionPerformed() {
        List<KhachHang> list = getSelectedData();
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                KhachHang data = list.get(0);
                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setPreferredSize(new Dimension(400, 250));

                panelCreate create = new panelCreate();
                create.setData(data);
                mainPanel.add(create, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
                buttonPanel.setOpaque(false);

                JButton btnHuy = new JButton("Hủy");
                btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btnHuy.setBackground(new Color(255, 99, 71)); // Màu đỏ nhạt (tomato)
                btnHuy.setForeground(Color.WHITE);

                JButton btnCapNhat = new JButton("Cập nhật");
                btnCapNhat.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btnCapNhat.setBackground(new Color(76, 175, 80)); // Sử dụng màu focus từ theme (#82AAFF)
                btnCapNhat.setForeground(Color.WHITE);

                buttonPanel.add(btnHuy);
                buttonPanel.add(btnCapNhat);
                mainPanel.add(buttonPanel, BorderLayout.SOUTH);

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                SimplePopupBorder popupBorder = new SimplePopupBorder(mainPanel, "Sửa khách hàng [" + data.getTenKH() + "]", new String[]{}, null);

                GlassPanePopup.showPopup(popupBorder, option);

                btnHuy.addActionListener(e -> GlassPanePopup.closePopupLast());
                btnCapNhat.addActionListener(e -> {
                    try {
                        KhachHang updatedKH = create.getData();
                        if (updatedKH != null) {
                            dao.updateKhachHang(updatedKH);
                            loadData();
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Cập nhật khách hàng thành công!");
                        }
                        GlassPanePopup.closePopupLast();
                    } catch (Exception ex) {
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi cập nhật: " + ex.getMessage());
                    }
                });
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chỉ chọn một khách hàng để sửa!");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn khách hàng để sửa!");
        }
    }

    private void cmdDeleteActionPerformed() {
        List<KhachHang> list = getSelectedData();
        if (!list.isEmpty()) {
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setPreferredSize(new Dimension(400, 150));

            JLabel label = new JLabel("Bạn có chắc muốn xóa " + list.size() + " khách hàng?");
            label.setBorder(new EmptyBorder(0, 25, 0, 25));
            mainPanel.add(label, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            buttonPanel.setOpaque(false);

            JButton btnHuy = new JButton("Hủy");
            btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnHuy.setBackground(new Color(255, 99, 71)); // Màu đỏ nhạt (tomato)
            btnHuy.setForeground(Color.WHITE);

            JButton btnXoa = new JButton("Xóa");
            btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnXoa.setBackground(new Color(76, 175, 80)); // Màu đỏ đậm (orangered)
            btnXoa.setForeground(Color.WHITE);

            buttonPanel.add(btnHuy);
            buttonPanel.add(btnXoa);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            SimplePopupBorder popupBorder = new SimplePopupBorder(mainPanel, "Xác nhận xóa", new String[]{}, null);

            GlassPanePopup.showPopup(popupBorder, option);

            btnHuy.addActionListener(e -> GlassPanePopup.closePopupLast());
            btnXoa.addActionListener(e -> {
                try {
                    for (KhachHang kh : list) {
                        dao.deleteKhachHang(kh.getMaKH());
                    }
                    loadData();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Xóa khách hàng thành công!");
                    GlassPanePopup.closePopupLast();
                } catch (Exception ex) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi xóa: " + ex.getMessage());
                }
            });
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn khách hàng để xóa!");
        }
    }

    private List<KhachHang> getSelectedData() {
        List<KhachHang> list = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 0)) {
                KhachHang kh = new KhachHang();
                kh.setMaKH((String) model.getValueAt(i, 2));
                kh.setTenKH((String) model.getValueAt(i, 3));
                kh.setSdt((String) model.getValueAt(i, 4));
                kh.setGioiTinh((String) model.getValueAt(i, 5));
                list.add(kh);
            }
        }
        return list;
    }

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
}