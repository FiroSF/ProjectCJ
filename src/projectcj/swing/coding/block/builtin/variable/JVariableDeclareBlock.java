package projectcj.swing.coding.block.builtin.variable;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.variable.VariableDeclareBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

public class JVariableDeclareBlock extends JFunctionRunnerBlockBase {
    public JVariableDeclareBlock(Display display) {
        super(display, new Color(0x3FA9F5), "int", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VariableDeclareBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVariableDeclareBlock newv = new JVariableDeclareBlock(display);
        newv.setInnerText(blockName);
        return newv;
    }
}
