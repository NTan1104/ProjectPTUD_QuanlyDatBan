package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.toedter.calendar.JDateChooser;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import dao.DAO_KhuyenMai;
import entity.KhuyenMai;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class panelKhuyenMai extends JPanel {
    private JTextField txtSearch;
    private JTable table;
    private DAO_KhuyenMai DAOKM;
    private DefaultTableModel model;
    private JComboBox<String> cbStatus;

    public panelKhuyenMai() {
        // Khởi tạo DAO
        DAOKM = new DAO_KhuyenMai();

        setBackground(SystemColor.controlHighlight);
        setLayout(null);
        setSize(1535, 850);

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setBounds(10, 25, 70, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(90, 25, 200, 25);
        add(txtSearch);

        cbStatus = new JComboBox<>(new String[]{"Tất cả", "Đang khuyến mãi", "Đã hết hạn"});
        cbStatus.setBounds(421, 25, 174, 25);
        cbStatus.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filterPromotions();
                }
            }
        });
        add(cbStatus);

        JButton btnCreate = new JButton("Tạo khuyến mãi");
        btnCreate.setBounds(1350, 25, 120, 25);
        btnCreate.setBackground(Color.BLUE);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreatePromotionForm();
            }
        });
        add(btnCreate);

        String[] columnNames = {"Mã KM", "Điều kiện", "Ngày bắt đầu", "Ngày kết thúc", "Phần trăm giảm giá", "Tác vụ"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Chỉ cột "Tác vụ" có thể chỉnh sửa
            }
        };
        table = new JTable(model);
        table.setRowHeight(36);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumnModel().getColumn(5).setPreferredWidth(60);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        JScrollPane scrollPane = new RoundedScrollPane(table,30);
        scrollPane.setBounds(10, 60, 1502, 760);
        add(scrollPane);

        JButton btnsearchButton = new JButton("Tìm");
        btnsearchButton.setBounds(295, 25, 100, 25);
        btnsearchButton.setBackground(Color.BLUE);
        btnsearchButton.setForeground(Color.WHITE);
        btnsearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterPromotions();
            }
        });
        add(btnsearchButton);

        // Load dữ liệu từ cơ sở dữ liệu
        loadPromotions();
    }

    // Load danh sách khuyến mãi từ cơ sở dữ liệu
    private void loadPromotions() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        List<KhuyenMai> promotions = DAOKM.getAllPromotions();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (KhuyenMai km : promotions) {
            String ngayBatDau = sdf.format(Date.from(km.getNgayBatDau().atZone(ZoneId.systemDefault()).toInstant()));
            String ngayKetThuc = sdf.format(Date.from(km.getngayKetThuc().atZone(ZoneId.systemDefault()).toInstant()));
            model.addRow(new Object[]{
                km.getMaKM(),
                km.getDieuKien(),
                ngayBatDau,
                ngayKetThuc,
                km.getPhanTramGiamGia() + "%",
                ""
            });
        }
    }

    private void filterPromotions() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        String status = (String) cbStatus.getSelectedItem();
        model.setRowCount(0); // Xóa dữ liệu cũ
        List<KhuyenMai> promotions = DAOKM.getAllPromotions();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        for (KhuyenMai km : promotions) {
            boolean matchesStatus = false;
            if (status.equals("Tất cả")) {
                matchesStatus = true;
            } else if (status.equals("Đang khuyến mãi") && !km.getngayKetThuc().isBefore(now)) {
                matchesStatus = true;
            } else if (status.equals("Đã hết hạn") && km.getngayKetThuc().isBefore(now)) {
                matchesStatus = true;
            }

            boolean matchesKeyword = keyword.isEmpty() ||
                km.getMaKM().toLowerCase().contains(keyword) ||
                km.getDieuKien().toLowerCase().contains(keyword);

            if (matchesStatus && matchesKeyword) {
                String ngayBatDau = sdf.format(Date.from(km.getNgayBatDau().atZone(ZoneId.systemDefault()).toInstant()));
                String ngayKetThuc = sdf.format(Date.from(km.getngayKetThuc().atZone(ZoneId.systemDefault()).toInstant()));
                model.addRow(new Object[]{
                    km.getMaKM(),
                    km.getDieuKien(),
                    ngayBatDau,
                    ngayKetThuc,
                    km.getPhanTramGiamGia() + "%",
                    ""
                });
            }
        }
    }

    // Hiển thị form tạo khuyến mãi
    private void showCreatePromotionForm() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Tạo khuyến mãi mới", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtMaKM = new JTextField(20);
        JTextField txtDieuKien = new JTextField(20);
        JDateChooser dateChooserNgayBatDau = new JDateChooser();
        dateChooserNgayBatDau.setDateFormatString("dd/MM/yyyy");
        JDateChooser dateChooserNgayKetThuc = new JDateChooser();
        dateChooserNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        JTextField txtPhanTramGiam = new JTextField(20);

        // Giới hạn nhập liệu cho txtPhanTramGiam

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Mã KM:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtMaKM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Điều kiện:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtDieuKien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Ngày bắt đầu:"), gbc);
        gbc.gridx = 1;
        dialog.add(dateChooserNgayBatDau, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("Ngày kết thúc:"), gbc);
        gbc.gridx = 1;
        dialog.add(dateChooserNgayKetThuc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(new JLabel("Phần trăm giảm:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtPhanTramGiam, gbc);

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(Color.RED);
        btnCancel.setForeground(Color.WHITE);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        dialog.add(buttonPanel, gbc);

        btnCancel.addActionListener(e -> dialog.dispose());


        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maKM = txtMaKM.getText().trim();
                String dieuKien = txtDieuKien.getText().trim();
                String phanTramGiam = txtPhanTramGiam.getText().trim();

                System.out.println("Create - Input phanTramGiam: [" + phanTramGiam + "]");

                if (maKM.isEmpty() || dieuKien.isEmpty() || phanTramGiam.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DAOKM.getPromotionById(maKM) != null) {
                    JOptionPane.showMessageDialog(dialog, "Mã khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double phanTram;
                try {
                    phanTramGiam = phanTramGiam.replace(",", ".");
                    phanTram = Double.parseDouble(phanTramGiam);
                    if (phanTram < 0 || phanTram > 100) {
                        JOptionPane.showMessageDialog(dialog, "Phần trăm giảm phải từ 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    System.out.println("Create - Parsed phanTram: " + phanTram);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Phần trăm giảm phải là số hợp lệ !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                try {
                    if (dateChooserNgayBatDau.getDate() != null) {
                        ngayBatDau = dateChooserNgayBatDau.getDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
                    }
                    if (dateChooserNgayKetThuc.getDate() != null) {
                        ngayKetThuc = dateChooserNgayKetThuc.getDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi định dạng ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ngayBatDau == null || ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày bắt đầu và kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ngayKetThuc.isBefore(ngayBatDau)) {
                    JOptionPane.showMessageDialog(dialog, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                KhuyenMai km = new KhuyenMai(maKM, dieuKien, ngayKetThuc, ngayBatDau, phanTram);
                if (DAOKM.addPromotion(km)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadPromotions();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    // Hiển thị form chỉnh sửa khuyến mãi
    private void showEditPromotionForm(int row) {
        String maKM = (String) model.getValueAt(row, 0);
        KhuyenMai km = DAOKM.getPromotionById(maKM);
        if (km == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chỉnh sửa khuyến mãi", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtMaKM = new JTextField(km.getMaKM(), 20);
        txtMaKM.setEditable(false);
        JTextField txtDieuKien = new JTextField(km.getDieuKien(), 20);
        JDateChooser dateChooserNgayBatDau = new JDateChooser();
        dateChooserNgayBatDau.setDateFormatString("dd/MM/yyyy");
        if (km.getNgayBatDau() != null) {
            dateChooserNgayBatDau.setDate(Date.from(km.getNgayBatDau().atZone(ZoneId.systemDefault()).toInstant()));
        }
        JDateChooser dateChooserNgayKetThuc = new JDateChooser();
        dateChooserNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        if (km.getngayKetThuc() != null) {
            dateChooserNgayKetThuc.setDate(Date.from(km.getngayKetThuc().atZone(ZoneId.systemDefault()).toInstant()));
        }
        JTextField txtPhanTramGiam = new JTextField(String.valueOf(km.getPhanTramGiamGia()), 20);

        // Giới hạn nhập liệu cho txtPhanTramGiam

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Mã KM:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtMaKM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Điều kiện:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtDieuKien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Ngày bắt đầu:"), gbc);
        gbc.gridx = 1;
        dialog.add(dateChooserNgayBatDau, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("Ngày kết thúc:"), gbc);
        gbc.gridx = 1;
        dialog.add(dateChooserNgayKetThuc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(new JLabel("Phần trăm giảm:"), gbc);
        gbc.gridx = 1;
        dialog.add(txtPhanTramGiam, gbc);

        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(Color.RED);
        btnCancel.setForeground(Color.WHITE);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        dialog.add(buttonPanel, gbc);

        btnCancel.addActionListener(e -> dialog.dispose());

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dieuKien = txtDieuKien.getText().trim();
                String phanTramGiam = txtPhanTramGiam.getText().trim();

                System.out.println("Edit - Input phanTramGiam: [" + phanTramGiam + "]");

                if (dieuKien.isEmpty() || phanTramGiam.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double phanTram;
                try {
                    phanTramGiam = phanTramGiam.replace(",", ".");
                    phanTram = Double.parseDouble(phanTramGiam);
                    if (phanTram < 0 || phanTram > 100) {
                        JOptionPane.showMessageDialog(dialog, "Phần trăm giảm phải từ 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    System.out.println("Edit - Parsed phanTram: " + phanTram);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Phần trăm giảm phải là số hợp lệ !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDateTime ngayBatDau = null;
                LocalDateTime ngayKetThuc = null;
                try {
                    if (dateChooserNgayBatDau.getDate() != null) {
                        ngayBatDau = dateChooserNgayBatDau.getDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
                    }
                    if (dateChooserNgayKetThuc.getDate() != null) {
                        ngayKetThuc = dateChooserNgayKetThuc.getDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Lỗi định dạng ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ngayBatDau == null || ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày bắt đầu và kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ngayKetThuc.isBefore(ngayBatDau)) {
                    JOptionPane.showMessageDialog(dialog, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                KhuyenMai updatedKm = new KhuyenMai(maKM, dieuKien, ngayKetThuc, ngayBatDau, phanTram);
                if (DAOKM.updatePromotion(updatedKm)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadPromotions();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    // Renderer cho cột Tác vụ
    class ButtonRenderer extends DefaultTableCellRenderer {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(Box.createHorizontalGlue());

            btnEdit = new JButton();
            ImageIcon editIcon = new ImageIcon(getClass().getResource("/img/edit-246-16.png"));
            if (editIcon.getImage() == null) {
                btnEdit.setText("Edit");
            } else {
                Image scaledEditImage = editIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                btnEdit.setIcon(new ImageIcon(scaledEditImage));
            }
            btnEdit.setPreferredSize(new Dimension(25, 25));
            btnEdit.setMinimumSize(new Dimension(25, 25));
            btnEdit.setMaximumSize(new Dimension(25, 25));
            btnEdit.setBorderPainted(false);
            btnEdit.setContentAreaFilled(false);

            panel.add(btnEdit);
            panel.add(Box.createHorizontalStrut(5));

            btnDelete = new JButton();
            ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/img/delete.png"));
            if (deleteIcon.getImage() == null) {
                btnDelete.setText("Delete");
            } else {
                Image scaledDeleteImage = deleteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                btnDelete.setIcon(new ImageIcon(scaledDeleteImage));
            }
            btnDelete.setPreferredSize(new Dimension(25, 25));
            btnDelete.setMinimumSize(new Dimension(25, 25));
            btnDelete.setMaximumSize(new Dimension(25, 25));
            btnDelete.setBorderPainted(false);
            btnDelete.setContentAreaFilled(false);

            panel.add(btnDelete);
            panel.add(Box.createHorizontalGlue());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }
            return panel;
        }
    }

    // Editor cho cột Tác vụ
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;
        private String maKM;

        public ButtonEditor(JCheckBox checkBox) {
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(Box.createHorizontalGlue());

            btnEdit = new JButton();
            ImageIcon editIcon = new ImageIcon(getClass().getResource("/img/edit-246-16.png"));
            if (editIcon.getImage() == null) {
                btnEdit.setText("Edit");
            } else {
                Image scaledEditImage = editIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                btnEdit.setIcon(new ImageIcon(scaledEditImage));
            }
            btnEdit.setPreferredSize(new Dimension(25, 25));
            btnEdit.setMinimumSize(new Dimension(25, 25));
            btnEdit.setMaximumSize(new Dimension(25, 25));
            btnEdit.setBorderPainted(false);
            btnEdit.setContentAreaFilled(false);
            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        showEditPromotionForm(row);
                    }
                }
            });

            panel.add(btnEdit);
            panel.add(Box.createHorizontalStrut(5));

            btnDelete = new JButton();
            ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/img/delete.png"));
            if (deleteIcon.getImage() == null) {
                btnDelete.setText("Delete");
            } else {
                Image scaledDeleteImage = deleteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                btnDelete.setIcon(new ImageIcon(scaledDeleteImage));
            }
            btnDelete.setPreferredSize(new Dimension(25, 25));
            btnDelete.setMinimumSize(new Dimension(25, 25));
            btnDelete.setMaximumSize(new Dimension(25, 25));
            btnDelete.setBorderPainted(false);
            btnDelete.setContentAreaFilled(false);
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        maKM = (String) model.getValueAt(row, 0);
                        int confirm = JOptionPane.showConfirmDialog(
                            panelKhuyenMai.this,
                            "Bạn có chắc muốn xóa khuyến mãi " + maKM + "?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.YES_OPTION) {
                            if (DAOKM.deletePromotion(maKM)) {
                                JOptionPane.showMessageDialog(
                                    panelKhuyenMai.this,
                                    "Xóa khuyến mãi thành công!",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                                loadPromotions(); // Tải lại danh sách
                            } else {
                                JOptionPane.showMessageDialog(
                                    panelKhuyenMai.this,
                                    "Xóa khuyến mãi thất bại!",
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }
                }
            });

            panel.add(btnDelete);
            panel.add(Box.createHorizontalGlue());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            maKM = (String) model.getValueAt(row, 0);
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return maKM;
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

}