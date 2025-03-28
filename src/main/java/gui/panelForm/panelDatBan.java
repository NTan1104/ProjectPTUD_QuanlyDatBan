package gui.panelForm;

import javax.swing.JLabel;	
import javax.swing.JPanel;
import java.awt.Color;

public class panelDatBan extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public panelDatBan() {
		setBackground(Color.RED);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("quan ly dat ban");
		lblNewLabel.setBounds(187, 5, 75, 14);
		add(lblNewLabel);

		
	}

}
