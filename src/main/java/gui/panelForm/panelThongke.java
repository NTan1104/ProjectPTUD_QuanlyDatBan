package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.toedter.calendar.JDateChooser;

public class panelThongke extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable tblThongKe; // Bảng thống kê (không sử dụng)
    private DefaultTableModel mdlThongKe; // Mô hình bảng (không sử dụng)
    private JPanel pnlChiTiet; // Panel chi tiết (không sử dụng)
    private JButton btnThongKe; // Nút thống kê (không sử dụng)
    private RoundedScrollPane scrThongKe; // ScrollPane chứa bảng
    private JDateChooser dtcNgayKetThuc; // Chọn ngày kết thúc

    public panelThongke() {
        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);

        // Cài đặt font FlatLaf
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        // Tùy chỉnh màu sắc qua UIManager
        UIManager.put("Panel.background", new Color(247, 248, 252));
        UIManager.put("Button.background", new Color(52, 102, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219));
        UIManager.put("Label.foreground", new Color(17, 24, 39));
        UIManager.put("Component.borderColor", new Color(229, 231, 235));

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("THỐNG KÊ");
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setBounds(0, 10, 1535, 27);
        add(lblTieuDe);

        JPanel pnlTongQuan = new JPanel();
        pnlTongQuan.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTongQuan.setBounds(10, 50, 1515, 246);
        add(pnlTongQuan);
        pnlTongQuan.setLayout(null);

        JPanel pnlTongDoanhThu = new JPanel();
        pnlTongDoanhThu.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTongDoanhThu.setBounds(50, 25, 300, 196);
        pnlTongQuan.add(pnlTongDoanhThu);
        pnlTongDoanhThu.setLayout(null);

        JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu");
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongDoanhThu.setBounds(0, 10, 300, 24);
        pnlTongDoanhThu.add(lblTongDoanhThu);

        JLabel lblIconDoanhThu = new JLabel();
        lblIconDoanhThu.setBounds(10, 86, 56, 50);
        URL imgURL1 = getClass().getResource("/img/icons8-increase-50.png");
        if (imgURL1 != null) {
            lblIconDoanhThu.setIcon(new ImageIcon(imgURL1));
        } else {
            lblIconDoanhThu.setText("No Image");
        }
        pnlTongDoanhThu.add(lblIconDoanhThu);

        JPanel pnlTongSoBan = new JPanel();
        pnlTongSoBan.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTongSoBan.setLayout(null);
        pnlTongSoBan.setBounds(555, 25, 300, 196);
        pnlTongQuan.add(pnlTongSoBan);

        JLabel lblTongSoBan = new JLabel("Tổng số bàn");
        lblTongSoBan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTongSoBan.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongSoBan.setBounds(0, 10, 300, 24);
        pnlTongSoBan.add(lblTongSoBan);

        JLabel lblIconSoBan = new JLabel();
        lblIconSoBan.setBounds(24, 86, 56, 50);
        URL imgURL2 = getClass().getResource("/img/icons8-table-50.png");
        if (imgURL2 != null) {
            lblIconSoBan.setIcon(new ImageIcon(imgURL2));
        } else {
            lblIconSoBan.setText("No Image");
        }
        pnlTongSoBan.add(lblIconSoBan);

        JPanel pnlSoMonAn = new JPanel();
        pnlSoMonAn.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlSoMonAn.setLayout(null);
        pnlSoMonAn.setBounds(1060, 25, 300, 196);
        pnlTongQuan.add(pnlSoMonAn);

        JLabel lblSoMonAn = new JLabel("Số lượng đồ ăn được bán");
        lblSoMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSoMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoMonAn.setBounds(0, 10, 300, 24);
        pnlSoMonAn.add(lblSoMonAn);

        JLabel lblIconMonAn = new JLabel();
        lblIconMonAn.setBounds(21, 86, 62, 50);
        URL imgURL3 = getClass().getResource("/img/icons8-grilled-meat-64.png");
        if (imgURL3 != null) {
            lblIconMonAn.setIcon(new ImageIcon(imgURL3));
        } else {
            lblIconMonAn.setText("No Image");
        }
        pnlSoMonAn.add(lblIconMonAn);

        JComboBox<String> cbLoaiThoiGian = new JComboBox<>();
        cbLoaiThoiGian.setBounds(333, 310, 179, 21);
        cbLoaiThoiGian.addItem("Theo ngày");
        cbLoaiThoiGian.addItem("Theo tháng");
        cbLoaiThoiGian.addItem("Theo năm");
        add(cbLoaiThoiGian);

        JLabel lblLoaiThoiGian = new JLabel("Loại thời gian");
        lblLoaiThoiGian.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblLoaiThoiGian.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoaiThoiGian.setBounds(170, 306, 153, 27);
        add(lblLoaiThoiGian);

        dtcNgayKetThuc = new JDateChooser();
        dtcNgayKetThuc.setBounds(985, 310, 120, 19);
        dtcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        add(dtcNgayKetThuc);

        JPanel pnlBieuDo = new JPanel();
        TitledBorder titledBorder = new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Biểu đồ", TitledBorder.CENTER, TitledBorder.TOP);
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 15));
        pnlBieuDo.setBorder(titledBorder);
        pnlBieuDo.setBounds(10, 358, 1515, 411);
        add(pnlBieuDo);
        pnlBieuDo.setLayout(null);

        JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
        lblNgayBatDau.setHorizontalAlignment(SwingConstants.CENTER);
        lblNgayBatDau.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNgayBatDau.setBounds(532, 306, 153, 27);
        add(lblNgayBatDau);

        JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
        lblNgayKetThuc.setHorizontalAlignment(SwingConstants.CENTER);
        lblNgayKetThuc.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNgayKetThuc.setBounds(829, 306, 153, 27);
        add(lblNgayKetThuc);

        JDateChooser dtcNgayBatDau = new JDateChooser();
        dtcNgayBatDau.setDateFormatString("dd/MM/yyyy");
        dtcNgayBatDau.setBounds(678, 310, 120, 19);
        add(dtcNgayBatDau);
    }

    class RoundedScrollPane extends JScrollPane {
        private static final long serialVersionUID = 1L;
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

    class RoundedPane extends JPanel {
        private static final long serialVersionUID = 1L;
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