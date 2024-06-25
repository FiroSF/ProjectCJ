package projectcj.swing.coding.block.scope.function;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.FunctionBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JParameterScopeBlock;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.JParameter;
import projectcj.swing.coding.otherui.FunctionData;

public class JFunctionBlock extends JParameterScopeBlock {
    FunctionData functionData;

    public JFunctionBlock(Display display) {
        super(display, new Color(0x30455D), "", 1);
        // X_OFFSET += 60;
    }

    @Override
    public void beforeGenerate() {
        super.beforeGenerate();
        X_OFFSET += 60;
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        Vector<BlockPolygon> v = new Vector<>();
        int[] xs2 = { 0, 60, 70, 130 };
        int[] ys2 = { 40, 0, 0, 40 };
        v.add(new BlockPolygon(this, xs2, ys2, new Color(0x3B5168)));

        int[] xs = { 150, 0, 0, 150, 150, 10, 10, 150 };
        int[] ys = { 40, 40, 100, 100, 90, 90, 50, 50 };
        v.add(new BlockPolygon(this, xs, ys, color));

        v.get(1).stretchVertically(-JParameterScopeBlock.INNER_DEFAULT_HEIGHT);
        v.get(0).stretchHorizontaly(-50 + X_OFFSET);
        v.get(1).stretchHorizontaly(-50 + X_OFFSET);
        // makeParameters(v);

        // if (parameters.size() > 0) {
        // v.get(0).stretchVertically(20);
        // v.get(1).moveDelta(0, 20);
        // // gluePoints.get(0).moveDelta(0, 20);
        // }

        return v;

        // Vector<BlockPolygon> v = new Vector<>();

        // int[] xs2 = { 0, 40, MINIMUM_WIDTH, MINIMUM_WIDTH + 40 };
        // int[] ys2 = { 40, 0, 0, 40 };
        // v.add(new BlockPolygon(this, xs2, ys2, new Color(0x3B5168)));

        // int[] xs = { MINIMUM_WIDTH + 60, 0, 0, MINIMUM_WIDTH + 60, MINIMUM_WIDTH +
        // 60, 10, 10, MINIMUM_WIDTH + 60 };
        // int[] ys = { 40, 40, 100, 100, 90, 90, 50, 50 };
        // v.add(new BlockPolygon(this, xs, ys, color));
        // v.get(1).stretchVertically(-JScopeBlock.INNER_DEFAULT_HEIGHT);

        // return v;
    }

    @Override
    public Point changeParameterSize(int index, int dw, int dh) {
        Point res = super.changeParameterSize(index, dw, dh);

        // When parameters[parameters.size() - 2] parameter is disappeared
        while (parameters.size() > 1 &&
                parameters.get(parameters.size() - 1).innerBlock == null &&
                parameters.get(parameters.size() - 2).innerBlock == null) {
            removeParameter();
            functionData.changeParameterCount(-1);
        }

        // Calc param counts
        int cnt = 0;
        for (JParameter param : parameters) {
            if (param.innerBlock != null) {
                cnt++;
            }
        }

        // Full
        if (parameters.size() == cnt) {
            addParameter();
            functionData.changeParameterCount(1);
        }

        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new FunctionBlock(scope, blockName);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JFunctionBlock blk = new JFunctionBlock(display);
        blk.setInnerText(blockName);

        display.addFunction(blk);

        return blk;
    }

    public void setFunctionData(FunctionData functionData) {
        this.functionData = functionData;
    }
}
