package projectcj.swing.coding.block.testblocks;

import java.awt.Color;
import java.util.Vector;

import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;

public class JHelloWorldBlock extends JNormalBlockBase {
    public JHelloWorldBlock(Display display) {
        super(display);

        setWidth(200);
        setHeight(50);

        // Default pos
        posx = 50;
        posy = 500;
        polygons = makePolygon();

        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);
        setLocation(posx, posy);

        for (BlockPolygon p : polygons) {
            p.setColor(new Color(0xFFC200));
        }
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs = { 0, getWidth(), getWidth(), 0 };
        int[] ys = { 0, 0, getHeight(), getHeight() };
        v.add(new BlockPolygon(this, xs, ys));

        texts.addElement(new BlockText(this, "Hello world!", 10, 0, Color.WHITE));

        return v;
    }
}
