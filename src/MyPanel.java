import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    UIDictionary ui = null;
    MyPanel() {
//        ui = new UIDictionary(this.);
        this.setPreferredSize(new Dimension(700, 700));
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        ui.drawUI();

//        g2d.dispose();
    }

}
