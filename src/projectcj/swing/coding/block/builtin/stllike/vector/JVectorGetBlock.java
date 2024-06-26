package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorGetBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JVectorGetBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JVectorGetBlock(Display display) {
        super(display, new Color(0xFFC200), "Vector get", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorGetBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVectorGetBlock newv = new JVectorGetBlock(display);
        return newv;
    }
}
