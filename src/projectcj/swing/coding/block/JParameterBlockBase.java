package projectcj.swing.coding.block;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;
import projectcj.swing.coding.block.special.ParameterGluePoint;

public abstract class JParameterBlockBase extends JNormalBlockBase {
    // When parameter is updated, other parameters' pos may should be modified.
    // So parameter glue points are seperated from others.
    public Vector<JParameter> parameters = new Vector<>();
    public String blockName = "";

    // Default distance between params
    public final int PARAM_DIST = 80;

    public JParameterBlockBase(Display display, Color color, String fname, int paramCount) {
        super(display, color);

        // Make params
        Vector<Integer> params = new Vector<>();
        for (int i = 0; i < paramCount; i++) {
            params.add(1);
        }

        init(display, color, fname, params);
    }

    public JParameterBlockBase(Display display, Color color, String fname, Vector<Integer> params) {
        super(display, color);
        init(display, color, fname, params);

    }

    /**
     * Initialize function
     * 
     * @param display
     * @param color
     * @param fname Name of this block
     * @param params Bitmask, 1 = LValue, 2 = RValue
     */
    private void init(Display display, Color color, String fname, Vector<Integer> params) {
        blockName = fname;

        // Add params
        int xOffset = X_OFFSET + 20; // X_OFFSET + border + distance between text and parameter
        for (int i = 0; i < params.size(); i++) {
            Point gluePos = new Point(xOffset, 10);
            ParameterGluePoint glue =
                    new ParameterGluePoint(this, gluePos, GluePoint.PARAMETER_CHECK);
            JParameter param = new JParameter(this, glue, i);

            parameters.add(param);

            xOffset += PARAM_DIST;
        }

        // Make polygons
        polygons = makePolygon();

        // And adjust some positions
        polygons.get(0).stretchHorizontaly(PARAM_DIST * params.size());

        // if (paramCount == 0) {
        // // !fix: only single gluepoint should be moved i think
        // for (GluePoint gluePoint : gluePoints) {
        // gluePoint.moveDelta(0, -20);
        // }
        // }

        // RValue offset
        BlockText text = texts.get(0);
        text.setX(text.getX() + X_OFFSET);
        text.setY(polygons.get(0).getPolygon().getBounds().height / 2 - 20);
        // System.out.println(additionalHeight);

        setInnerText(blockName);
    }

    @Override
    public void beforeGenerate() {
        super.beforeGenerate();

        // DEFAULT_WIDTH = 160;
        // DEFAULT_HEIGHT = 40;
        // additionalHeight = 20;
        // !!!
    }

    @Override
    public int movePropagation(Point pos) {
        int movSize = super.movePropagation(pos);

        // Move all parameters
        for (JParameter param : parameters) {
            JNormalBlockBase blk = (JNormalBlockBase) param.innerBlock;
            if (blk == null)
                continue;

            // Movement is processed by calculating newpos by gluePoint
            Point newPos = new Point(param.gluePoint.getPoint().x, param.gluePoint.getPoint().y);
            blk.movePropagation(newPos);
        }

        return movSize;
    }

    @Override
    public void changeZIndex(int index) {
        super.changeZIndex(index);

        // Manage parameter block's zindex
        JParameterBlockBase now = this;
        for (JParameter param : now.parameters) {
            if (param.innerBlock != null) {
                ((JNormalBlockBase) param.innerBlock).changeZIndex(0);
            }
        }
    }

    @Override
    public void releaseMouse() {
        super.releaseMouse();

        // Manage parameter block's zindex
        JParameterBlockBase now = this;
        for (JParameter param : now.parameters) {
            if (param.innerBlock != null) {
                ((JNormalBlockBase) param.innerBlock).releaseMouse();
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        JParameterBlockBase me = (JParameterBlockBase) super.clone();

        // Clone parameter blocks
        JParameterBlockBase now = this;
        for (int i = 0; i < now.parameters.size(); i++) {
            if (now.parameters.get(i).innerBlock != null) {
                me.parameters.get(i).innerBlock =
                        (JNormalBlockBase) now.parameters.get(i).innerBlock.clone();
            }
        }

        return me;
    }

    /**
     * Changes parameter size.
     * 
     * @param index which parameter
     * @param dw
     * @param dh
     * @return {actualDw, actualDh}
     */
    public Point changeParameterSize(int index, int dw, int dh) {
        JParameter now = parameters.get(index);

        // Actual dw and dh
        // Change this block's parameter size directly
        int actualDw = now.bolygon.stretchHorizontaly(dw);
        int actualDh = now.bolygon.stretchVertically(dh);

        now.width = now.width + actualDw;
        now.height = now.height + actualDh;

        // Change body size
        polygons.get(0).stretchHorizontaly(actualDw);
        polygons.get(0).stretchVertically(actualDh);

        // Change gluePoint pos
        for (GluePoint gluePoint : gluePoints) {
            gluePoint.moveDelta(0, actualDh);
        }

        // for (int i = index + 1; i < parameters.size(); i++) {
        // JParameter target = parameters.get(i);

        // // Change bolygon pos and gluePoint pos
        // target.moveDelta(actualDw, actualDh / 2);
        // }

        // And move antoher parameters' pos
        for (int i = 0; i < parameters.size(); i++) {
            JParameter target = parameters.get(i);
            if (target == now)
                continue;

            int tardw = actualDw, tardh = actualDh / 2;
            // If this parameter is on left side, dw is 0
            if (i < index)
                tardw = 0;

            // Change bolygon pos and gluePoint pos
            target.moveDelta(tardw, tardh);
        }

        for (BlockText text : texts) {
            text.setY(text.getY() + actualDh / 2);
        }

        // Propagation
        if (lowerBlock != null) {
            Point newpos = new Point(gluePoints.get(0).getPoint());
            lowerBlock.movePropagation(newpos);
        }

        if (upperParameter != null) {
            upperParameter.outerBlock.changeParameterSize(upperParameter.index, actualDw, actualDh);
        } else if (upperScope != null) {
            upperScope.updateScopeHeight(actualDh);
        }

        return new Point(actualDw, actualDh);
    }

    @Override
    public int setInnerText(String innerText) {
        int dx = super.setInnerText(innerText);
        this.blockName = innerText;

        // Move other parameters
        for (JParameter param : parameters) {
            param.moveDelta(dx, 0);
        }

        updateSize();
        return dx;
    }

    public void makeParameters(Vector<BlockPolygon> v) {
        // Make parameter polygons
        for (int i = 0; i < parameters.size(); i++) {
            // Param width = 60
            int[] pxs = {0 - 1, JParameter.DEFAULT_WIDTH + 1, JParameter.DEFAULT_WIDTH + 1, 0 - 1,
                    0 - 1, 10 - 1, 10 - 1, 0 - 1};
            int[] pys = {0 - 1, 0 - 1, 40 + 1, 40 + 1, 30 - 1, 30 - 1, 10 + 1, 10 + 1};
            BlockPolygon bolygon = new BlockPolygon(this, pxs, pys, Color.WHITE);
            bolygon.stretchHorizontaly(-JParameter.DEFAULT_WIDTH);
            bolygon.stretchVertically(-JParameter.DEFAULT_HEIGHT);

            // Offset
            bolygon.xOffset = parameters.get(i).gluePoint.getOffset().x;
            bolygon.yOffset = parameters.get(i).gluePoint.getOffset().y;

            v.add(bolygon);
            parameters.get(i).bolygon = bolygon;
        }
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = super.makePolygon();
        makeParameters(v);

        if (parameters.size() > 0) {
            v.get(0).stretchVertically(20);
            gluePoints.get(0).moveDelta(0, 20);
        }

        return v;
    }

    public void addParameter() {
        // TODO: implement!
    }
}
