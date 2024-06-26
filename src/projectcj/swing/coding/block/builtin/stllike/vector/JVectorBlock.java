package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JVectorBlock extends JFunctionRunnerBlockBase implements JRValue {

    public JVectorBlock(Display display) {
        super(display, new Color(0xD6546A), "New vector", 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JVectorBlock(display);
    }
}
