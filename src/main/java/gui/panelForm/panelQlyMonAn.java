package gui.panelForm;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    private JTextField txtMaMonAn;
    private JTextField txtTenMonAn;
    private JTextField txtGiaMonAn;
    private JTextField txtTimKiem;
    private JComboBox<String> cbLoaiMonAn;
    private JTextField txtDuongDanHinhAnh;
    private JComboBox<String> cbLocLoaiMonAn;
    private JTextArea txtGhiChu;
    private JLabel lblXemTruocHinhAnh;
    private JPanel pnlDanhSachMonAn;
    private DAO_MonAn daoMonAn;
    private int columns;
    private int chieuRongPnlDanhSach;

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
        UIManager.put("Panel.background", new Color(247, 248, 252));
        UIManager.put("Button.background", new Color(52, 102, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.disabledBackground", new Color(209, 213, 219));
        UIManager.put("Label.foreground", new Color(17, 24, 39));
        UIManager.put("Component.borderColor", new Color(229, 231, 235));

        // Khởi tạo các biến instance
        daoMonAn = new DAO_MonAn();
        columns = 4;
        chieuRongPnlDanhSach = 1200;

        // Danh sách món ăn
        pnlDanhSachMonAn = new JPanel();
        pnlDanhSachMonAn.setBounds(10, 47, 1200, 793);
        add(pnlDanhSachMonAn);
        pnlDanhSachMonAn.setLayout(null);

        List<MonAn> monAnList = daoMonAn.getAllMonAn();
        int panelHeight = (int) Math.ceil((double) monAnList.size() / columns) * (340 + 33);
        pnlDanhSachMonAn.setBounds(0, 0, chieuRongPnlDanhSach, panelHeight);
        pnlDanhSachMonAn.setPreferredSize(new Dimension(chieuRongPnlDanhSach, panelHeight));

        for (int i = 0; i < monAnList.size(); i++) {
            MonAn monAn = monAnList.get(i);
            int row = i / columns;
            int col = i % columns;
            int x = 10 + col * (270 + 39);
            int y = 10 + row * (340 + 33);
            JPanel dishPanel = createMonAnPanel(
                monAn.getTenMonAn(),
                monAn.getDuongDanHinhAnh(),
                monAn.getMaMonAn(),
                String.valueOf(monAn.getGia()),
                monAn.getLoaiMonAn(),
                monAn.getGhiChu()
            );
            dishPanel.setBounds(x, y, 270, 340);
            pnlDanhSachMonAn.add(dishPanel);
        }

        // Bọc pnlDanhSachMonAn trong JScrollPane
        JScrollPane scrollPane = new JScrollPane(pnlDanhSachMonAn);
        scrollPane.setBounds(10, 47, 1200, 793);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        // Panel nhập thông tin
        JPanel pnlNhapThongTin = new JPanel();
        pnlNhapThongTin.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
            "Thông tin món ăn", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pnlNhapThongTin.setBounds(1232, 10, 303, 793);
        add(pnlNhapThongTin);
        pnlNhapThongTin.setLayout(null);

        JLabel lblMaMonAn = new JLabel("Mã món ăn");
        lblMaMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblMaMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaMonAn.setBounds(10, 40, 81, 35);
        pnlNhapThongTin.add(lblMaMonAn);

        txtMaMonAn = new JTextField();
        txtMaMonAn.setBounds(111, 40, 182, 30);
        pnlNhapThongTin.add(txtMaMonAn);
        txtMaMonAn.setColumns(10);

        JLabel lblTenMonAn = new JLabel("Tên món ăn");
        lblTenMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenMonAn.setBounds(10, 96, 91, 35);
        pnlNhapThongTin.add(lblTenMonAn);

        txtTenMonAn = new JTextField();
        txtTenMonAn.setColumns(10);
        txtTenMonAn.setBounds(111, 102, 182, 30);
        pnlNhapThongTin.add(txtTenMonAn);

        JLabel lblGiaMonAn = new JLabel("Giá món ăn");
        lblGiaMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblGiaMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGiaMonAn.setBounds(10, 155, 91, 35);
        pnlNhapThongTin.add(lblGiaMonAn);

        txtGiaMonAn = new JTextField();
        txtGiaMonAn.setColumns(10);
        txtGiaMonAn.setBounds(111, 163, 182, 30);
        pnlNhapThongTin.add(txtGiaMonAn);

        JLabel lblLoaiMonAn = new JLabel("Loại món ăn");
        lblLoaiMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoaiMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblLoaiMonAn.setBounds(10, 220, 91, 35);
        pnlNhapThongTin.add(lblLoaiMonAn);

        cbLoaiMonAn = new JComboBox<>();
        cbLoaiMonAn.setBounds(111, 220, 182, 30);
        cbLoaiMonAn.addItem("");
        List<String> loaiMonAnList = daoMonAn.getAllLoaiMonAn();
        for (String loai : loaiMonAnList) {
            cbLoaiMonAn.addItem(loai);
        }
        cbLoaiMonAn.setEditable(true); // Cho phép nhập loại mới
        pnlNhapThongTin.add(cbLoaiMonAn);

        JLabel lblGhiChu = new JLabel("Ghi chú");
        lblGhiChu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGhiChu.setBounds(10, 276, 108, 35);
        pnlNhapThongTin.add(lblGhiChu);

        txtGhiChu = new JTextArea();
        txtGhiChu.setBounds(10, 305, 283, 150);
        pnlNhapThongTin.add(txtGhiChu);

        JLabel lblHinhAnh = new JLabel("Hình ảnh");
        lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblHinhAnh.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblHinhAnh.setBounds(10, 465, 91, 35);
        pnlNhapThongTin.add(lblHinhAnh);

        txtDuongDanHinhAnh = new JTextField();
        txtDuongDanHinhAnh.setBounds(111, 465, 182, 30);
        txtDuongDanHinhAnh.setEditable(false);
        pnlNhapThongTin.add(txtDuongDanHinhAnh);
        txtDuongDanHinhAnh.setColumns(10);

        JButton btnChonHinhAnh = new JButton("Chọn hình");
        btnChonHinhAnh.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnChonHinhAnh.setBounds(111, 505, 100, 30);
        pnlNhapThongTin.add(btnChonHinhAnh);

        lblXemTruocHinhAnh = new JLabel("Hình ảnh sẽ hiển thị ở đây", SwingConstants.CENTER);
        lblXemTruocHinhAnh.setBounds(10, 545, 283, 200);
        lblXemTruocHinhAnh.setBackground(Color.LIGHT_GRAY);
        lblXemTruocHinhAnh.setOpaque(true);
        pnlNhapThongTin.add(lblXemTruocHinhAnh);

        // ActionListener cho nút chọn hình
        btnChonHinhAnh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            File defaultDirectory = new File("src/main/resources/img");
            if (defaultDirectory.exists()) {
                fileChooser.setCurrentDirectory(defaultDirectory);
            } else {
                try {
                    Files.createDirectories(defaultDirectory.toPath());
                    fileChooser.setCurrentDirectory(defaultDirectory);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi tạo thư mục: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "gif");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    Path sourcePath = selectedFile.toPath();
                    Path targetDir = Paths.get("src/main/resources/img");
                    if (!Files.exists(targetDir)) {
                        Files.createDirectories(targetDir);
                    }
                    String targetFileName = selectedFile.getName();
                    Path targetPath = Paths.get("src/main/resources/img/" + targetFileName);
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    String relativePath = "/img/" + targetFileName;
                    txtDuongDanHinhAnh.setText(relativePath);

                    URL imgURL = getClass().getResource(relativePath);
                    if (imgURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imgURL);
                        Image scaledImage = imageIcon.getImage().getScaledInstance(283, 200, Image.SCALE_SMOOTH);
                        lblXemTruocHinhAnh.setIcon(new ImageIcon(scaledImage));
                        lblXemTruocHinhAnh.setText("");
                    } else {
                        lblXemTruocHinhAnh.setIcon(null);
                        lblXemTruocHinhAnh.setText("Không tải được hình ảnh");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    lblXemTruocHinhAnh.setIcon(null);
                    lblXemTruocHinhAnh.setText("Lỗi tải hình ảnh");
                    JOptionPane.showMessageDialog(this, "Lỗi tải hình ảnh: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // JComboBox cho bộ lọc
        cbLocLoaiMonAn = new JComboBox<>();
        cbLocLoaiMonAn.setBounds(10, 10, 150, 35);
        cbLocLoaiMonAn.addItem("Tất cả");
        for (String loai : loaiMonAnList) {
            cbLocLoaiMonAn.addItem(loai);
        }
        add(cbLocLoaiMonAn);

        // Thêm các nút "Thêm", "Xóa", "Cập nhật"
        JButton btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnThem.setBounds(800, 10, 108, 35);
        add(btnThem);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setBackground(Color.RED);
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnXoa.setBounds(950, 10, 108, 35);
        add(btnXoa);

        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBackground(Color.GREEN);
        btnCapNhat.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCapNhat.setBounds(1100, 10, 108, 35);
        add(btnCapNhat);

        // TextField tìm kiếm với placeholder và nút tìm kiếm bên trong


        // ActionListener cho cbLocLoaiMonAn
        cbLocLoaiMonAn.addActionListener(e -> {
            String selectedType = (String) cbLocLoaiMonAn.getSelectedItem();
            if (selectedType == null) {
                return;
            }
            try {
                List<MonAn> result = daoMonAn.filterMonAnByLoai(selectedType.equals("Tất cả") ? "All" : selectedType);
                pnlDanhSachMonAn.removeAll();
                int i = 0;
                for (MonAn monAn : result) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        monAn.getTenMonAn(),
                        monAn.getDuongDanHinhAnh(),
                        monAn.getMaMonAn(),
                        String.valueOf(monAn.getGia()),
                        monAn.getLoaiMonAn(),
                        monAn.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    pnlDanhSachMonAn.add(dishPanel);
                    i++;
                }
                int panelHeightFilter = (int) Math.ceil((double) i / columns) * (340 + 33);
                pnlDanhSachMonAn.setPreferredSize(new Dimension(chieuRongPnlDanhSach, panelHeightFilter));
                pnlDanhSachMonAn.setBounds(0, 0, chieuRongPnlDanhSach, panelHeightFilter);
                scrollPane.revalidate();
                scrollPane.repaint();
                pnlDanhSachMonAn.revalidate();
                pnlDanhSachMonAn.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi lọc món ăn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        

        // ActionListener cho btnThem
        btnThem.addActionListener(e -> {
            try {
                String maMonAn = txtMaMonAn.getText().trim();
                String tenMonAn = txtTenMonAn.getText().trim();
                String giaStr = txtGiaMonAn.getText().trim();
                String loaiMonAn = cbLoaiMonAn.getSelectedItem() != null ? cbLoaiMonAn.getSelectedItem().toString().trim() : "";
                String ghiChu = txtGhiChu.getText().trim();
                String duongDanHinhAnh = txtDuongDanHinhAnh.getText().trim();

                // Kiểm tra dữ liệu đầu vào
                if (maMonAn.isEmpty() || tenMonAn.isEmpty() || giaStr.isEmpty() || loaiMonAn.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin (mã, tên, giá, loại món ăn)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!maMonAn.matches("^MA\\d{3}$")) {
                    JOptionPane.showMessageDialog(this, "Mã món ăn phải theo định dạng MAxxx (x là số)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaMonAn.requestFocusInWindow();
                    return;
                }

                if (daoMonAn.checkMaMonAnExists(maMonAn)) {
                    JOptionPane.showMessageDialog(this, "Mã món ăn đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaMonAn.requestFocusInWindow();
                    return;
                }

                float gia;
                try {
                    gia = Float.parseFloat(giaStr);
                    if (gia <= 0) {
                        JOptionPane.showMessageDialog(this, "Giá món ăn phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaMonAn.requestFocusInWindow();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Giá món ăn phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaMonAn.requestFocusInWindow();
                    return;
                }

                MonAn monAn = new MonAn(maMonAn, tenMonAn, gia, ghiChu, loaiMonAn, duongDanHinhAnh);
                daoMonAn.addMonAn(monAn);

                // Cập nhật danh sách
                List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                pnlDanhSachMonAn.removeAll();
                int i = 0;
                for (MonAn ma : monAnListUpdated) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        ma.getTenMonAn(),
                        ma.getDuongDanHinhAnh(),
                        ma.getMaMonAn(),
                        String.valueOf(ma.getGia()),
                        ma.getLoaiMonAn(),
                        ma.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    pnlDanhSachMonAn.add(dishPanel);
                    i++;
                }
                int panelHeightAdd = (int) Math.ceil((double) i / columns) * (340 + 33);
                pnlDanhSachMonAn.setBounds(0, 0, chieuRongPnlDanhSach, panelHeightAdd);
                pnlDanhSachMonAn.setPreferredSize(new Dimension(chieuRongPnlDanhSach, panelHeightAdd));
                pnlDanhSachMonAn.revalidate();
                pnlDanhSachMonAn.repaint();

                // Cập nhật cbLocLoaiMonAn và cbLoaiMonAn
                cbLocLoaiMonAn.removeAllItems();
                cbLoaiMonAn.removeAllItems();
                cbLocLoaiMonAn.addItem("Tất cả");
                cbLoaiMonAn.addItem("");
                List<String> updatedLoaiMonAnList = daoMonAn.getAllLoaiMonAn();
                for (String loai : updatedLoaiMonAnList) {
                    cbLocLoaiMonAn.addItem(loai);
                    cbLoaiMonAn.addItem(loai);
                }

                JOptionPane.showMessageDialog(this, "Thêm món ăn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi thêm món ăn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener cho btnXoa
        btnXoa.addActionListener(e -> {
            try {
                String maMonAn = txtMaMonAn.getText().trim();
                if (maMonAn.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập mã món ăn để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaMonAn.requestFocusInWindow();
                    return;
                }

                if (!daoMonAn.checkMaMonAnExists(maMonAn)) {
                    JOptionPane.showMessageDialog(this, "Mã món ăn không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaMonAn.requestFocusInWindow();
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa món ăn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    daoMonAn.deleteMonAn(maMonAn);

                    List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                    pnlDanhSachMonAn.removeAll();
                    int i = 0;
                    for (MonAn ma : monAnListUpdated) {
                        int row = i / columns;
                        int col = i % columns;
                        int x = 10 + col * (270 + 39);
                        int y = 10 + row * (340 + 33);
                        JPanel dishPanel = createMonAnPanel(
                            ma.getTenMonAn(),
                            ma.getDuongDanHinhAnh(),
                            ma.getMaMonAn(),
                            String.valueOf(ma.getGia()),
                            ma.getLoaiMonAn(),
                            ma.getGhiChu()
                        );
                        dishPanel.setBounds(x, y, 270, 340);
                        pnlDanhSachMonAn.add(dishPanel);
                        i++;
                    }
                    int panelHeightDelete = (int) Math.ceil((double) i / columns) * (340 + 33);
                    pnlDanhSachMonAn.setBounds(0, 0, chieuRongPnlDanhSach, panelHeightDelete);
                    pnlDanhSachMonAn.setPreferredSize(new Dimension(chieuRongPnlDanhSach, panelHeightDelete));
                    pnlDanhSachMonAn.revalidate();
                    pnlDanhSachMonAn.repaint();

                    // Cập nhật cbLocLoaiMonAn và cbLoaiMonAn
                    cbLocLoaiMonAn.removeAllItems();
                    cbLoaiMonAn.removeAllItems();
                    cbLocLoaiMonAn.addItem("Tất cả");
                    cbLoaiMonAn.addItem("");
                    List<String> updatedLoaiMonAnList = daoMonAn.getAllLoaiMonAn();
                    for (String loai : updatedLoaiMonAnList) {
                        cbLocLoaiMonAn.addItem(loai);
                        cbLoaiMonAn.addItem(loai);
                    }

                    JOptionPane.showMessageDialog(this, "Xóa món ăn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi xóa món ăn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ActionListener cho btnCapNhat
        btnCapNhat.addActionListener(e -> {
            try {
                String maMonAn = txtMaMonAn.getText().trim();
                String tenMonAn = txtTenMonAn.getText().trim();
                String giaStr = txtGiaMonAn.getText().trim();
                String loaiMonAn = cbLoaiMonAn.getSelectedItem() != null ? cbLoaiMonAn.getSelectedItem().toString().trim() : "";
                String ghiChu = txtGhiChu.getText().trim();
                String duongDanHinhAnh = txtDuongDanHinhAnh.getText().trim();

                // Kiểm tra dữ liệu đầu vào
                if (maMonAn.isEmpty() || tenMonAn.isEmpty() || giaStr.isEmpty() || loaiMonAn.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin (mã, tên, giá, loại món ăn)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MonAn existingMonAn = daoMonAn.getMonAnByMa(maMonAn);
                if (existingMonAn == null) {
                    JOptionPane.showMessageDialog(this, "Mã món ăn không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaMonAn.requestFocusInWindow();
                    return;
                }

                float gia;
                try {
                    gia = Float.parseFloat(giaStr);
                    if (gia <= 0) {
                        JOptionPane.showMessageDialog(this, "Giá món ăn phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaMonAn.requestFocusInWindow();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Giá món ăn phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaMonAn.requestFocusInWindow();
                    return;
                }

                MonAn monAn = new MonAn(maMonAn, tenMonAn, gia, ghiChu, loaiMonAn, duongDanHinhAnh);
                daoMonAn.updateMonAn(monAn);

                List<MonAn> monAnListUpdated = daoMonAn.getAllMonAn();
                pnlDanhSachMonAn.removeAll();
                int i = 0;
                for (MonAn ma : monAnListUpdated) {
                    int row = i / columns;
                    int col = i % columns;
                    int x = 10 + col * (270 + 39);
                    int y = 10 + row * (340 + 33);
                    JPanel dishPanel = createMonAnPanel(
                        ma.getTenMonAn(),
                        ma.getDuongDanHinhAnh(),
                        ma.getMaMonAn(),
                        String.valueOf(ma.getGia()),
                        ma.getLoaiMonAn(),
                        ma.getGhiChu()
                    );
                    dishPanel.setBounds(x, y, 270, 340);
                    pnlDanhSachMonAn.add(dishPanel);
                    i++;
                }
                int panelHeightUpdate = (int) Math.ceil((double) i / columns) * (340 + 33);
                pnlDanhSachMonAn.setBounds(0, 0, chieuRongPnlDanhSach, panelHeightUpdate);
                pnlDanhSachMonAn.setPreferredSize(new Dimension(chieuRongPnlDanhSach, panelHeightUpdate));
                pnlDanhSachMonAn.revalidate();
                pnlDanhSachMonAn.repaint();

                // Cập nhật cbLocLoaiMonAn và cbLoaiMonAn
                cbLocLoaiMonAn.removeAllItems();
                cbLoaiMonAn.removeAllItems();
                cbLocLoaiMonAn.addItem("Tất cả");
                cbLoaiMonAn.addItem("");
                List<String> updatedLoaiMonAnList = daoMonAn.getAllLoaiMonAn();
                for (String loai : updatedLoaiMonAnList) {
                    cbLocLoaiMonAn.addItem(loai);
                    cbLoaiMonAn.addItem(loai);
                }

                JOptionPane.showMessageDialog(this, "Cập nhật món ăn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi cập nhật món ăn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearForm() {
        txtMaMonAn.setText("");
        txtTenMonAn.setText("");
        txtGiaMonAn.setText("");
        cbLoaiMonAn.setSelectedIndex(0);
        txtGhiChu.setText("");
        txtDuongDanHinhAnh.setText("");
        lblXemTruocHinhAnh.setIcon(null);
        lblXemTruocHinhAnh.setText("Hình ảnh sẽ hiển thị ở đây");
    }

    private JPanel createMonAnPanel(String tenMon, String imagePath, String maMon, String giaMon, String loaiMon, String ghichu) {
        JPanel pnlMonAn = new JPanel();
        pnlMonAn.setLayout(null);
        pnlMonAn.setBorder(null);

        JLabel lblHinhAnhMonAn;
        if (imagePath != null) {
            URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                ImageIcon originalIcon = new ImageIcon(imgURL);
                Image scaledImage = originalIcon.getImage().getScaledInstance(270, 200, Image.SCALE_SMOOTH);
                lblHinhAnhMonAn = new JLabel(new ImageIcon(scaledImage));
            } else {
                lblHinhAnhMonAn = new JLabel("No Image", SwingConstants.CENTER);
                lblHinhAnhMonAn.setBackground(Color.LIGHT_GRAY);
                lblHinhAnhMonAn.setOpaque(true);
            }
        } else {
            lblHinhAnhMonAn = new JLabel("No Image", SwingConstants.CENTER);
            lblHinhAnhMonAn.setBackground(Color.LIGHT_GRAY);
            lblHinhAnhMonAn.setOpaque(true);
        }
        lblHinhAnhMonAn.setBounds(0, 0, 270, 200);
        pnlMonAn.add(lblHinhAnhMonAn);

        JLabel lblTenMonAn = new JLabel(tenMon);
        lblTenMonAn.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenMonAn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTenMonAn.setBounds(0, 200, 270, 24);
        pnlMonAn.add(lblTenMonAn);

        JLabel lblMaMonAn = new JLabel("Mã món ăn: " + maMon);
        lblMaMonAn.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMaMonAn.setBounds(10, 224, 250, 20);
        pnlMonAn.add(lblMaMonAn);

        JLabel lblTenLoaiMonAn = new JLabel("Tên loại món ăn: " + tenMon);
        lblTenLoaiMonAn.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenLoaiMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTenLoaiMonAn.setBounds(10, 244, 250, 20);
        pnlMonAn.add(lblTenLoaiMonAn);

        JLabel lblGiaMonAn = new JLabel("Giá: " + giaMon);
        lblGiaMonAn.setHorizontalAlignment(SwingConstants.LEFT);
        lblGiaMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGiaMonAn.setBounds(10, 264, 250, 20);
        pnlMonAn.add(lblGiaMonAn);

        JLabel lblLoaiMonAn = new JLabel("Loại món ăn: " + loaiMon);
        lblLoaiMonAn.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoaiMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLoaiMonAn.setBounds(10, 284, 250, 20);
        pnlMonAn.add(lblLoaiMonAn);

        JLabel lblGhiChuMonAn = new JLabel("Ghi chú: " + (ghichu != null ? ghichu : ""));
        lblGhiChuMonAn.setHorizontalAlignment(SwingConstants.LEFT);
        lblGhiChuMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGhiChuMonAn.setBounds(10, 304, 250, 40);
        pnlMonAn.add(lblGhiChuMonAn);

        pnlMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaMonAn.setText(maMon);
                txtTenMonAn.setText(tenMon);
                txtGiaMonAn.setText(giaMon);
                txtGhiChu.setText(ghichu != null ? ghichu : "");
                cbLoaiMonAn.setSelectedItem(loaiMon);
                txtDuongDanHinhAnh.setText(imagePath != null ? imagePath : "");

                if (imagePath != null) {
                    URL imgURL = getClass().getResource(imagePath);
                    if (imgURL != null) {
                        ImageIcon imageIcon = new ImageIcon(imgURL);
                        Image scaledImage = imageIcon.getImage().getScaledInstance(283, 200, Image.SCALE_SMOOTH);
                        lblXemTruocHinhAnh.setIcon(new ImageIcon(scaledImage));
                        lblXemTruocHinhAnh.setText("");
                    } else {
                        lblXemTruocHinhAnh.setIcon(null);
                        lblXemTruocHinhAnh.setText("Không tải được hình ảnh");
                    }
                } else {
                    lblXemTruocHinhAnh.setIcon(null);
                    lblXemTruocHinhAnh.setText("Hình ảnh sẽ hiển thị ở đây");
                }
            }
        });

        pnlMonAn.setBounds(0, 0, 270, 340);
        return pnlMonAn;
    }
}