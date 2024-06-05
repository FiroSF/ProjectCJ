package projectcj.swing.coding.block.special;

import java.awt.Point;

import projectcj.swing.coding.block.JBlockBase;

public class GluePoint {
    // Types
    public static final int NORMAL_BLOCK = 1;
    public static final int SCOPE_BLOCK = 2;
    public static final int RVALUE_BLOCK = NORMAL_BLOCK | 4;
    public static final int LVALUE_BLOCK = NORMAL_BLOCK | 8;
    public static final int RVALUE_BLOCK_TYPE = 4;
    public static final int LVALUE_BLOCK_TYPE = 8;
    public static final int ALL = 2147483647;

    // GluePoint types
    public static final int NORMAL_LOWER = 1024;
    public static final int SCOPE_INNER = 2048;
    public static final int PARAMETER = 4096;
    public static final int NORMAL_LOWER_CHECK = NORMAL_BLOCK | SCOPE_BLOCK | RVALUE_BLOCK_TYPE | 1024;
    public static final int SCOPE_INNER_CHECK = NORMAL_BLOCK | SCOPE_BLOCK | RVALUE_BLOCK_TYPE | 2048;
    public static final int PARAMETER_CHECK = RVALUE_BLOCK_TYPE | 4096;

    // Variables
    Point point = null;
    JBlockBase parent = null;
    int type = ALL; // All blocks

    public JBlockBase getParent() {
        return parent;
    }

    public int getType() {
        return type;
    }

    /**
     * Translate point to appropriate point, and return
     * 
     * @return Point object which refers to glue point
     */
    public Point getPoint() {
        // Point realPoint = new Point((int) point.getX() + parent.getX(), (int)
        // point.getY() + parent.getY());
        Point realPoint = new Point(point);
        realPoint.translate(parent.getX(), parent.getY());
        return realPoint;
    }

    public GluePoint(JBlockBase parent, int x, int y, int type) {
        this(parent, new Point(x, y), type);
    }

    public GluePoint(JBlockBase parent, Point point, int type) {
        this.parent = parent;
        this.point = point;
        this.type = type;
    }
}
