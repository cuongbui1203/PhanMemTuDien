import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyPanel() {
        this.setPreferredSize(new Dimension(700, 700));
    }

    private void drawUI(Graphics2D g2d){

        g2d.setColor(new Color(100,100,200));
        g2d.fillRect(0,0,this.getWidth()/2,this.getHeight());
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(30,10,getWidth()/2-60,50);
        g2d.drawRect(30,100,getWidth()/2-60,getHeight()*2/3 - 80);
        g2d.setColor(Color.black);
//        g2d.setFont(new Font("NewellsHand", Font.ITALIC, 30));
    }

    private void drawString(Graphics2D g2d,String str,int fontSize){

    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawUI(g2d);
        g2d.drawString("Dictionary",40,50);
        g2d.dispose();
    }

}
