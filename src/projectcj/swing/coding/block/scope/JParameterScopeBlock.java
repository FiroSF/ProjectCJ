package projectcj.swing.coding.block.scope;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;

abstract public class JParameterScopeBlock extends JParameterBlockBase implements JScopableBlock {
    JNormalBlockBase innerBlock = null;

    // Represents top of inner block
    protected int DEFAULT_HEIGHT = 100;
    protected int UPPER_DEFAULT_HEIGHT = 50;
    protected static int INNER_DEFAULT_HEIGHT = 40;
    protected int LINE_DEFAULT_WIDTH = 10;
    protected int LOWER_DEFAULT_HEIGHT = 10;

    protected int innerAdditionalHeight = 0;

    public JParameterScopeBlock(Display display, Color color, String bname, int paramCount) {
        super(display, color, bname, paramCount);
        TYPE = 3;

        // Scope body's height
        // additionalHeight += 60;

        // Stratch brace
        polygons.get(1).stretchHorizontaly(PARAM_DIST * paramCount);

        // Set default gluePoint (inside of scope)
        Point pointOfGluePoint = new Point(LINE_DEFAULT_WIDTH, UPPER_DEFAULT_HEIGHT + (paramCount != 0 ? 20 : 0));
        GluePoint gluePoint = new GluePoint(this, pointOfGluePoint, GluePoint.SCOPE_INNER_CHECK);
        gluePoints.addElement(gluePoint);
        updateSize();

        // Re-calc gluePoint
        // System.out.println(getCalcedHeight());
        gluePoints.get(0).moveDelta(0, getCalcedHeight() - gluePoints.get(0).getOffset().y);
    }

    @Override
    public JNormalBlockBase getInnerBlock() {
        return innerBlock;
    }

    @Override
    public void setInnerBlock(JNormalBlockBase innerBlock) {
        this.innerBlock = innerBlock;
    }

    @Override
    public void updateScopeHeight(int dh) {
        innerAdditionalHeight += dh;
        // additionalHeight += dh;
        int realdh = polygons.elementAt(1).stretchVertically(dh);

        // Move gluePoint
        gluePoints.get(0).moveDelta(0, realdh);

        if (upperScope != null) {
            // System.out.println("asdf");
            upperScope.updateScopeHeight(realdh);
        }

        // When height is updated, lower block's position should be modified.
        if (lowerBlock != null) {
            lowerBlock.movePropagation(gluePoints.get(0).getPoint());
        }

        repaint();
        revalidate();
    }

    @Override
    public Point changeParameterSize(int index, int dw, int dh) {
        Point delta = super.changeParameterSize(index, dw, dh);

        // Move braces
        polygons.get(1).moveDelta(0, delta.y);

        // And stratch braces
        polygons.get(1).stretchHorizontaly(delta.x);

        // And move inner blocks
        if (innerBlock != null) {
            innerBlock.movePropagation(gluePoints.get(1).getPoint());
        }

        updateSize();
        return delta;
    }

    @Override
    public int movePropagation(Point pos) {
        // super
        int movSize = super.movePropagation(pos);

        setLocation(pos);
        posx = pos.x;
        posy = pos.y;

        // Move innerBlocks
        if (innerBlock != null) {
            // innerBlock.movePropagation(new Point(posx + LINE_DEFAULT_WIDTH, posy +
            // UPPER_DEFAULT_HEIGHT));
            innerBlock.movePropagation(gluePoints.get(1).getPoint());
        }

        return movSize;
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs2 = { 0, 0, MINIMUM_WIDTH, MINIMUM_WIDTH };
        int[] ys2 = { 40, 0, 0, 40 };
        v.add(new BlockPolygon(this, xs2, ys2, new Color(0x3B5168)));

        int[] xs = { MINIMUM_WIDTH + 20, 0, 0, MINIMUM_WIDTH + 20, MINIMUM_WIDTH + 20, 10, 10, MINIMUM_WIDTH + 20 };
        int[] ys = { 40, 40, 100, 100, 90, 90, 50, 50 };
        v.add(new BlockPolygon(this, xs, ys, color));

        v.get(1).stretchVertically(-JParameterScopeBlock.INNER_DEFAULT_HEIGHT);
        v.get(0).stretchHorizontaly(-MINIMUM_WIDTH + 20 + X_OFFSET);
        v.get(1).stretchHorizontaly(-MINIMUM_WIDTH + 20 + X_OFFSET);
        makeParameters(v);

        if (parameters.size() > 0) {
            v.get(0).stretchVertically(20);
            v.get(1).moveDelta(0, 20);
            // gluePoints.get(0).moveDelta(0, 20);
        }

        return v;
    }

    @Override
    public int setInnerText(String innerText) {
        int originalWidth = getInnerText().getWidth();
        getInnerText().setText(innerText);

        int dx = getInnerText().getWidth() - originalWidth;
        // System.out.println(dx);
        // System.out.println("!!");

        // Stratch body
        int realdx = Math.max(0, getTrueWidth() - getCalcedWidth() + dx);
        polygons.get(0).stretchHorizontaly(realdx);
        polygons.get(1).stretchHorizontaly(realdx);

        // Move other parameters
        for (JParameter param : parameters) {
            param.moveDelta(dx, 0);
        }

        updateSize();
        return dx;
    }
}
