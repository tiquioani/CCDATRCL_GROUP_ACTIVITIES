package Finals;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class SemiTransparentBackgroundPanel extends JPanel {
    public final Color backgroundColor;

    public SemiTransparentBackgroundPanel(Color color) {
        this.backgroundColor = color;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
