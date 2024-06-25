package projectcj.swing.coding.block.special;

import java.awt.Point;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;

public class JParameter {
    // Default width and height
    public static int DEFAULT_WIDTH = 60;
    public static int DEFAULT_HEIGHT = 40;

    public JNormalBlockBase innerBlock = null;
    public JParameterBlockBase outerBlock = null;
    public BlockPolygon bolygon = null;

    public int index = 0;
    public int width = 0;
    public int height = 0;

    // When parameter is updated, other parameters' pos may should be modified.
    // So parameter glue points are seperated from others.
    public ParameterGluePoint gluePoint = null;

    public JParameter(JParameterBlockBase outer, ParameterGluePoint glue, int index) {
        this.index = index;
        outerBlock = outer;
        gluePoint = glue;

        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;

        glue.setParentParam(this);
    }

    /**
     * Move parameter.
     * 
     * @param dx
     * @param dy
     */
    public void moveDelta(int dx, int dy) {
        bolygon.xOffset += dx;
        bolygon.yOffset += dy;
        gluePoint.moveDelta(dx, dy);

        if (innerBlock != null) {
            // JNormalBlockBase blk = ((JNormalBlockBase) innerBlock);
            innerBlock.movePropagation(new Point(innerBlock.posx + dx, innerBlock.posy + dy));
        }
    }
}
