package gui.panelForm;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import dao.DAO_MonAn;
import entity.MonAn;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SuppressWarnings("serial")
public class panelQlyMonAn extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textFieldHinhAnh;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JTextArea textArea;
    private JLabel lblHinhAnhPreview;	
    private JPanel panel;
    private DAO_MonAn daoMonAn;
    private int columns;
    private int panelWidth;
    private String placeholder;

    public panelQlyMonAn() throws Exception {
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


        // Khởi tạo các biến instance
        daoMonAn = new DAO_MonAn();
        columns = 4;
        panelWidth = 1200;
        placeholder = "Tìm kiếm theo mã món ăn, tên món ăn";


        panel = new JPanel();
        panel.setBounds(10, 47, 1200, 793);
        add(panel);
        panel.setLayout(null);

        List<MonAn> monAnList = daoMonAn.getAllMonAn();
        int panelHeight = (int) Math.ceil((double) monAnList.size() / columns) * (340 + 33);
        panel.setBounds(0, 0, panelWidth, panelHeight);
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        for (int i = 0; i < monAnList.size(); i++) {
            MonAn monAn = monAnList.get(i);
            int row = i / columns;
            int col = i % columns;
            int x = 10 + col * (270 + 39);
            int y = 10 + row * (340 + 33);
            JPanel dishPanel = createMonAnPanel(
                monAn.getTenMonAn(),
                monAn.getLinkIMG(),
                monAn.getMaMonAn(),
                String.valueOf(monAn.getGia()),
                monAn.getLoaiMonAn(),
                monAn.getGhiChu()
            );
            dishPanel.setBounds(x, y, 270, 340);
            panel.add(dishPanel);
        }

        // Bọc monAnPanel trong JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 47, 1200, 793);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
            "Danh sách món ăn", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_2.setBounds(1232, 47, 303, 793);
        add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Mã món ăn");
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(10, 40, 81, 35);
        panel_2.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(111, 40, 182, 30);
        panel_2.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Tên món ăn");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(10, 96, 91, 35);
        panel_2.add(lblNewLabel_2_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(111, 102, 182, 30);
        panel_2.add(textField_1);

        JLabel lblNewLabel_2_1_1 = new JLabel("Giá món ăn");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2_1_1.setBounds(10, 155, 91, 35);
        panel_2.add(lblNewLabel_2_1_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(111, 163, 182, 30);
        panel_2.add(textField_2);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("Loại món ăn");
        lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2_1_1_1.setBounds(10, 220, 91, 35);
        panel_2.add(lblNewLabel_2_1_1_1);

        comboBox = new JComboBox<>();
        comboBox.setBounds(111, 220, 134, 31);
        panel_2.add(comboBox);

        comboBox.addItem("");
        comboBox.addItem("Đồ nướng");
        comboBox.addItem("Đồ uống");
        comboBox.addItem("Món phụ");

        JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Ghi chú");
        lblNewLabel_2_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2_1_1_1_1.setBounds(10, 276, 108, 35);
        panel_2.add(lblNewLabel_2_1_1_1_1);

        textArea = new JTextArea();
        textArea.setBounds(10, 305, 283, 150);
        panel_2.add(textArea);
        
        JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Hình ảnh");
        lblNewLabel_2_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1_1_2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNewLabel_2_1_1_1_2.setBounds(10, 465, 91, 35);
        panel_2.add(lblNewLabel_2_1_1_1_2);
        
     // JTextField hiển thị đường dẫn hình ảnh
        textFieldHinhAnh = new JTextField();
        textFieldHinhAnh.setBounds(111, 465, 182, 30);
        textFieldHinhAnh.setEditable(false); // Không cho phép chỉnh sửa trực tiếp
        panel_2.add(textFieldHinhAnh);
        textFieldHinhAnh.setColumns(10);
        
     // Nút chọn hình
        JButton btnChonHinh = new JButton("Chọn hình");
        btnChonHinh.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnChonHinh.setBounds(111, 505, 100, 30);
        panel_2.add(btnChonHinh);
        
     // JLabel để hiển thị hình ảnh
        lblHinhAnhPreview = new JLabel("Hình ảnh sẽ hiển thị ở đây", SwingConstants.CENTER);
        lblHinhAnhPreview.setBounds(10, 545, 283, 200);
        lblHinhAnhPreview.setBackground(Color.LIGHT_GRAY);
        lblHinhAnhPreview.setOpaque(true);
        panel_2.add(lblHinhAnhPreview);

        // ActionListener cho nút chọn hình
        btnChonHinh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
         // Thiết lập thư mục mặc định là src/main/resources/img
            File defaultDirectory = new File("src/main/resources/img");
            if (defaultDirectory.exists()) {
                fileChooser.setCurrentDirectory(defaultDirectory);
            } else {
                // Nếu thư mục không tồn tại, tạo thư mục
                try {
                    Files.createDirectories(defaultDirectory.toPath());
                    fileChooser.setCurrentDirectory(defaultDirectory);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "gif");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    // Lấy file được chọn
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    Path sourcePath = selectedFile.toPath();

                    // Đường dẫn đích: src/main/resources/img
                    Path targetDir = Paths.get("src/main/resources/img");
                    if (!Files.exists(targetDir)) {
                        Files.createDirectories(targetDir);
                    }
                    String targetFileName = selectedFile.getName();
                    Path targetPath = Paths.get("src/main/resources/img/" + targetFileName);

                    // Sao chép file vào thư mục đích
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // Tạo đường dẫn tương đối để lưu vào database
                    String relativePath = "/img/" + targetFileName;
                    textFieldHinhAnh.setText(relativePath);

                    // Hiển thị hình ảnh lên JLabel bằng getClass().getResource()
                    URL imgURL = getClass().getResource(relativePath);
                    if (imgURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imgURL);
                        Image scaledImage = imageIcon.getImage().getScaledInstance(283, 200, Image.SCALE_SMOOTH);
                        lblHinhAnhPreview.setIcon(new ImageIcon(scaledImage));
                        lblHinhAnhPreview.setText("");
                    } else {
                        lblHinhAnhPreview.setIcon(null);
                        lblHinhAnhPreview.setText("Không tải được hình ảnh");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    lblHinhAnhPreview.setIcon(null);
                    lblHinhAnhPreview.setText("Lỗi tải hình ảnh");
                }
            }
        });

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Tất cả");
        rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        rdbtnNewRadioButton.setBounds(10, 18, 103, 21);
        add(rdbtnNewRadioButton);

        JRadioButton rdbtnn = new JRadioButton("Đồ nướng");
        rdbtnn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        rdbtnn.setBounds(150, 18, 103, 21);
        add(rdbtnn);

        JRadioButton rdbtnUng = new JRadioButton("Đồ uống");
        rdbtnUng.setFont(new Font("Segoe UI", Font.BOLD, 15));
        rdbtnUng.setBounds(290, 18, 103, 21);
        add(rdbtnUng);

        JRadioButton rdbtnMnnKm = new JRadioButton("Món ăn kèm");
        rdbtnMnnKm.setFont(new Font("Segoe UI", Font.BOLD, 15));
        rdbtnMnnKm.setBounds(430, 18, 115, 21);
        add(rdbtnMnnKm);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnn);
        group.add(rdbtnUng);
        group.add(rdbtnMnnKm);

        // Thêm các nút "Thêm", "Xóa", "Sửa" bên trái thanh tìm kiếm
        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnNewButton.setBounds(800, 10, 108, 35); // Cùng hàng với textField_3 (y=10), kích thước 108x35
        add(btnNewButton);

        JButton btnXa = new JButton("Xóa");
        btnXa.setForeground(Color.WHITE);
        btnXa.setBackground(Color.RED);
        btnXa.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnXa.setBounds(950, 10, 108, 35); // Ngay sau nút "Thêm"
        add(btnXa);

        JButton btnSa = new JButton("Cập nhật");
        btnSa.setBackground(Color.GREEN);
        btnSa.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSa.setBounds(1100, 10, 108, 35); // Ngay sau nút "Xóa"
        add(btnSa);

        ActionListener filterListener = e -> {
            String selectedType = e.getActionCommand();
            try {
                List<MonAn> result = daoMonAn.filterMonAnByLoai(selectedType);
                panel.removeAll();
                int i = 0;
                for (MonAn monAn : result) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        monAn.getTenMonAn(),
                        monAn.getLinkIMG(),
                        monAn.getMaMonAn(),
                        String.valueOf(monAn.getGia()),
                        monAn.getLoaiMonAn(),
                        monAn.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    panel.add(dishPanel);
                    i++;
                }
                int panelHeightFilter = (int) Math.ceil((double) i / columns) * (340 + 33);
                panel.setBounds(0, 0, panelWidth, panelHeightFilter);
                panel.setPreferredSize(new Dimension(panelWidth, panelHeightFilter));
                panel.revalidate();
                panel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

        rdbtnNewRadioButton.setActionCommand("Tất cả");
        rdbtnn.setActionCommand("Đồ nướng");
        rdbtnUng.setActionCommand("Đồ uống");
        rdbtnMnnKm.setActionCommand("Món ăn kèm");

        rdbtnNewRadioButton.addActionListener(filterListener);
        rdbtnn.addActionListener(filterListener);
        rdbtnUng.addActionListener(filterListener);
        rdbtnMnnKm.addActionListener(filterListener);

        rdbtnNewRadioButton.setSelected(true);

        // TextField tìm kiếm với placeholder và nút tìm kiếm bên trong
        textField_3 = new JTextField();
        textField_3.setBounds(1232, 10, 238, 35);
        textField_3.setColumns(10);

        // Placeholder
        textField_3.setText(placeholder);
        textField_3.setForeground(Color.GRAY);
        textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        textField_3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField_3.getText().equals(placeholder)) {
                    textField_3.setText("");
                    textField_3.setForeground(Color.BLACK);
                    textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField_3.getText().isEmpty()) {
                    textField_3.setText(placeholder);
                    textField_3.setForeground(Color.GRAY);
                    textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 10));
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
        searchButton.setBackground(textField_3.getBackground());
        searchButton.setFocusPainted(false);
        searchButton.setContentAreaFilled(false);
        textField_3.setLayout(new BorderLayout());
        textField_3.add(searchButton, BorderLayout.EAST);
        textField_3.setBorder(BorderFactory.createCompoundBorder(
            textField_3.getBorder(),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        add(textField_3);

        comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(1472, 10, 63, 35);
        add(comboBox_1);

        comboBox_1.addItem("");
        comboBox_1.addItem("Mã món ăn");
        comboBox_1.addItem("Tên món ăn");

        searchButton.addActionListener(e -> {
            String query = textField_3.getText();
            String searchBy = comboBox_1.getSelectedItem().toString();
            if (!query.equals(placeholder) && !query.isEmpty()) {
                try {
                    List<MonAn> result = daoMonAn.searchMonAn(query, searchBy);
                    panel.removeAll();
                    int i = 0;
                    for (MonAn monAn : result) {
                        int row = i / columns;
                        int col = i % columns;
                        int x = 10 + col * (270 + 39);
                        int y = 10 + row * (340 + 33);
                        JPanel dishPanel = createMonAnPanel(
                            monAn.getTenMonAn(),
                            monAn.getLinkIMG(),
                            monAn.getMaMonAn(),
                            String.valueOf(monAn.getGia()),
                            monAn.getLoaiMonAn(),
                            monAn.getGhiChu()
                        );
                        dishPanel.setBounds(x, y, 270, 340);
                        panel.add(dishPanel);
                        i++;
                    }
                    int panelHeightSearch = (int) Math.ceil((double) i / columns) * (340 + 33);
                    panel.setBounds(0, 0, panelWidth, panelHeightSearch);
                    panel.setPreferredSize(new Dimension(panelWidth, panelHeightSearch));
                    panel.revalidate();
                    panel.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Thêm ActionListener cho btnNewButton (Thêm)
        btnNewButton.addActionListener(e -> {
            try {
                MonAn monAn = new MonAn(
                    textField.getText(),
                    textField_1.getText(),
                    Float.parseFloat(textField_2.getText()),
                    textArea.getText(),
                    comboBox.getSelectedItem().toString(),
                    textFieldHinhAnh.getText()
                );
                daoMonAn.addMonAn(monAn);

                // Cập nhật lại danh sách
                List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                panel.removeAll();
                int i = 0;
                for (MonAn ma : monAnListUpdated) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        ma.getTenMonAn(),
                        ma.getLinkIMG(),
                        ma.getMaMonAn(),
                        String.valueOf(ma.getGia()),
                        ma.getLoaiMonAn(),
                        ma.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    panel.add(dishPanel);
                    i++;
                }
                int panelHeightAdd = (int) Math.ceil((double) i / columns) * (340 + 33);
                panel.setBounds(0, 0, panelWidth, panelHeightAdd);
                panel.setPreferredSize(new Dimension(panelWidth, panelHeightAdd));
                panel.revalidate();
                panel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Thêm ActionListener cho btnXa (Xóa)
        btnXa.addActionListener(e -> {
            try {
                String maMonAn = textField.getText();
                daoMonAn.deleteMonAn(maMonAn);

                // Cập nhật lại danh sách
                List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                panel.removeAll();
                int i = 0;
                for (MonAn ma : monAnListUpdated) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        ma.getTenMonAn(),
                        ma.getLinkIMG(),
                        ma.getMaMonAn(),
                        String.valueOf(ma.getGia()),
                        ma.getLoaiMonAn(),
                        ma.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    panel.add(dishPanel);
                    i++;
                }
                int panelHeightDelete = (int) Math.ceil((double) i / columns) * (340 + 33);
                panel.setBounds(0, 0, panelWidth, panelHeightDelete);
                panel.setPreferredSize(new Dimension(panelWidth, panelHeightDelete));
                panel.revalidate();
                panel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Thêm ActionListener cho btnSa (Sửa)
        btnSa.addActionListener(e -> {
            try {
                MonAn monAn = new MonAn(
                    textField.getText(),
                    textField_1.getText(),
                    Float.parseFloat(textField_2.getText()),
                    textArea.getText(),
                    comboBox.getSelectedItem().toString(),
                    textFieldHinhAnh.getText()
                );
                daoMonAn.updateMonAn(monAn);

                // Cập nhật lại danh sách
                List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                panel.removeAll();
                int i = 0;
                for (MonAn ma : monAnListUpdated) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        ma.getTenMonAn(),
                        ma.getLinkIMG(),
                        ma.getMaMonAn(),
                        String.valueOf(ma.getGia()),
                        ma.getLoaiMonAn(),
                        ma.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    panel.add(dishPanel);
                    i++;
                }
                int panelHeightUpdate = (int) Math.ceil((double) i / columns) * (340 + 33);
                panel.setBounds(0, 0, panelWidth, panelHeightUpdate);
                panel.setPreferredSize(new Dimension(panelWidth, panelHeightUpdate));
                panel.revalidate();
                panel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private JPanel createMonAnPanel(String tenMon, String imagePath, String maMon, String giaMon, String loaiMon, String ghichu) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(null);

        // Hình ảnh
        JLabel lblImage;
        if (imagePath != null) {
            URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image scaledImage = originalIcon.getImage().getScaledInstance(270, 200, Image.SCALE_SMOOTH);
                lblImage = new JLabel(new ImageIcon(scaledImage));
            } else {
                lblImage = new JLabel("No Image", SwingConstants.CENTER);
                lblImage.setBackground(Color.LIGHT_GRAY);
                lblImage.setOpaque(true);
            }
        } else {
            lblImage = new JLabel("No Image", SwingConstants.CENTER);
            lblImage.setBackground(Color.LIGHT_GRAY);
            lblImage.setOpaque(true);
        }
        lblImage.setBounds(0, 0, 270, 200);
        panel.add(lblImage);

        // Tên món ăn
        JLabel lblTen = new JLabel(tenMon);
        lblTen.setHorizontalAlignment(SwingConstants.CENTER);
        lblTen.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTen.setBounds(0, 200, 270, 24);
        panel.add(lblTen);

        // Thông tin bổ sung
        JLabel lblMaMon = new JLabel("Mã món ăn: " + maMon);
        lblMaMon.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaMon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMaMon.setBounds(10, 224, 250, 20);
        panel.add(lblMaMon);

        JLabel lblTenLoaiMon = new JLabel("Tên loại món ăn: " + tenMon);
        lblTenLoaiMon.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenLoaiMon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTenLoaiMon.setBounds(10, 244, 250, 20);
        panel.add(lblTenLoaiMon);

        JLabel lblGiaMon = new JLabel("Giá: " + giaMon);
        lblGiaMon.setHorizontalAlignment(SwingConstants.LEFT);
        lblGiaMon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGiaMon.setBounds(10, 264, 250, 20);
        panel.add(lblGiaMon);

        JLabel lblLoaiMon = new JLabel("Loại món ăn: " + loaiMon);
        lblLoaiMon.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoaiMon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLoaiMon.setBounds(10, 284, 250, 20);
        panel.add(lblLoaiMon);
        
        JLabel lblGhiChu = new JLabel("Ghi chú: " + (ghichu != null ? ghichu : ""));
        lblGhiChu.setHorizontalAlignment(SwingConstants.LEFT);
        lblGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGhiChu.setBounds(10, 304, 250, 40);
        panel.add(lblGhiChu);
        
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Cập nhật thông tin vào các trường nhập liệu
                textField.setText(maMon); // Mã món ăn
                textField_1.setText(tenMon); // Tên món ăn
                textField_2.setText(giaMon); // Giá món ăn
                textArea.setText(ghichu != null ? ghichu : ""); // Ghi chú
                comboBox.setSelectedItem(loaiMon); // Loại món ăn
                textFieldHinhAnh.setText(imagePath != null ? imagePath : ""); // Đường dẫn hình ảnh

                // Cập nhật hình ảnh preview
                if (imagePath != null) {
                    URL imgURL = getClass().getResource(imagePath);
                    if (imgURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imgURL);
                        Image scaledImage = imageIcon.getImage().getScaledInstance(283, 200, Image.SCALE_SMOOTH);
                        lblHinhAnhPreview.setIcon(new ImageIcon(scaledImage));
                        lblHinhAnhPreview.setText("");
                    } else {
                        lblHinhAnhPreview.setIcon(null);
                        lblHinhAnhPreview.setText("Không tải được hình ảnh");
                    }
                } else {
                    lblHinhAnhPreview.setIcon(null);
                    lblHinhAnhPreview.setText("Hình ảnh sẽ hiển thị ở đây");
                }
            }
        });

        panel.setBounds(0, 0, 270, 340);
        return panel;
    }
}