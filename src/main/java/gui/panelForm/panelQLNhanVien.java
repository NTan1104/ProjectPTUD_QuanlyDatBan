package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class panelQLNhanVien extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTable table;
    private RoundedPane panel;
    private RoundedScrollPane scrollPane;

    public panelQLNhanVien() {
        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        JLabel lblNewLabel = new JLabel("QUẢN LÍ NHÂN VIÊN");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblNewLabel.setBounds(0, 0, 1535, 44);
        add(lblNewLabel);

        panel = new RoundedPane(30);
        panel.setBounds(10, 37, 1515, 252);
        add(panel);
        panel.setLayout(null);

        JPanel linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK); 
                g.drawLine(0, 40, 1515, 40); 
            }
        };
        linePanel.setOpaque(false);

        linePanel.setBounds(0, 0, 1515, 252);
        panel.add(linePanel);
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(50, 65, 183, 164);
        lblNewLabel_1.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK)); 
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Mã Nhân Viên:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setBounds(400, 62, 115, 16);
        panel.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(520, 62, 270, 19);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Tên Nhân Viên:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_3.setBounds(400, 112, 115, 16);
        panel.add(lblNewLabel_3);

        textField_1 = new JTextField();
        textField_1.setBounds(520, 112, 270, 19);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Số Điện Thoại:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_4.setBounds(400, 162, 115, 19);
        panel.add(lblNewLabel_4);

        textField_2 = new JTextField();
        textField_2.setBounds(520, 162, 270, 19);
        panel.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Giới Tính:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_5.setBounds(400, 212, 115, 19);
        panel.add(lblNewLabel_5);

        textField_3 = new JTextField();
        textField_3.setBounds(520, 212, 270, 19);
        panel.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Chức vụ:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_6.setBounds(930, 62, 100, 16);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Tuổi:");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_7.setBounds(930, 112, 100, 13);
        panel.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Email:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_8.setBounds(930, 162, 100, 13);
        panel.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Hệ số lương:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_9.setBounds(930, 212, 100, 13);
        panel.add(lblNewLabel_9);

        textField_4 = new JTextField();
        textField_4.setBounds(1060, 62, 270, 19);
        panel.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(1060, 112, 270, 19);
        panel.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(1060, 162, 270, 19);
        panel.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setBounds(1060, 212, 270, 19);
        panel.add(textField_7);
        textField_7.setColumns(10);

        JLabel lblNewLabel_10 = new JLabel("Thông tin nhân viên");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_10.setBounds(50, 10, 183, 27);
        panel.add(lblNewLabel_10);

        Object[][] data = {
//                {new ImageIcon(getClass().getResource("/img/a1.jpg")), "NV001", "Nguyễn Văn A", "Nam", 18, "NVLT", "25K/h", null},
//                {new ImageIcon(getClass().getResource("/img/a2.jpg")), "NV002", "Trần Thị B", "Nữ", 22, "NVQL", "22K/h", null}
        };

        String[] columnNames = {"", "Mã NV", "Tên NV", "Giới tính", "Tuổi", "Chức vụ", "Hệ số lương", "Xử lí"};

        // **Tạo model cho bảng**
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return ImageIcon.class;
                if (column == 7) return Object.class; // For the icons in the last column
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable by default
            }
        };



        table = new JTable(model);
        table.setRowHeight(60);
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(50));
        table.getColumnModel().getColumn(7).setCellRenderer(new ActionRenderer(25)); // Use a new renderer for action icons

        scrollPane = new RoundedScrollPane(table, 30);
        scrollPane.setBounds(10, 299, 1515, 525);
        add(scrollPane);
    }

    class ImageRenderer extends DefaultTableCellRenderer {
        private int size;

        public ImageRenderer(int size) {
            this.size = size;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                ImageIcon icon = (ImageIcon) value;
                ImageIcon resizedIcon = getRoundedIcon(icon, size); // Resize và bo tròn

                JLabel label = new JLabel(resizedIcon);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        private ImageIcon getRoundedIcon(ImageIcon icon, int size) {
            Image img = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = bufferedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Tạo vùng hình tròn
            g2.setClip(new Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(img, 0, 0, size, size, null);
            g2.dispose();

            return new ImageIcon(bufferedImage);
        }
    }

    class ActionRenderer extends DefaultTableCellRenderer {
        private int size;

        public ActionRenderer(int size) {
            this.size = size;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == 7) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                panel.setOpaque(false);

//                ImageIcon editIcon = new ImageIcon(getClass().getResource("/img/edit-246-16.png")); 
//                ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/img/icontru.png"));

//                Image resizedEdit = editIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
//                ImageIcon finalEditIcon = new ImageIcon(resizedEdit);
//                Image resizedDelete = deleteIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
//                ImageIcon finalDeleteIcon = new ImageIcon(resizedDelete);

//                JLabel editLabel = new JLabel(finalEditIcon);
//                JLabel deleteLabel = new JLabel(finalDeleteIcon);
//
//                panel.add(editLabel);
//                panel.add(deleteLabel);

                // Center the panel horizontally within the cell
                setHorizontalAlignment(SwingConstants.CENTER);
                // Center the content vertically within the cell
                setVerticalAlignment(SwingConstants.CENTER);
                return panel;
            }
            // For other columns, ensure default alignment
            setHorizontalAlignment(SwingConstants.LEFT);
            setVerticalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
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