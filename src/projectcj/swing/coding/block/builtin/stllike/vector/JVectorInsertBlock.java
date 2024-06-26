package projectcj.swing.coding.block.builtin.stllike.vector;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.stllike.vector.VectorInsertBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

public class JVectorInsertBlock extends JFunctionRunnerBlockBase {
    public JVectorInsertBlock(Display display) {
        super(display, new Color(0xFFC200), "Vector insert", 3);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VectorInsertBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVectorInsertBlock newv = new JVectorInsertBlock(display);
        return newv;
    }
}
