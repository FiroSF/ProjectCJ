package projectcj.swing.coding.block.builtin.cast;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.cast.ToDoubleBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JToDoubleBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JToDoubleBlock(Display display) {
        super(display, new Color(0xFFC200), "To double", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ToDoubleBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JToDoubleBlock newv = new JToDoubleBlock(display);
        return newv;
    }
}
