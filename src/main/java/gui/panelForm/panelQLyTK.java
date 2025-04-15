package gui.panelForm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import gui.tabbed.*;
import com.formdev.flatlaf.FlatClientProperties;

import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.ScrollPane;
import java.text.SimpleDateFormat;
import java.util.Date;


public class panelQLyTK extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel tableModel;
	private RoundedScrollPane scrollPane;
	private JTable table_1;
  
  public panelQLyTK() {
	  setBackground(SystemColor.controlHighlight);
      setLayout(null);
      setSize(1535, 850);
      
      // Cài đặt font FlatLaf
      FlatLaf.registerCustomDefaultsSource("themes");
      FlatIntelliJLaf.setup();
      UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
      
      
      UIManager.put("Panel.background", new Color(247, 248, 252)); // Trắng xám nhạt nhẹ nhàng
      UIManager.put("Button.background", new Color(52, 102, 255)); // Xanh dương nhẹ cho nút
      UIManager.put("Button.foreground", Color.WHITE);
      UIManager.put("Button.disabledBackground", new Color(209, 213, 219)); // Xám nhạt khi vô hiệu
      UIManager.put("Label.foreground", new Color(17, 24, 39)); // Xám đen đậm cho chữ
      UIManager.put("Component.borderColor", new Color(229, 231, 235)); // Viền xám nhạt

      // Tiêu đề
      JLabel lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
      lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
      lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
      lblTitle.setBounds(0, 10, 1535, 27);
      add(lblTitle);
//      
//      JPanel panel = new JPanel();
//      panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//      panel.setBounds(50, 66, 1159, 96);
//      add(panel);
//      panel.setLayout(null);
      
      JPanel panelLeft = new JPanel();
      panelLeft.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
      panelLeft.setPreferredSize(new Dimension(400, 100));
      
      JLabel lblNewLabel = new JLabel("Mã nhân viên");
      lblNewLabel.setBounds(1, 1, 193, 41);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
      
      JLabel lblMtKhu = new JLabel("Tên tài khoản");
      lblMtKhu.setBounds(1, 55, 193, 41);
      lblMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
      lblMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 12));

      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      setupPlaceholder(textField_1, "Nhập mật khẩu");
      textField_1.setBounds(204, 110, 253, 41);
      panelLeft.setLayout(null);
      
      panelLeft.add(lblNewLabel);
      panelLeft.add(lblMtKhu);
      panelLeft.add(textField_1);
      
      JPanel panelRight = new JPanel();
      panelRight.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

      JButton btnNewButton = new JButton("Thêm");
      btnNewButton.setBounds(45, 50, 120, 50);
      JButton btnXa = new JButton("Xóa");
      btnXa.setBounds(240, 50, 120, 50);
      JButton btnSa = new JButton("Sửa");
      btnSa.setBounds(430, 50, 120, 50);
      JButton btnTimKiem = new JButton("Tìm kiếm");
      btnTimKiem.setBounds(620, 50, 120, 50);
      panelRight.setLayout(null);

      panelRight.add(btnNewButton);
      panelRight.add(btnXa);
      panelRight.add(btnSa);
      panelRight.add(btnTimKiem);

      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
      
      JLabel lblTrngThi = new JLabel("Mật khẩu");
      lblTrngThi.setHorizontalAlignment(SwingConstants.CENTER);
      lblTrngThi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
      lblTrngThi.setBounds(11, 109, 193, 41);
      panelLeft.add(lblTrngThi);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setBounds(204, 7, 253, 30);
      panelLeft.add(comboBox);
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"NV002"}));
            textField = new JTextField();
            textField.setBounds(204, 56, 253, 41);
            panelLeft.add(textField);
            //      textField.setBounds(230, 10, 158, 25);
                  setupPlaceholder(textField, "Nhập tên tài khoản");
                  //      panel.add(textField);
                        textField.setColumns(10);
      splitPane.setBounds(50, 60, 1454, 161);
      splitPane.setDividerLocation(600); 
      add(splitPane);
      
      String[] columnNames = {"STT", "Mã NV", "Tên tài khoản", "Mật khẩu", "Thời gian đăng nhập", "Thời gian đăng xuất", "Số giờ làm", "Trạng thái"};
      tableModel = new DefaultTableModel(columnNames, 0);
      table_1 = new JTable(tableModel);
      
      table_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
      table_1.setRowHeight(25);
      table_1.setShowGrid(true);
      
      
      JTableHeader tableHeader = table_1.getTableHeader();
      tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));
      
      Object[][] data = {
    	        {1, "NV002","admin01", "123456", "02/04/2025 08:30", "02/04/2025 18:00","6", "Online"},
    	        {2,"NV003", "user02", "abcdef", "01/04/2025 09:00", "01/04/2025 17:30","6", "Offline",},
    	        {3, "NV001","staff03", "qwerty", "30/03/2025 07:45", "30/03/2025 15:20","6", "Hoạt động"}
    	    };
      for (Object[] row : data) {
          tableModel.addRow(row);
      }

      // ScrollPane chứa bảng
      scrollPane = new RoundedScrollPane(table_1,30);
      scrollPane.setBounds(50, 256, 1454, 563);
      add(scrollPane);
      
      FlatRobotoFont.install();

  }
  class RoundedScrollPane extends JScrollPane {
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
  private void setupPlaceholder(JTextField textField, String placeholder) {
      textField.setText(placeholder);
      textField.setForeground(Color.GRAY);

      textField.addFocusListener(new java.awt.event.FocusListener() {
          @Override
          public void focusGained(java.awt.event.FocusEvent e) {
              if (textField.getText().equals(placeholder)) {
                  textField.setText("");
                  textField.setForeground(Color.BLACK);
              }
          }

          @Override
          public void focusLost(java.awt.event.FocusEvent e) {
              if (textField.getText().isEmpty()) {
                  textField.setText(placeholder);
                  textField.setForeground(Color.GRAY);
              }
          }
      });
  }
}