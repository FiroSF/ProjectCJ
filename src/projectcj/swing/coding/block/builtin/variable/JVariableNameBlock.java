package projectcj.swing.coding.block.builtin.variable;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.variable.VariableNameBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JLValue;
import projectcj.swing.coding.block.variable.JRValue;

public class JVariableNameBlock extends JFunctionRunnerBlockBase implements JLValue, JRValue {

    public JVariableNameBlock(Display display) {
        super(display, new Color(0xD6546A), "", 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new VariableNameBlock(scope, blockName);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JVariableNameBlock newv = new JVariableNameBlock(display);
        newv.setInnerText(blockName);
        return newv;
    }
}
