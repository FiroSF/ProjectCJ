package projectcj.swing.coding.block.testblocks;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JBlankParamBlock extends JFunctionRunnerBlockBase implements JRValue {
    // private int width;
    // private int height;

    public JBlankParamBlock(Display display, int x, int y, int w, int h) {
        this(display, x, y, w, h, Color.GRAY);
    }

    public JBlankParamBlock(Display display, int x, int y, int w, int h, Color col) {
        super(display, col, "", 1);
        // width = w;
        // height = h;

        // Default pos
        posx = x;
        posy = y;
        updateSize();
        setLocation(posx, posy);

        // polygons = makePolygon();
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return null;
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JBlankParamBlock(display, posx, posy, 0, 0);
    }
}
