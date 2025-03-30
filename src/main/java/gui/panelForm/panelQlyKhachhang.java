package gui.panelForm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gui.tabbed.*;
import com.formdev.flatlaf.FlatClientProperties;

import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;


public class panelQlyKhachhang extends JPanel {
    private JTable table;
    private JScrollPane scroll;
    private JLabel lbTitle;
    private JSeparator jSeparator1;
    private gui.tabbed.ButtonAction cmdDelete;
    private gui.tabbed.ButtonAction cmdEdit;
    private gui.tabbed.ButtonAction cmdNew;

    public panelQlyKhachhang() {
        initComponents();
        init();
    }

    private void initComponents() {
    	setSize(1535, 850);
        lbTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        jSeparator1 = new JSeparator();
        
        cmdNew = new gui.tabbed.ButtonAction();
        cmdNew.setFont(new Font("Tahoma", Font.PLAIN, 10));
        cmdNew.setBackground(Color.LIGHT_GRAY);
        cmdNew.setText("Thêm");
        cmdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewActionPerformed(evt);
            }
        });
        
        
        
        cmdEdit = new gui.tabbed.ButtonAction();
        cmdEdit.setBackground(Color.LIGHT_GRAY);
        cmdEdit.setText("Sửa");
        
        
        cmdDelete = new gui.tabbed.ButtonAction();
        cmdDelete.setBackground(Color.LIGHT_GRAY);
        cmdDelete.setText("Xóa");
        
 
        
        scroll = new JScrollPane();
        table = new JTable();

        table.setModel(new DefaultTableModel(
        	    new Object[][] {
        	        {Boolean.FALSE, 1, "KH001", "Nguyễn Văn A", "0901234567", "Nam"},
        	        {Boolean.FALSE, 2, "KH002", "Trần Thị B", "0907654321", "Nữ"},
        	        {Boolean.FALSE, 3, "KH003", "Phạm Minh C", "0912345678", "Nam"},
        	        {Boolean.FALSE, 4, "KH004", "Lê Mai D", "0923456789", "Nữ"},
        	    },
        	    new String[] {
        	        "SELECT", "STT", "Mã KH", "Tên KH", "SDT", "Giới tính"
        	    }
        	) {
        	    // Override getColumnClass to return the correct class for each column
        	    @Override
        	    public Class<?> getColumnClass(int columnIndex) {
        	        Class[] types = new Class[] {
        	            Boolean.class, // "SELECT" column (checkbox)
        	            Object.class,  // "#" column
        	            Object.class,  // "ID" column
        	            Object.class,  // "TenKH" column
        	            Object.class,  // "SDT" column
        	            Object.class   // "GioiTinh" column
        	        };
        	        return types[columnIndex];
        	    }

        	    // Override isCellEditable to allow editing only for the "SELECT" column (checkbox)
        	    @Override
        	    public boolean isCellEditable(int rowIndex, int columnIndex) {
        	        // Only allow the "SELECT" column (columnIndex == 0) to be editable
        	        return columnIndex == 0;
        	    }
        	});

        
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
        }
        
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
        
        scroll.setBorder(BorderFactory.createEmptyBorder());
        
        GroupLayout panelLayout = new GroupLayout(this);
        panelLayout.setHorizontalGroup(
        	panelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelLayout.createSequentialGroup()
        			.addGap(20)
        			.addGroup(panelLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 1495, Short.MAX_VALUE)
        				.addGroup(Alignment.TRAILING, panelLayout.createSequentialGroup()
        					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 781, Short.MAX_VALUE)
        					.addComponent(cmdNew, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(cmdEdit, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(cmdDelete, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        					.addGap(375)))
        			.addGap(20))
        		.addGroup(panelLayout.createSequentialGroup()
        			.addGap(486)
        			.addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(812, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
        	panelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelLayout.createSequentialGroup()
        			.addComponent(lbTitle, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addGap(15)
        			.addGroup(panelLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(panelLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(cmdDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(cmdEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(cmdNew, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(10)
        			.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        			.addGap(10))
        );
        this.setLayout(panelLayout);

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

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
    }
    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        panelCreate create = new panelCreate();
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Lưu"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Thêm khách hàng", actions, (pc, i) -> {
            if (i == 1) {
                // save
                try {
                    pc.closePopup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed
    
}