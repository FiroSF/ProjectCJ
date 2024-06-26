package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorEraseBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JVectorEraseBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JVectorEraseBlock(Display display) {
        super(display, new Color(0xFFC200), "Vector erase", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorEraseBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVectorEraseBlock newv = new JVectorEraseBlock(display);
        return newv;
    }
}
