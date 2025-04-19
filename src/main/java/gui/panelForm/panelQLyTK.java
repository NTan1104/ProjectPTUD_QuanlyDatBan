package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import gui.tabbed.*;
import com.formdev.flatlaf.FlatClientProperties;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import dao.DAO_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class panelQLyTK extends JPanel {
    private JComboBox<String> cbMaNhanVien; // Chọn mã nhân viên
    private JTextField txtTenDangNhap; // Nhập tên đăng nhập
    private JTextField txtMatKhau; // Nhập mật khẩu
    private JTable tblTaiKhoan; // Bảng tài khoản (không sử dụng)
    private DefaultTableModel mdlDanhSachTaiKhoan; // Mô hình dữ liệu bảng
    private RoundedScrollPane scrDanhSachTaiKhoan; // ScrollPane chứa bảng
    private JTable tblDanhSachTaiKhoan; // Bảng hiển thị danh sách tài khoản
    private DAO_TaiKhoan daoTaiKhoan;

    public panelQLyTK() throws Exception {
        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);

        daoTaiKhoan = new DAO_TaiKhoan();

        // Cài đặt font FlatLaf
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        UIManager.put("Panel.background", new Color(247, 248, 252));
        UIManager.put("Button.background", new Color(52, 102, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219));
        UIManager.put("Label.foreground", new Color(17, 24, 39));
        UIManager.put("Component.borderColor", new Color(229, 231, 235));

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("QUẢN LÝ TÀI KHOẢN");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setBounds(0, 10, 1535, 27);
        add(lblTieuDe);

        JPanel pnlNhapThongTin = new JPanel();
        pnlNhapThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        pnlNhapThongTin.setPreferredSize(new Dimension(400, 100));

        JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
        lblMaNhanVien.setBounds(1, 1, 193, 41);
        lblMaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblTenDangNhap = new JLabel("Tên tài khoản");
        lblTenDangNhap.setBounds(1, 55, 193, 41);
        lblTenDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        txtMatKhau = new JTextField();
        txtMatKhau.setColumns(10);
        setupPlaceholder(txtMatKhau, "Nhập mật khẩu");
        txtMatKhau.setBounds(204, 110, 253, 41);
        pnlNhapThongTin.setLayout(null);

        pnlNhapThongTin.add(lblMaNhanVien);
        pnlNhapThongTin.add(lblTenDangNhap);
        pnlNhapThongTin.add(txtMatKhau);

        JPanel pnlChucNang = new JPanel();
        pnlChucNang.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(45, 50, 120, 50);
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(240, 50, 120, 50);
        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(430, 50, 120, 50);
        JButton btnTim = new JButton("Tìm kiếm");
        btnTim.setBounds(620, 50, 120, 50);
        pnlChucNang.setLayout(null);

        pnlChucNang.add(btnThem);
        pnlChucNang.add(btnXoa);
        pnlChucNang.add(btnSua);
        pnlChucNang.add(btnTim);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlNhapThongTin, pnlChucNang);

        JLabel lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
        lblMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMatKhau.setBounds(11, 109, 193, 41);
        pnlNhapThongTin.add(lblMatKhau);

        cbMaNhanVien = new JComboBox();
        cbMaNhanVien.setBounds(204, 7, 253, 30);
        pnlNhapThongTin.add(cbMaNhanVien);
        cbMaNhanVien.setModel(new DefaultComboBoxModel(new String[] {"NV002"}));
        
        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setBounds(204, 56, 253, 41);
        pnlNhapThongTin.add(txtTenDangNhap);
        setupPlaceholder(txtTenDangNhap, "Nhập tên tài khoản");
        txtTenDangNhap.setColumns(10);

        splitPane.setBounds(50, 60, 1454, 161);
        splitPane.setDividerLocation(600);
        add(splitPane);

        String[] columnNames = {"STT", "Mã NV", "Tên tài khoản", "Mật khẩu", "Thời gian đăng nhập", "Thời gian đăng xuất", "Số giờ làm", "Trạng thái"};
        mdlDanhSachTaiKhoan = new DefaultTableModel(columnNames, 0);
        tblDanhSachTaiKhoan = new JTable(mdlDanhSachTaiKhoan);

        tblDanhSachTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblDanhSachTaiKhoan.setRowHeight(25);
        tblDanhSachTaiKhoan.setShowGrid(true);

        JTableHeader tableHeader = tblDanhSachTaiKhoan.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // ScrollPane chứa bảng
        scrDanhSachTaiKhoan = new RoundedScrollPane(tblDanhSachTaiKhoan, 30);
        scrDanhSachTaiKhoan.setBounds(50, 256, 1454, 563);
        add(scrDanhSachTaiKhoan);

        loadTableData();
        FlatRobotoFont.install();
    }

    private void loadTableData() throws Exception {
        mdlDanhSachTaiKhoan.setRowCount(0);
        List<TaiKhoan> taiKhoanList = daoTaiKhoan.loadTaiKhoanData();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int stt = 1;
        for (TaiKhoan tk : taiKhoanList) {
            String gioVaoLam = tk.getGioVaoLam() != null ? sdf.format(java.util.Date.from(tk.getGioVaoLam().atZone(ZoneId.systemDefault()).toInstant())) : "";
            String gioNghi = tk.getGioNghi() != null ? sdf.format(java.util.Date.from(tk.getGioNghi().atZone(ZoneId.systemDefault()).toInstant())) : "";
            Object[] row = {
                stt++,
                tk.getNhanVien().getMaNV(),
                tk.getTenDangNhap(),
                "****",
                gioVaoLam,
                gioNghi,
                tk.getSoGioLam(),
                tk.getTrangThai()
            };
            mdlDanhSachTaiKhoan.addRow(row);
        }
    }

    private void loadComboBoxData() throws Exception {
        List<TaiKhoan> taiKhoanList = daoTaiKhoan.loadTaiKhoanData();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (TaiKhoan tk : taiKhoanList) {
            model.addElement(tk.getNhanVien().getMaNV());
        }
        cbMaNhanVien.setModel(model);
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
}