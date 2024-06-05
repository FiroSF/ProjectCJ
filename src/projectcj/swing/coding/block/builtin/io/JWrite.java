package projectcj.swing.coding.block.builtin.io;

import java.awt.Color;
import java.util.Vector;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.JParameter;

/**
 * Print function.
 * 
 * <p>
 * This function can have multiple parameters (like *args).
 */
public class JWrite extends JFunctionRunnerBlockBase {
    public JWrite(Display display) {
        super(display, new Color(0xFFC200), "Write", 1, 500, 0);
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        throw new UnsupportedOperationException("Unimplemented method 'getCoreClassObj'");
    }

}
