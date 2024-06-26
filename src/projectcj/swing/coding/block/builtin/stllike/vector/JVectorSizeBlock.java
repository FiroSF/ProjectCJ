package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorSizeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JVectorSizeBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JVectorSizeBlock(Display display) {
        super(display, new Color(0xFFC200), "Vector size", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorSizeBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVectorSizeBlock newv = new JVectorSizeBlock(display);
        return newv;
    }
}
