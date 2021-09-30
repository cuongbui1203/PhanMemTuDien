//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//class Surface extends JPanel  implements ActionListener {
//
//    private final int DELAY = 15;
//    private final int INITIAL_DELAY = 200;
//    private Timer timer;
//    private int x = 1;
//    private float alpha = 1;
//
//    public Surface() {
//
//        initTimer();
//    }
//
//    private void initTimer() {
//
//        timer = new Timer(DELAY, this);
//        timer.setInitialDelay(INITIAL_DELAY);
//        timer.start();
//    }
//
//    private void doDrawing(Graphics g) {
//
//        Graphics2D g2d = (Graphics2D) g.create();
//
//        RenderingHints rh =
//                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//                        RenderingHints.VALUE_ANTIALIAS_ON);
//
//        rh.put(RenderingHints.KEY_RENDERING,
//                RenderingHints.VALUE_RENDER_QUALITY);
//
//        g2d.setRenderingHints(rh);
//
//        Font font = new Font("Dialog", Font.PLAIN, x);
//        g2d.setFont(font);
//
//        FontMetrics fm = g2d.getFontMetrics();
//        String s = "PhoCode";
//        Dimension size = getSize();
//
//        int w = (int) size.getWidth();
//        int h = (int) size.getHeight();
//
//        int stringWidth = fm.stringWidth(s);
//        AlphaComposite ac = AlphaComposite.getInstance(
//                AlphaComposite.SRC_OVER, alpha);
//        g2d.setComposite(ac);
//
//        g2d.drawString(s, (w - stringWidth) / 2, h / 2);
//
//        g2d.dispose();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        doDrawing(g);
//    }
//
//    private void step() {
//
//        x += 1;
//
//        if (x > 40)
//            alpha -= 0.01;
//
//        if (alpha <= 0.01)
//            timer.stop();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        step();
//        repaint();
//    }
//}
//
//public class PuffEx extends JFrame {
//
//    public PuffEx() {
//
//        initUI();
//    }
//
//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                PuffEx ex = new PuffEx();
//                ex.setVisible(true);
//            }
//        });
//    }
//
//    private void initUI() {
//
//        setTitle("Puff");
//
//        add(new Surface());
//
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//    }
//}
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    private Rectangle2D rect;
    private Ellipse2D ellipse;
    private float alpha_rectangle;
    private float alpha_ellipse;

    public Surface() {

        initSurface();
    }

    private void initSurface() {

        addMouseListener(new HitTestAdapter());
        addKeyListener(new HitTestAdapter());

        rect = new Rectangle2D.Float(20f, 20f, 80f, 50f);
        ellipse = new Ellipse2D.Float(120f, 30f, 60f, 60f);

        alpha_rectangle = 1f;
        alpha_ellipse = 1f;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setPaint(new Color(50, 50, 50));

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                alpha_rectangle));
        g2d.fill(rect);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                alpha_ellipse));
        g2d.fill(ellipse);

        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    class RectRunnable implements Runnable {

        private Thread runner;

        public RectRunnable() {

            initThread();
        }

        private void initThread() {

            runner = new Thread(this);
            runner.start();
        }

        @Override
        public void run() {

            while (alpha_rectangle >= 0) {

                repaint();
                alpha_rectangle += -0.01f;

                if (alpha_rectangle < 0) {
                    alpha_rectangle = 0;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Surface.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
    }

    class HitTestAdapter extends MouseAdapter implements Runnable, KeyListener {
        private RectRunnable rectAnimator;
        private Thread ellipseAnimator;

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
//            if (rect.contains(x, y)) {
//                rectAnimator = new RectRunnable();
//            }
//            if (ellipse.contains(x, y)) {
//                ellipseAnimator = new Thread(this);
//                ellipseAnimator.start();
//            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (rect.contains(x, y)) {
                rectAnimator = new RectRunnable();
            }
            if (ellipse.contains(x, y)) {
                ellipseAnimator = new Thread(this);
                ellipseAnimator.start();
            }
        }



        @Override
        public void run() {
            while (alpha_ellipse >= 0) {
                repaint();
                alpha_ellipse += -0.01f;
                if (alpha_ellipse < 0) {
                    alpha_ellipse = 1;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Surface.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println((char) e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}

public class HitTestingEx extends JFrame {
    public HitTestingEx() {
        add(new Surface());
        setTitle("Hit testing");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                HitTestingEx ex = new HitTestingEx();
                ex.setVisible(true);
            }
        });
    }
}