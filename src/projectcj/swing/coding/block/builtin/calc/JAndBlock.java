package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.AndBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JAndBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JAndBlock(Display display) {
        super(display, new Color(0x7AC943), "And", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new AndBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JAndBlock newv = new JAndBlock(display);
        return newv;
    }
}
