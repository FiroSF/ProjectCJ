package projectcj.swing.coding.block.builtin.string;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.string.ToStringBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JToStringBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JToStringBlock(Display display) {
        super(display, new Color(0xFFC200), "To string", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ToStringBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JToStringBlock newv = new JToStringBlock(display);
        return newv;
    }
}
