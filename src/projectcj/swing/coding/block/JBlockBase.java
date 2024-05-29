package projectcj.swing.coding.block;

import javax.swing.*;
import javax.swing.event.*;
import projectcj.swing.coding.Display;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Base of blocks.
 */
public abstract class JBlockBase extends JPanel {
    // ID
    static int idReservation = 0;
    int blockID;

    // Color for test
    public Color color;

    // For tracking another blocks' glue area
    Display display;

    // Block connected to lower part.
    // This block object will be JNormalBlockBase object or null.
    JNormalBlockBase lowerBlock = null;

    // Shape
    Vector<Integer> xs = new Vector<>();
    Vector<Integer> ys = new Vector<>();

    int posx;
    int posy;

    int xoffset;
    int yoffset;

    int width;
    int height;

    /**
     * Constructor
     * 
     * @param display original display
     */
    public JBlockBase(Display display) {
        blockID = idReservation++;

        posx = 0;
        posy = 0;
        xoffset = 0;
        yoffset = 0;

        this.display = display;


        BlockMouseAdapter mouseAdapter = new BlockMouseAdapter(this);
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // System.out.println("repaint");
        g.setColor(color);

        // Convert vector to array
        int xarr[] = new int[xs.size()];
        int yarr[] = new int[xs.size()];

        for (int i = 0; i < xarr.length; i++) {
            xarr[i] = xs.get(i);
            // System.out.printf("%d ", xarr[i]);
        }

        // System.out.println();

        for (int i = 0; i < yarr.length; i++) {
            yarr[i] = ys.get(i);
            // System.out.printf("%d ", yarr[i]);
        }

        // System.out.println();
        // System.out.println();

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

    /**
     * get coordinate of glue point
     * 
     * @return Point object which refers to glue point
     */
    public Point getGluePoint() {
        return new Point(getGlueX(), getGlueY());
    }

    /**
     * Get target glue object(where this object should be glued).
     * 
     * @param pos current pos
     * @return JBlockBase object which refers to glue point
     */
    public JBlockBase getTargetGlueObject(Point pos) {
        return display.getGlueObject(this, pos);
    }

    /**
     * This method handles movement
     * 
     * @param e MouseEvent object from mouseDragged event
     */
    public void handleMove(MouseEvent e) {
        System.out.println("JBlockBase move (default)");
        setLocation(posx, posy);
    }
}
