package projectcj.swing.coding.block.builtin.calc.arithmetic;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.arithmetic.MulBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JMulBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JMulBlock(Display display) {
        super(display, new Color(0xFFC200), "Mul", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new MulBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JMulBlock newv = new JMulBlock(display);
        return newv;
    }
}
