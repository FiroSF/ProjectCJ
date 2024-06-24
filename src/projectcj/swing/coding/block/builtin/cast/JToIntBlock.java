package projectcj.swing.coding.block.builtin.cast;

import java.awt.Color;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.cast.ToIntBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;

public class JToIntBlock extends JFunctionRunnerBlockBase implements JRValue {
    public JToIntBlock(Display display) {
        super(display, new Color(0xFFC200), "To int", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ToIntBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JToIntBlock newv = new JToIntBlock(display);
        return newv;
    }
}
