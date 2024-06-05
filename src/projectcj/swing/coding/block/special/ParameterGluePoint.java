package projectcj.swing.coding.block.special;

import java.awt.Point;

import projectcj.swing.coding.block.JBlockBase;

public class ParameterGluePoint extends GluePoint {
    JParameter parentParam;

    public JParameter getParentParam() {
        return parentParam;
    }

    public void setParentParam(JParameter parentParam) {
        this.parentParam = parentParam;
    }

    public ParameterGluePoint(JBlockBase parent, int x, int y, int type) {
        this(parent, new Point(x, y), type);
    }

    public ParameterGluePoint(JBlockBase parent, Point point, int type) {
        super(parent, point, type);
    }
}
