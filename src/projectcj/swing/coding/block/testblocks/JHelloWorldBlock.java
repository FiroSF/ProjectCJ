package projectcj.swing.coding.block.testblocks;

import java.awt.Color;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.core.coding.block.testblocks.HelloWorldBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;

public class JHelloWorldBlock extends JNormalBlockBase {
    public String hw;

    public JHelloWorldBlock(Display display, String text) {
        super(display, new Color(0xFFC200));
        hw = text;

        setWidth(DEFAULT_WIDTH);
        setHeight(DEFAULT_HEIGHT);
        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);

        // Default pos
        // posx = 50;
        // posy = 500;
        polygons = makePolygon();

        setInnerText(hw);
        // setLocation(posx, posy);
    }

    @Override
    public void beforeGenerate() {
        super.beforeGenerate();
        DEFAULT_WIDTH = 150;
        DEFAULT_HEIGHT = 40;
    }

    public JHelloWorldBlock(Display display) {
        this(display, "Hello world!");
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs = { 0, getCalcedWidth(), getCalcedWidth(), 0 };
        int[] ys = { 0, 0, getCalcedHeight(), getCalcedHeight() };
        v.add(new BlockPolygon(this, xs, ys));

        // texts.addElement(new BlockText(this, hw, 10, 0, Color.WHITE));

        return v;
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        return (T) new HelloWorldBlock(scope, hw);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JHelloWorldBlock(display, hw);
    }
}
