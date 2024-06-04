package projectcj.swing.coding.block;

import java.awt.Color;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.BlockText;
import projectcj.swing.coding.block.special.JParameter;

public abstract class JFunctionRunnerBlockBase extends JParameterBlockBase {
    public String functionName = "";
    public int FUNCTION_NAME_LENGTH = 0;

    public JFunctionRunnerBlockBase(Display display, String fname, int paramCount, int x, int y) {
        super(display);

        functionName = fname;
        // TODO: name length part
        FUNCTION_NAME_LENGTH = functionName.length() * 20;
        for (int i = 0; i < paramCount; i++) {
            parameters.add(new JParameter());
        }

        setWidth(DEFAULT_WIDTH + additionalWidth);
        setHeight(DEFAULT_HEIGHT + additionalHeight);
        setSize(DEFAULT_WIDTH + additionalWidth, DEFAULT_HEIGHT + additionalHeight);

        posx = x;
        posy = y;
        texts.add(new BlockText(this, functionName, 10, additionalHeight / 2, Color.WHITE));
        polygons = makePolygon();

        setLocation(posx, posy);
    }

    @Override
    public void beforeGenerate() {
        super.beforeGenerate();

        DEFAULT_WIDTH = 200;
        DEFAULT_HEIGHT = 40;
        additionalHeight = 20;
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int xoffset = FUNCTION_NAME_LENGTH;

        // Make block polygon
        int[] xs = { 0, getWidth(), getWidth(), 0 };
        int[] ys = { 0, 0, getHeight(), getHeight() };
        v.add(new BlockPolygon(this, xs, ys, new Color(0xD6546A)));

        // Make parameter polygons
        for (int i = 0; i < parameters.size(); i++) {
            int[] pxs = { 10, 90, 90, 10, 10, 20, 20, 10 };
            int[] pys = { 10, 10, 50, 50, 40, 40, 20, 20 };

            // Offset
            for (int j = 0; j < pxs.length; j++) {
                pxs[j] += xoffset;
            }

            v.add(new BlockPolygon(this, pxs, pys, Color.WHITE));
            xoffset += 100;
        }

        return v;
    }

    @Override
    public void changeParameter(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeParameter'");
    }
}
