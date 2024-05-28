package projectcj.swing.block;

import javax.swing.*;
import javax.swing.event.*;
import projectcj.swing.Display;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Base of blocks.
 */
public abstract class JBlockBase extends JPanel {
    // For tracking another blocks' glue area
    Display display;

    // Shape
    Vector<Integer> xs = new Vector<>();
    Vector<Integer> ys = new Vector<>();

    int posx;
    int posy;

    int xoffset;
    int yoffset;

    int width;
    int height;

    // Event
    class MyMouseAdapter extends MouseAdapter {
        /**
         * Adjust offset
         */
        @Override
        public void mousePressed(MouseEvent e) {
            xoffset = display.getMouseX() - posx;
            yoffset = display.getMouseY() - posy;
            // System.out.println("Offset");

            // repaint();
        }

        /**
         * Make move
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            posx = display.getMouseX() - xoffset;
            posy = display.getMouseY() - yoffset;
            // System.out.println("Move block");

            redispatchToParent(e);

            setLocation(posx, posy);
            // repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            redispatchToParent(e);
        }

        // https://stackoverflow.com/questions/3818246/passing-events-to-parent
        private void redispatchToParent(MouseEvent e) {
            Component source = (Component) e.getSource();
            MouseEvent parentEvent =
                    SwingUtilities.convertMouseEvent(source, e, source.getParent());
            source.getParent().dispatchEvent(parentEvent);
        }
    }

    /**
     * Constructor
     * 
     * @param display original display
     */
    public JBlockBase(Display display) {
        posx = 0;
        posy = 0;
        xoffset = 0;
        yoffset = 0;

        this.display = display;


        MyMouseAdapter mouseAdapter = new MyMouseAdapter();
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("repaint");
        g.setColor(Color.RED);

        // Convert vector to array
        int xarr[] = new int[xs.size()];
        int yarr[] = new int[xs.size()];

        for (int i = 0; i < xarr.length; i++) {
            xarr[i] = xs.get(i);
            System.out.printf("%d ", xarr[i]);
        }

        System.out.println();

        for (int i = 0; i < yarr.length; i++) {
            yarr[i] = ys.get(i);
            System.out.printf("%d ", yarr[i]);
        }

        System.out.println();
        System.out.println();

        // Fill block
        g.fillPolygon(xarr, yarr, xs.size());
    }


    /**
     * Get x value of glue point
     */
    public int getGlueX() {
        return posx;
    }

    /**
     * Get y value of glue point
     */
    public int getGlueY() {
        return posy + height;
    }
}
