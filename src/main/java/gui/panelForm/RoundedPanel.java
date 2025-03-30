package gui.panelForm;
import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JButton {
    private static final long serialVersionUID = 1L;
    private int cornerRadius; // Bán kính góc bo

    public RoundedPanel(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        setFocusPainted(false); // Loại bỏ hiệu ứng khi được focus
        setContentAreaFilled(false); // Không vẽ nền mặc định
        setBorderPainted(false); // Loại bỏ đường viền mặc định
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Nền bo góc
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Vẽ text
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }

    
}
