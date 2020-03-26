package crawling.entertainment.feed_the_sanke.display;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width;
    private int height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        initializeJFrame();
        initializeCanvas();
        frame.add(canvas);
        frame.pack();
    }

    private void initializeJFrame() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeCanvas() {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
