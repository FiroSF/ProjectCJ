package projectcj.swing.coding.block.builtin.io;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.io.WriteBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

/**
 * Print function.
 * 
 * <p>
 * This function can have multiple parameters (like *args).
 */
public class JWriteBlock extends JFunctionRunnerBlockBase {
    public JWriteBlock(Display display) {
        super(display, new Color(0xFFC200), "Write", 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new WriteBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JWriteBlock(display);
    }
}
