package projectcj.swing.coding.block.builtin.io;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.io.ReadBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JReadBlock extends JFunctionRunnerBlockBase implements JRValue {

    public JReadBlock(Display display) {
        super(display, new Color(0xD6546A), "Read", 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ReadBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JReadBlock(display);
    }
}
