package projectcj.swing.coding.block.builtin.calc;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.NotBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JNotBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JNotBlock(Display display) {
        super(display, new Color(0x7AC943), "Not", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new NotBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JNotBlock newv = new JNotBlock(display);
        return newv;
    }
}
