package projectcj.swing.coding.block;

import javax.swing.*;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
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
 * 
 */
public abstract class JBlockBase extends JPanel implements Cloneable {
    // 1 = JNormalBlock, 2 = JScopeBlock, 3 = JNormalScopeBlock
    // See GluePoiunt's consts
    protected int TYPE = 0;
    public int DEFAULT_WIDTH = 20;
    public int MINIMUM_WIDTH = 60;
    public int DEFAULT_HEIGHT = 40;
    protected int additionalWidth = 0;
    protected int additionalHeight = 0;

    // ID
    static int idReservation = 0;
    protected final int blockID;

    // Block color
    public Color color = Color.GRAY;

    // For tracking another blocks' glue area
    public Display display;

    // Upper scope block.
    public JScopableBlock upperScope = null;

    // Shape
    // protected Vector<Integer> xs = new Vector<>();
    // protected Vector<Integer> ys = new Vector<>();
    // !Change to protected
    public Vector<BlockPolygon> polygons = new Vector<>();
    protected Vector<BlockText> texts = new Vector<>();
    protected Vector<GluePoint> gluePoints = new Vector<>();

    // Records block's absolute pos, and calc if there is gluepoint
    public int posx;
    public int posy;

    // Relative pos of mouse, when clicked
    public int xoffset;
    public int yoffset;

    // JLValue, JRValue offsets
    public int X_OFFSET = 0;

    /**
     * Constructor
     * 
     * @param display
     *            original display
     */
    public JBlockBase(Display display, Color color) {
        setOpaque(false);
        blockID = idReservation++;

        posx = 0;
        posy = 0;

        this.display = display;
        this.color = color;

        texts.add(new BlockText(this, "", 10, additionalHeight / 2));

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

    public int getCalcedWidth() {
        return Math.max(MINIMUM_WIDTH, DEFAULT_WIDTH + additionalWidth);
        // return DEFAULT_WIDTH + additionalWidth;
    }

    public int getTrueWidth() {
        return DEFAULT_WIDTH + additionalWidth;
    }

    public int getCalcedHeight() {
        return DEFAULT_HEIGHT + additionalHeight;
    }

    public void setWidth(int w) {
        additionalWidth = w - DEFAULT_WIDTH;
    }

    public void setHeight(int h) {
        additionalHeight = h - DEFAULT_HEIGHT;
    }

    /**
     * When params are updated, width of block should be changed
     * 
     * <p>
     * This method is called by calling updateSize.
     * 
     * @param dw
     */
    private void updateWidth(int dw) {
        additionalWidth += dw;
        setSize(getCalcedWidth(), getCalcedHeight());

        // System.out.printf("%d, %d\n", newdw, newdh);
        // System.out.printf("%d, %d\n", DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT
        // + additionalHeight);
    }

    /**
     * When params are updated, height of block should be changed
     * 
     * <p>
     * This method is called by calling updateSize.
     * 
     * @param dh
     */
    private void updateHeight(int dh) {
        additionalHeight += dh;
        // System.out.printf("%d, %d\n", getCalcedWidth(), getCalcedHeight());
        setSize(getCalcedWidth(), getCalcedHeight());

    }

    /**
     * When polygons are updated, block's size should be updated
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

        // https://stackoverflow.com/a/5921210/24828578
        setPreferredSize(new Dimension(getCalcedWidth(), getCalcedHeight()));
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

    public abstract <T extends BlockBase> T getCoreClassObj(ScopableBlock scope);

    /**
     * Change zIndex of block. And propagation.
     * 
     * @param index
     */
    public void changeZIndex(int index) {
        if (this.getParent() == display.blockContainer)
            display.blockContainer.remove(this);
        if (this.getParent() != display.overlayContainer)
            display.overlayContainer.add(this);

        display.overlayContainer.setComponentZOrder(this, 0);

        // Manage inner block's zindex
        if (this instanceof JScopableBlock) {
            JNormalBlockBase now = ((JScopableBlock) this).getInnerBlock();
            while (now != null) {
                now.changeZIndex(0);
                now = now.lowerBlock;
            }
        }

    }

    /**
     * When release mouse, blocks should be moved into blockContainer.
     */
    public void releaseMouse() {
        if (this.getParent() == display.overlayContainer)
            display.overlayContainer.remove(this);
        if (this.getParent() != display.blockContainer)
            display.blockContainer.add(this);

        display.blockContainer.setComponentZOrder(this, 0);

        // Manage inner block's zindex
        if (this instanceof JScopableBlock) {
            JNormalBlockBase now = ((JScopableBlock) this).getInnerBlock();
            while (now != null) {
                now.releaseMouse();
                now = now.lowerBlock;
            }
        }
    }

    /**
     * Before runs constructors some code, this method will be run first
     */
    public void beforeGenerate() {

    }

    public BlockText getInnerText() {
        return texts.get(0);
    }

    /**
     * Sets inner text.
     * 
     * <p>
     * When set inner text, shape of block shoule be transformed.
     * 
     * @param innerText
     */
    public abstract int setInnerText(String innerText);

    /**
     * When object is moved by upper blocks, this method moves object properly.
     * 
     * @param pos
     * 
     * @return Moved height
     */
    abstract public int movePropagation(Point pos);

    /**
     * Make copy of block
     * 
     * @return Copy of block itself
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        JBlockBase me = instantiateMe();

        // Manage inner block
        if (me instanceof JScopableBlock) {
            JNormalBlockBase now = ((JScopableBlock) me).getInnerBlock();
            if (now != null) {
                ((JScopableBlock) me).setInnerBlock((JNormalBlockBase) now.clone());
            }
        }

        return me;
    }

    /**
     * Make copy of block, but only me. not lowerblocks or innerblocks, etc.
     * 
     * @return Copy of block itself
     */
    abstract protected JBlockBase instantiateMe();
}
