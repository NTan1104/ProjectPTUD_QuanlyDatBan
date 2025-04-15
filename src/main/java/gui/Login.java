package gui;

import com.formdev.flatlaf.FlatClientProperties;	
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

public class Login extends JPanel {

    private static final long serialVersionUID = 1L;
    private Main mainFrame; // Tham chiếu đến Main

    // Constructor nhận tham chiếu Main
    public Login(Main mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
        });
        setLayout(new MigLayout("wrap,fillx,insets 45 45 50 45", "[fill]"));
        JLabel title = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JCheckBox chRememberMe = new JCheckBox("Nhớ tài khoản");
        JButton buttonLogin = new JButton("Đăng nhập");
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonLogin.doClick(); 
            }
        });
        title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
        txtUsername.putClientProperty(FlatClientProperties.STYLE,
                "" + "margin:5,10,5,10;" + "focusWidth:1;" + "innerFocusWidth:0");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,
                "" + "margin:5,10,5,10;" + "focusWidth:1;" + "innerFocusWidth:0;" + "showRevealButton:true");
        buttonLogin.putClientProperty(FlatClientProperties.STYLE,
                "" + "background:$Component.accentColor;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điền tài khoản của bạn");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Điền mật khẩu của bạn");

        add(title);
        add(new JLabel("Tài khoản"), "gapy 20");
        add(txtUsername);
        add(new JLabel("Mật khẩu"), "gapy 10");
        add(txtPassword);
        add(chRememberMe);
        add(buttonLogin, "gapy 30");

        // Xử lý sự kiện đăng nhập
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Vui lòng điền đầy đủ thông tin!", "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    if (username.equals("1") && password.equals("1")) {
                        EventQueue.invokeLater(() -> {
                            mainFrame.dispose(); 
                            homeNV trangChu = new homeNV(mainFrame);
                            trangChu.setVisible(true);
                        });
                    } else {
                        if (username.equals("2") && password.equals("2")) {
                            EventQueue.invokeLater(() -> {
                                mainFrame.dispose(); 
                                HomeQL trangChu = new HomeQL(mainFrame);
                                trangChu.setVisible(true);
                            });
                        } else {
                        JOptionPane.showMessageDialog(Login.this, "Tài khoản hoặc mật khẩu không đúng!",
                                "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                        }
                        }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int arc = UIScale.scale(20);
        g2.setColor(getBackground());
        g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));
        g2.dispose();
        super.paintComponent(g);
    }
}