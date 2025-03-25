package gui;
	
import javax.swing.JFrame;	
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import gui.drawer.DrawerNV;
//import gui.drawer.DrawerQL;
import gui.tabbed.WindowsTabbed;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
	
public class homeNV extends JFrame {

	private static final long serialVersionUID = 1L;

	public homeNV() {
		//Color theme
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("themes");
		FlatLightLaf.setup();
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		//Drawer
		GlassPanePopup.install(this);
		DrawerNV myDrawerBuilder = new DrawerNV();
//		DrawerQL myDrawerBuilder = new DrawerQL();
		Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
		//Set size
		java.net.URL iconURL = getClass().getResource("bbqIcon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panelBody = new JPanel();
		panelBody.setBounds(0, 0, 1936, 1097);
		getContentPane().add(panelBody);
		panelBody.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		//Tabbed
		WindowsTabbed.getInstance().install(this, panelBody);

	}
}
