package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.LteBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JLteBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JLteBlock(Display display) {
        super(display, new Color(0x7AC943), "Lte", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new LteBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JLteBlock newv = new JLteBlock(display);
        return newv;
    }
}
