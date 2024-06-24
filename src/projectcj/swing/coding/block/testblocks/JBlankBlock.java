package projectcj.swing.coding.block.testblocks;

import java.awt.Color;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;

public class JBlankBlock extends JNormalBlockBase {
    private int width;
    private int height;

    public JBlankBlock(Display display, int x, int y, int w, int h) {
        this(display, x, y, w, h, Color.GRAY);
    }

    public JBlankBlock(Display display, int x, int y, int w, int h, Color col) {
        super(display, col);
        width = w;
        height = h;

        // Default pos
        posx = x;
        posy = y;
        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);
        setLocation(posx, posy);

        polygons = makePolygon();
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs = {0, width, width, 0};
        int[] ys = {0, 0, height, height};
        v.add(new BlockPolygon(this, xs, ys, color));

        return v;
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return null;
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JBlankBlock(display, posx, posy, width, height);
    }
}
