package projectcj.swing.coding.block.testblocks;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JParameterScopeBlock;

public class JBlankScopeBlock extends JParameterScopeBlock {
    public JBlankScopeBlock(Display display) {
        this(display, Color.GRAY);
    }

    public JBlankScopeBlock(Display display, Color col) {
        // super(display, col, "", 1, x, y);
        super(display, col, "Some name of block", 0);

        // setWidth(DEFAULT_WIDTH);
        // setHeight(DEFAULT_HEIGHT);

        // Default pos
        // posx = x;
        // posy = y;
        // polygons = makePolygon();

        // setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);
        setLocation(posx, posy);
        // setInnerText("Some name of block");
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return null;
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JBlankScopeBlock(display);
    }
}
