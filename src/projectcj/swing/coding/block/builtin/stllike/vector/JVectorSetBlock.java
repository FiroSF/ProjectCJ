package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorSetBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JVectorSetBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JVectorSetBlock(Display display) {
        super(display, new Color(0xFFC200), "Vector set", 3);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorSetBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVectorSetBlock newv = new JVectorSetBlock(display);
        return newv;
    }
}
