package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.LtBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JLtBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JLtBlock(Display display) {
        super(display, new Color(0x7AC943), "Lt", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new LtBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JLtBlock newv = new JLtBlock(display);
        return newv;
    }
}
