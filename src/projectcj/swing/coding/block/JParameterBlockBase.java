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

    // X pos where new params will be
    public int newParamX;

    // Default distance between params' gluePoint
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
     * @param fname
     *            Name of this block
     * @param params
     *            Bitmask, 1 = LValue, 2 = RValue
     */
    private void init(Display display, Color color, String fname, Vector<Integer> params) {
        blockName = fname;
        newParamX = X_OFFSET + 20; // X_OFFSET + border + distance between text and parameter

        // Make polygons
        polygons = makePolygon();

        // Add params
        for (int i = 0; i < params.size(); i++) {
            addParameter(params.get(i));
        }

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
                me.parameters.get(i).innerBlock = (JNormalBlockBase) now.parameters.get(i).innerBlock.clone();
            }
        }

        return me;
    }

    /**
     * Changes parameter size.
     * 
     * @param index
     *            which parameter
     * @param dw
     * @param dh
     * @return {actualDw, actualDh}
     */
    public Point changeParameterSize(int index, int dw, int dh) {
        if (parameters.size() <= index)
            return new Point(0, 0);
        JParameter now = parameters.get(index);

        // Actual dw and dh
        // Change this block's parameter size directly
        int actualDw = now.bolygon.stretchHorizontaly(dw);
        int actualDh = now.bolygon.stretchVertically(dh);

        now.width += actualDw;
        now.height += actualDh;
        newParamX += actualDw;

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

        updateSize();
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
        newParamX += dx;

        updateSize();
        return dx;
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = super.makePolygon();
        return v;
    }

    public void addParameter(int type) {
        // Add data first
        int idx = parameters.size();
        Point gluePos = new Point(newParamX, 10);
        ParameterGluePoint glue;

        // LValue, RValue
        if (type == 1) {
            glue = new ParameterGluePoint(this, gluePos, GluePoint.PARAMETER_CHECK);
        } else {
            glue = new ParameterGluePoint(this, gluePos, GluePoint.LVALUE_BLOCK_TYPE | GluePoint.PARAMETER);
        }

        JParameter param = new JParameter(this, glue, idx);

        if (idx == 0) {
            for (BlockText text : texts) {
                text.setY(text.getY() + 10);
            }
            polygons.get(0).stretchVertically(20);
            gluePoints.get(0).moveDelta(0, 20);
        }

        polygons.get(0).stretchHorizontaly(PARAM_DIST);
        parameters.add(param);
        newParamX += PARAM_DIST;

        // Polygon part
        // Param width = 60, +-1: white border
        BlockPolygon bolygon;
        // LValue, RValue
        if (type == 1) {
            int[] pxs = { 0 - 1, JParameter.DEFAULT_WIDTH + 1, JParameter.DEFAULT_WIDTH + 1, 0 - 1,
                    0 - 1, 10 - 1, 10 - 1, 0 - 1 };
            int[] pys = { 0 - 1, 0 - 1, 40 + 1, 40 + 1, 30 - 1, 30 - 1, 10 + 1, 10 + 1 };
            bolygon = new BlockPolygon(this, pxs, pys, Color.WHITE);

        } else {
            int[] pxs = { 0 - 1, JParameter.DEFAULT_WIDTH + 1, JParameter.DEFAULT_WIDTH + 1,
                    JParameter.DEFAULT_WIDTH - 10 + 1, JParameter.DEFAULT_WIDTH - 10 + 1, JParameter.DEFAULT_WIDTH + 1,
                    JParameter.DEFAULT_WIDTH + 1, 0 - 1,
                    0 - 1, 10 - 1, 10 - 1, 0 - 1 };
            int[] pys = { 0 - 1, 0 - 1, 10 + 1, 10 + 1, 40 - 10 - 1, 40 - 10 - 1, 40 + 1, 40 + 1, 30 - 1, 30 - 1,
                    10 + 1, 10 + 1 };
            bolygon = new BlockPolygon(this, pxs, pys, Color.WHITE);

        }
        bolygon.stretchHorizontaly(-JParameter.DEFAULT_WIDTH);
        bolygon.stretchVertically(-JParameter.DEFAULT_HEIGHT);

        // Offset
        bolygon.xOffset = parameters.get(idx).gluePoint.getOffset().x;
        bolygon.yOffset = parameters.get(idx).gluePoint.getOffset().y;

        polygons.add(bolygon);
        parameters.get(idx).bolygon = bolygon;

        if (idx == 0) {
            if (upperParameter != null) {
                upperParameter.outerBlock.changeParameterSize(upperParameter.index, PARAM_DIST, 20);
            } else if (upperScope != null) {
                upperScope.updateScopeHeight(20);
            }
        } else {
            if (upperParameter != null) {
                upperParameter.outerBlock.changeParameterSize(upperParameter.index, PARAM_DIST, 0);
            }
        }

        updateSize();
    }

    /**
     * Remove parameter. If there is block, disconnects.
     */
    public void removeParameter() {
        JParameter targetParam = parameters.get(parameters.size() - 1);
        // int paramDist = targetParam.width + 20;
        // System.out.println(targetParam.width);
        int paramDist = PARAM_DIST;

        // If there is another block
        if (targetParam.innerBlock != null) {
            JNormalBlockBase tmp = (JNormalBlockBase) targetParam.innerBlock;

            // Move original block
            Point newP = targetParam.gluePoint.getPoint();
            newP.translate(10, 10);
            tmp.disconnect(newP);
        }

        // Remove!
        parameters.removeElementAt(parameters.size() - 1);
        polygons.remove(polygons.size() - 1);

        newParamX -= paramDist;
        polygons.get(0).stretchHorizontaly(-paramDist);

        if (parameters.size() == 0) {
            for (BlockText text : texts) {
                text.setY(text.getY() - 10);
            }

            polygons.get(0).stretchVertically(-20);
            gluePoints.get(0).moveDelta(0, -20);

            if (upperParameter != null) {
                upperParameter.outerBlock.changeParameterSize(upperParameter.index, -paramDist, -20);
            } else if (upperScope != null) {
                upperScope.updateScopeHeight(-20);
            }
        } else {
            if (upperParameter != null) {
                upperParameter.outerBlock.changeParameterSize(upperParameter.index, -paramDist, 0);
            }
        }

        updateSize();
    }
}
