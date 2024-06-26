package projectcj.swing.coding.block.builtin.keyword;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.keyword.BreakBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

public class JBreakBlock extends JFunctionRunnerBlockBase {

    public JBreakBlock(Display display) {
        super(display, new Color(0xD6546A), "Break", 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new BreakBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JBreakBlock(display);
    }
}
