package projectcj.swing.coding.block.builtin.calc.arithmetic;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.arithmetic.DivBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JDivBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JDivBlock(Display display) {
        super(display, new Color(0xFFC200), "Div", 2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new DivBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JDivBlock newv = new JDivBlock(display);
        return newv;
    }
}
