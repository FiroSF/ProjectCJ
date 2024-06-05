package projectcj.swing.coding.block;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;
import projectcj.swing.coding.block.special.ParameterGluePoint;
import projectcj.swing.coding.block.variable.JLValue;
import projectcj.swing.coding.block.variable.JRValue;

public abstract class JFunctionRunnerBlockBase extends JParameterBlockBase {
    public String functionName = "";
    public int FUNCTION_NAME_LENGTH = 0;
    public final int PARAM_DIST = 100;

    public JFunctionRunnerBlockBase(Display display, Color color, String fname, int paramCount, int x, int y) {
        super(display, color);

        functionName = fname;
        // TODO: name length part
        FUNCTION_NAME_LENGTH = functionName.length() * 20;

        // Add params
        int xOffset = X_OFFSET + FUNCTION_NAME_LENGTH + 10;
        if (paramCount == 0) {
            additionalHeight -= 20;
        }
        for (int i = 0; i < paramCount; i++) {
            Point gluePos = new Point(xOffset, 10);
            ParameterGluePoint glue = new ParameterGluePoint(this, gluePos, GluePoint.PARAMETER_CHECK);
            JParameter param = new JParameter(this, glue, i);

            parameters.add(param);

            xOffset += PARAM_DIST;
        }

        setWidth(DEFAULT_WIDTH + additionalWidth);
        setHeight(DEFAULT_HEIGHT + additionalHeight);
        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);

        posx = x;
        posy = y;
        texts.add(new BlockText(this, functionName, 10 + X_OFFSET, additionalHeight / 2, Color.WHITE));
        polygons = makePolygon();

        setLocation(posx, posy);
    }

    @Override
    public void beforeGenerate() {
        super.beforeGenerate();

        DEFAULT_WIDTH = 160;
        DEFAULT_HEIGHT = 40;
        additionalHeight = 20;
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = super.makePolygon();
        int xOffset = FUNCTION_NAME_LENGTH + X_OFFSET;

        // Make parameter polygons
        for (int i = 0; i < parameters.size(); i++) {
            // Param width = 80
            int[] pxs = { 10, 90, 90, 10, 10, 20, 20, 10 };
            int[] pys = { 10, 10, 50, 50, 40, 40, 20, 20 };
            BlockPolygon bolygon = new BlockPolygon(this, pxs, pys, Color.WHITE);
            bolygon.stretchHorizontaly(-JParameter.DEFAULT_WIDTH);
            bolygon.stretchVertically(-JParameter.DEFAULT_HEIGHT);

            // Offset
            bolygon.xOffset = xOffset;

            v.add(bolygon);
            parameters.get(i).bolygon = bolygon;
            xOffset += PARAM_DIST;
        }

        return v;
    }

    @Override
    public void changeParameterSize(int index, int dw, int dh) {
        JParameter now = parameters.get(index);

        // Actual dw and dh
        // int actualDw = Math.max(dw, JParameter.DEFAULT_WIDTH - now.width);
        // int actualDh = Math.max(dh, JParameter.DEFAULT_HEIGHT - now.height);

        // Change this block's parameter size directly
        int actualDw = now.bolygon.stretchHorizontaly(dw);
        int actualDh = now.bolygon.stretchVertically(dh);

        // int actualDw = Math.max(dw, JParameter.DEFAULT_WIDTH - now.width);
        // int actualDh = Math.max(dh, JParameter.DEFAULT_HEIGHT - now.height);

        // Change body size
        polygons.get(0).stretchHorizontaly(actualDw);
        polygons.get(0).stretchVertically(actualDh);

        now.width = now.width + actualDw;
        now.height = now.height + actualDh;

        // And move antoher parameters' pos
        for (int i = index + 1; i < parameters.size(); i++) {
            now = parameters.get(i);

            // Change bolygon pos and gluePoint pos
            now.bolygon.xOffset += actualDw;
            now.bolygon.yOffset += actualDh / 2;
        }

        for (BlockText text : texts) {
            text.setY(text.getY() + actualDh / 2);
        }

        // Propagation
        if (upperParameter != null) {
            upperParameter.outerBlock.changeParameterSize(upperParameter.index, actualDw, actualDh);
        } else if (upperScope != null) {
            upperScope.updateScopeHeight(actualDh);
        }
    }

}
