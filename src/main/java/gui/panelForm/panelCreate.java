package gui.panelForm;

import java.awt.Font;
import javax.swing.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import dao.DAO_KhachHang;
import entity.KhachHang;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class panelCreate extends JPanel {
    private JLabel jLabelid;
    private JLabel jLabelkh;
    private JLabel jLabelsdt;
    private JLabel jLabelgt;
    private JTextField txtid;
    private JTextField txtkh;
    private JTextField txtsdt;
    private JComboBox<String> combogt;
    private DAO_KhachHang KH_DAO;
    public panelCreate() {
        initComponents();
    }

    private void initComponents() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatIntelliJLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        KH_DAO = new DAO_KhachHang();
        String maKH = KH_DAO.getNextMaKH();
        jLabelid = new JLabel("Mã khách hàng");
        txtid = new JTextField();
        txtid.setText(maKH);
        jLabelkh = new JLabel("Tên khách hàng");
        txtkh = new JTextField();

        jLabelsdt = new JLabel("Số điện thoại");
        txtsdt = new JTextField();

        jLabelgt = new JLabel("Giới tính");
        combogt = new JComboBox<>(new String[]{"Nam", "Nữ"});

        // Xóa căn phải, để mặc định căn trái
        jLabelid.setHorizontalAlignment(SwingConstants.LEADING);
        jLabelkh.setHorizontalAlignment(SwingConstants.LEADING);
        jLabelsdt.setHorizontalAlignment(SwingConstants.LEADING);
        jLabelgt.setHorizontalAlignment(SwingConstants.LEADING);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    // Đặt chiều rộng cố định cho các label để thẳng hàng
                    .addComponent(jLabelid, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelkh, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelsdt, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelgt, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtid, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtkh)
                    .addComponent(txtsdt)
                    .addComponent(combogt, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelid)
                    .addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelkh)
                    .addComponent(txtkh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelsdt)
                    .addComponent(txtsdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelgt)
                    .addComponent(combogt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE)));
    }

    public KhachHang getData() {
        String maKH = txtid.getText().trim();
        String tenKH = txtkh.getText().trim();
        String sdt = txtsdt.getText().trim();
        String gioiTinh = (String) combogt.getSelectedItem();

        if (maKH.isEmpty() || tenKH.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if(!maKH.matches("^KH\\d{3}")) {
        	JOptionPane.showMessageDialog(this, "Mẫu là KH\\d{3}", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!sdt.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10-11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new KhachHang(maKH, tenKH, sdt, gioiTinh);
    }


    public void setData(KhachHang kh) {
        txtid.setText(kh.getMaKH());
        txtkh.setText(kh.getTenKH());
        txtsdt.setText(kh.getSdt());
        combogt.setSelectedItem(kh.getGioiTinh());
        txtid.setEditable(false); 
    }
}