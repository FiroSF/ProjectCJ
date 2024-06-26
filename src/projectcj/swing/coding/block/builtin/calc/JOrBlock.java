package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.OrBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JOrBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JOrBlock(Display display) {
        super(display, new Color(0x7AC943), "Or", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new OrBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JOrBlock newv = new JOrBlock(display);
        return newv;
    }
}
