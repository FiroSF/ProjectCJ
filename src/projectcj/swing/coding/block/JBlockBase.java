package projectcj.swing.coding.block;

import javax.swing.*;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;
import projectcj.swing.coding.block.special.GluePoint;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Base of blocks.
 */
public abstract class JBlockBase extends JPanel {
    // 1 = JNormalBlock, 2 = JScopeBlock
    protected int TYPE = 0;
    public int DEFAULT_WIDTH = 100;
    public int DEFAULT_HEIGHT = 50;

    // ID
    static int idReservation = 0;
    protected final int blockID;

    // Color for test
    public Color color;

    // For tracking another blocks' glue area
    protected Display display;

    // Upper scope block.
    public JScopableBlock upperScope = null;

    // Shape
    // protected Vector<Integer> xs = new Vector<>();
    // protected Vector<Integer> ys = new Vector<>();
    // !Change to protected
    public Vector<BlockPolygon> polygons = new Vector<>();
    protected Vector<BlockText> texts = new Vector<>();
    protected Vector<GluePoint> gluePoints = new Vector<>();

    public int posx;
    public int posy;

    protected int additionalWidth = 0;
    protected int additionalHeight = 0;

    public int xoffset;
    public int yoffset;

    /**
     * Constructor
     * 
     * @param display
     *            original display
     */
    public JBlockBase(Display display) {
        setOpaque(false);
        blockID = idReservation++;

        posx = 0;
        posy = 0;

        this.display = display;

        beforeGenerate();
        // BlockMouseAdapter mouseAdapter = new BlockMouseAdapter(this);
        // addMouseMotionListener(mouseAdapter);
        // addMouseListener(mouseAdapter);
    }

    public int getType() {
        return TYPE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Anti-aliasing
        // https://stackoverflow.com/a/13236994
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill polygons
        for (BlockPolygon polygon : polygons) {
            g.setColor(polygon.getColor());
            g.fillPolygon(polygon.getPolygon());
        }

        for (BlockText text : texts) {
            g.setColor(text.getColor());
            g.setFont(text.getFont());
            g.drawString(text.getText(), text.getX(), text.getY() + text.getFont().getSize());
        }
    }

    /**
     * Get glue points of this block
     * 
     * @return GluePoint vector object which refers to glue points
     */
    public Vector<GluePoint> getGluePoints() {
        return gluePoints;
    }

    /**
     * Get target glue object(where this object should be glued).
     * 
     * @param pos
     *            current pos
     * @return GluePoint object which refers to glue point
     */
    public GluePoint getTargetGlueObject(Point pos) {
        return display.getGlueObject(this, pos);
    }

    public int getWidth() {
        return DEFAULT_WIDTH + additionalWidth;
    }

    public int getHeight() {
        return DEFAULT_HEIGHT + additionalHeight;
    }

    public void setWidth(int w) {
        additionalWidth = w - DEFAULT_HEIGHT;
    }

    public void setHeight(int h) {
        additionalHeight = h - DEFAULT_HEIGHT;
    }

    /**
     * When params are updated, width of block should be changed
     * 
     * @param dw
     */
    public void updateWidth(int dw) {
        additionalWidth += dw;
        setSize(getWidth(), getHeight());

        // System.out.printf("%d, %d\n", newdw, newdh);
        // System.out.printf("%d, %d\n", DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT
        // + additionalHeight);
    }

    /**
     * When params are updated, height of block should be changed
     * 
     * @param dh
     */
    public void updateHeight(int dh) {
        additionalHeight += dh;
        // System.out.printf("%d, %d\n", getWidth(), getHeight());
        setSize(getWidth(), getHeight());

    }

    /**
     * When polygons are update, block's size should be updated
     * 
     * @param dh
     */
    public void updateSize() {
        // When polygons are not loaded
        if (polygons.size() == 0)
            return;

        int xmax = 0, ymax = 0;
        for (BlockPolygon bolygon : polygons) {
            Polygon polygon = bolygon.getPolygon();
            for (int i = 0; i < polygon.npoints; i++) {
                int x = polygon.xpoints[i];
                int y = polygon.ypoints[i];

                xmax = Math.max(x, xmax);
                ymax = Math.max(y, ymax);
            }
        }

        // System.out.printf("%d, %d\n", xmax, ymax);
        int newdw = xmax - DEFAULT_WIDTH, newdh = ymax - DEFAULT_HEIGHT;
        if (additionalWidth != newdw) {
            updateWidth(newdw - additionalWidth);
        }
        if (additionalHeight != newdh) {
            updateHeight(newdh - additionalHeight);
        }
    }

    /**
     * This method handles movement
     * 
     * @param e
     *            MouseEvent object from mouseDragged event
     */
    public abstract void handleMove(MouseEvent e);

    /**
     * Thie method generates polygons of this block
     * 
     * @return
     */
    public abstract Vector<BlockPolygon> makePolygon();

    /**
     * Compiler helper method https://yarisong.tistory.com/48
     * 
     * @return proper core class
     */
    public abstract <T extends BlockBase> T getCoreClassObj(ScopeBlock scope);

    /**
     * Before runs constructors some code, this method will be run first
     */
    public void beforeGenerate() {

    }

}
