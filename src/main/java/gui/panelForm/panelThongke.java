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
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel detailPanel;
    private JButton btnNewButton;
    private RoundedScrollPane scrollPane;
    private JDateChooser dateChooserEnd;

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
        UIManager.put("Panel.background", new Color(247, 248, 252)); // Trắng xám nhạt nhẹ nhàng
        UIManager.put("Button.background", new Color(52, 102, 255)); // Xanh dương nhẹ cho nút
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219)); // Xám nhạt khi vô hiệu
        UIManager.put("Label.foreground", new Color(17, 24, 39)); // Xám đen đậm cho chữ
        UIManager.put("Component.borderColor", new Color(229, 231, 235)); // Viền xám nhạt

        // Tiêu đề
        JLabel lblTitle = new JLabel("THỐNG KÊ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 1535, 27);
        add(lblTitle);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 50, 1515, 246);
        add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(50, 25, 300, 196);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Tổng doanh thu");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 10, 300, 24);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel();
        lblNewLabel_2.setBounds(10, 86, 56, 50);
        // Tải hình ảnh bằng getClass().getResource()
        URL imgURL1 = getClass().getResource("/img/icons8-increase-50.png");
        if (imgURL1 != null) {
            lblNewLabel_2.setIcon(new ImageIcon(imgURL1));
        } else {
            lblNewLabel_2.setText("No Image");
        }
        panel_1.add(lblNewLabel_2);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1_1.setLayout(null);
        panel_1_1.setBounds(555, 25, 300, 196);
        panel.add(panel_1_1);

        JLabel lblNewLabel_1 = new JLabel("Tổng số bàn");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(0, 10, 300, 24);
        panel_1_1.add(lblNewLabel_1);

        JLabel lblNewLabel_2_1 = new JLabel();
        lblNewLabel_2_1.setBounds(24, 86, 56, 50);
        // Tải hình ảnh bằng getClass().getResource()
        URL imgURL2 = getClass().getResource("/img/icons8-table-50.png");
        if (imgURL2 != null) {
            lblNewLabel_2_1.setIcon(new ImageIcon(imgURL2));
        } else {
            lblNewLabel_2_1.setText("No Image");
        }
        panel_1_1.add(lblNewLabel_2_1);

        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBounds(1060, 25, 300, 196);
        panel.add(panel_1_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("Số lượng đồ ăn được bán");
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(0, 10, 300, 24);
        panel_1_1_1.add(lblNewLabel_1_1);

        JLabel lblNewLabel_2_2 = new JLabel();
        lblNewLabel_2_2.setBounds(21, 86, 62, 50);
        // Tải hình ảnh bằng getClass().getResource()
        URL imgURL3 = getClass().getResource("/img/icons8-grilled-meat-64.png");
        if (imgURL3 != null) {
            lblNewLabel_2_2.setIcon(new ImageIcon(imgURL3));
        } else {
            lblNewLabel_2_2.setText("No Image");
        }
        panel_1_1_1.add(lblNewLabel_2_2);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(333, 310, 179, 21);
        comboBox.addItem("Theo ngày");
        comboBox.addItem("Theo tháng");
        comboBox.addItem("Theo năm");
        add(comboBox);

        JLabel lblNewLabel_3 = new JLabel("Loại thời gian");
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(170, 306, 153, 27);
        add(lblNewLabel_3);

        // Thêm JDateChooser cho Ngày kết thúc
        dateChooserEnd = new JDateChooser();
        dateChooserEnd.setBounds(985, 310, 120, 19);
        dateChooserEnd.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày
        add(dateChooserEnd);

        JPanel panel_2 = new JPanel();
        TitledBorder titledBorder = new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Biểu đồ", TitledBorder.CENTER, TitledBorder.TOP);
        titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 15)); // Đặt font
        panel_2.setBorder(titledBorder); // Gán TitledBorder đã tùy chỉnh
        panel_2.setBounds(10, 358, 1515, 411);
        add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_3_1 = new JLabel("Ngày bắt đầu");
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_3_1.setBounds(532, 306, 153, 27);
        add(lblNewLabel_3_1);

        JLabel lblNewLabel_3_1_1 = new JLabel("Ngày kết thúc");
        lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_3_1_1.setBounds(829, 306, 153, 27);
        add(lblNewLabel_3_1_1);

        JDateChooser dateChooserEnd_1 = new JDateChooser();
        dateChooserEnd_1.setDateFormatString("dd/MM/yyyy");
        dateChooserEnd_1.setBounds(678, 310, 120, 19);
        add(dateChooserEnd_1);
    }

    class RoundedScrollPane extends JScrollPane {
        private static final long serialVersionUID = 1L;
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
        private static final long serialVersionUID = 1L;
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