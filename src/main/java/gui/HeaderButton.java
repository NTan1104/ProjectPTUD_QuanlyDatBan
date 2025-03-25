package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;

public class HeaderButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RippleEffect rippleEffect;
	public HeaderButton(String text) {
		super(text);
		init();
	}
	private void init() {
		rippleEffect = new RippleEffect(this);
		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +3");
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		int arc = UIScale.scale(20);
		rippleEffect.reder(g, new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));
	}
}
