package projectcj.swing.coding.block.scope.function;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.GluePoint;

abstract public class JScopeBlock extends JBlockBase implements JScopableBlock {
    JNormalBlockBase innerBlock = null;

    // Represents top of inner block
    protected int DEFAULT_HEIGHT = 100;
    protected int UPPER_DEFAULT_HEIGHT = 50;
    protected int INNER_DEFAULT_HEIGHT = 40;
    protected int LINE_DEFAULT_WIDTH = 10;
    protected int LOWER_DEFAULT_HEIGHT = 10;

    protected int innerAdditionalHeight = 0;

    public JScopeBlock(Display display) {
        super(display, new Color(0x30455D));
        TYPE = 2;

        // Set default gluePoint (inside of scope)
        Point pointOfGluePoint = new Point(LINE_DEFAULT_WIDTH, UPPER_DEFAULT_HEIGHT);
        GluePoint gluePoint = new GluePoint(this, pointOfGluePoint, GluePoint.SCOPE_INNER_CHECK);
        gluePoints.addElement(gluePoint);

    }

    @Override
    public JNormalBlockBase getInnerBlock() {
        return innerBlock;
    }

    @Override
    public void setInnerBlock(JNormalBlockBase innerBlock) {
        this.innerBlock = innerBlock;
    }

    /**
     * When innerBlock is updated, height of block should be changed
     * 
     * @param dh
     */
    @Override
    public void updateScopeHeight(int dh) {
        innerAdditionalHeight += dh;
        polygons.elementAt(0).stretchVertically(dh);

        repaint();
        revalidate();
    }

    @Override
    public void handleMove(MouseEvent e) {
        Point pos = new Point(posx, posy);

        // movePropagation part
        // This block cannot be moved by other blocks
        // System.out.printf("ID: %d\n", blockID);
        setLocation(pos);
        posx = pos.x;
        posy = pos.y;

        if (innerBlock != null) {
            innerBlock.movePropagation(new Point(posx + LINE_DEFAULT_WIDTH, posy + UPPER_DEFAULT_HEIGHT));
        }
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs = { 200, 0, 0, 200, 200, 10, 10, 200 };
        int[] ys = { 40, 40, 100, 100, 90, 90, 50, 50 };
        v.add(new BlockPolygon(this, xs, ys, color));

        int[] xs2 = { 0, 40, 120, 160 };
        int[] ys2 = { 40, 0, 0, 40 };
        v.add(new BlockPolygon(this, xs2, ys2, new Color(0x3B5168)));
        v.elementAt(0).stretchVertically(-INNER_DEFAULT_HEIGHT);

        return v;
    }

}
