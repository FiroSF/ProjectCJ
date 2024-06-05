package projectcj.swing.coding.block.special;

import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JParameter {
    // Default width and height
    public static int DEFAULT_WIDTH = 80;
    public static int DEFAULT_HEIGHT = 40;

    public JRValue innerBlock = null;
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

        glue.setParentParam(this);
    }
}
