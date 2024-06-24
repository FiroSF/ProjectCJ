package projectcj.swing.coding.block.builtin;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.LiteralBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JLiteralBlock extends JFunctionRunnerBlockBase implements JRValue {

    public JLiteralBlock(Display display) {
        super(display, new Color(0xD6546A), "\"\"", 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new LiteralBlock(scope, blockName.substring(1, blockName.length() - 1));
    }

    @Override
    protected JBlockBase instantiateMe() {
        JLiteralBlock newv = new JLiteralBlock(display);
        newv.setInnerText(blockName);
        return newv;
    }
}
