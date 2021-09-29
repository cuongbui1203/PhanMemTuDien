import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyPanel() {
        this.setPreferredSize(new Dimension(700, 700));
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(0, 0, 500, 500);
        g2d.drawOval(10,20,400,200);
        g2d.dispose();
    }

}
