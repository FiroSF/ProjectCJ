package projectcj.swing.coding.block.builtin.calc.arithmetic;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.arithmetic.SubBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JSubBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JSubBlock(Display display) {
        super(display, new Color(0xFFC200), "Sub", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new SubBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JSubBlock newv = new JSubBlock(display);
        return newv;
    }
}
