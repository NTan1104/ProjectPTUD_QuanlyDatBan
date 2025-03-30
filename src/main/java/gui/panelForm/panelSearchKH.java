package gui.panelForm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import gui.tabbed.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;


public class panelSearchKH extends JPanel {
    private JTable table;
    private JScrollPane scroll;
    private JLabel lbTitle;
    private JSeparator jSeparator1;
//    private sample.ButtonAction cmdDelete;
    private javax.swing.JTextField txtSearch;

    public panelSearchKH() {
        initComponents();
        init();
    }

    private void initComponents() {
    	setSize(1535, 850);
        lbTitle = new JLabel("TÌM KIẾM KHÁCH HÀNG");
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        jSeparator1 = new JSeparator();
        
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        
    
 
        
        scroll = new JScrollPane();
        table = new JTable();

        table.setModel(new DefaultTableModel(
        	    new Object[][] {
        	        {"KH001", "Nguyễn Văn A", "0901234567", "Nam"},
        	        {"KH002", "Trần Thị B", "0907654321", "Nữ"},
        	        {"KH003", "Phạm Minh C", "0912345678", "Nam"},
        	        {"KH004", "Lê Mai D", "0923456789", "Nữ"},
        	    },
        	    new String[] {
        	        "Mã KH", "Tên KH", "SDT", "Giới tính"
        	    }
        	) {
        	    // Override getColumnClass to return the correct class for each column
        	    @Override
        	    public Class<?> getColumnClass(int columnIndex) {
        	        Class[] types = new Class[] {
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
        
        lbTitle.setText("Tìm Kiếm");
        
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
        }
        
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
        
        scroll.setBorder(BorderFactory.createEmptyBorder());
        
        GroupLayout panelLayout = new GroupLayout(this);
        this.setLayout(panelLayout);

        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
                .addComponent(jSeparator1)
                .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbTitle)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(lbTitle)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGap(10, 10, 10))
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
        
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("java/img/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");

    }
}
