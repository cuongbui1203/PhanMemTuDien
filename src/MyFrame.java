import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(){

    }
    public void initUI(){
        MyPanel panel = new MyPanel();
        this.add(panel);
        this.pack();
        this.setTitle("Test");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }


    public void Run(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame myFrame = new MyFrame();
            }
        });
    }
}
