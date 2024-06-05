package projectcj.swing.coding.block;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;

// This class is redundant, should be moved to JNormalBlockBase.
public abstract class JParameterBlockBase extends JNormalBlockBase {
    // When parameter is updated, other parameters' pos may should be modified.
    // So parameter glue points are seperated from others.
    public Vector<JParameter> parameters = new Vector<>();

    public JParameterBlockBase(Display display, Color color) {
        super(display, color);
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

    public abstract void changeParameterSize(int index, int dw, int dh);
}
