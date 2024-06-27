package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.SplitBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JSplitBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JSplitBlock(Display display) {
        super(display, new Color(0xFFC200), "Split", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new SplitBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JSplitBlock newv = new JSplitBlock(display);
        return newv;
    }
}
