import javax.swing.*;
import java.awt.*;

public class UIDictionary extends JPanel {
    Graphics2D g2d;
    UIDictionary(){
        g2d = null;
    }

    UIDictionary(Graphics2D g2d){
        this.g2d = g2d;
    }

    public void drawUI(){

        g2d.setColor(new Color(55, 30, 179));
        g2d.fillRect(0,0,this.getWidth()/2,this.getHeight());
        g2d.setColor(new Color(61, 183, 150, 194));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(30,10,getWidth()/2-60,50);
        g2d.drawRect(30,100,getWidth()/2-60,getHeight()*2/3 - 80);
//        g2d.setColor(Color.black);
//        g2d.setFont(new Font("NewellsHand", Font.ITALIC, 30));
    }

}
