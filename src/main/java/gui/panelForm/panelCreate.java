package gui.panelForm;

import javax.swing.JPanel;

public class panelCreate extends JPanel {
    private javax.swing.JComboBox<Object> combogt;
    private javax.swing.JLabel jLabelid;
    private javax.swing.JLabel jLabelkh;
    private javax.swing.JLabel jLabelsdt;
    private javax.swing.JLabel jLabelgt;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtkh;
    private javax.swing.JTextField txtsdt;

    public panelCreate() {
        initComponents();
    }

    private void initComponents() {
        jLabelid = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabelkh = new javax.swing.JLabel();
        txtkh = new javax.swing.JTextField();
        jLabelsdt = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabelgt = new javax.swing.JLabel();
        combogt = new javax.swing.JComboBox<>();

        jLabelid.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelid.setText("Mã KH");

        jLabelkh.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelkh.setText("Tên KH");

        jLabelsdt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelsdt.setText("SDT");

        jLabelgt.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelgt.setText("Giới tính");
        
        combogt.addItem("Nam");
        combogt.addItem("Nữ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelkh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelgt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtid, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtkh)
                    .addComponent(txtsdt)
                    .addComponent(combogt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelid)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelkh)
                    .addComponent(txtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelsdt)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelgt)
                    .addComponent(combogt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }
}
