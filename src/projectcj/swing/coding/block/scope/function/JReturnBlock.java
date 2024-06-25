package projectcj.swing.coding.block.scope.function;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.ReturnBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

public class JReturnBlock extends JFunctionRunnerBlockBase {
    public JReturnBlock(Display display) {
        super(display, new Color(0xD6546A), "Return", 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ReturnBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JReturnBlock(display);
    }
}
