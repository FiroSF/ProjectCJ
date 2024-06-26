package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.EqBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JEqBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JEqBlock(Display display) {
        super(display, new Color(0x7AC943), "Eq", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new EqBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JEqBlock newv = new JEqBlock(display);
        return newv;
    }
}
