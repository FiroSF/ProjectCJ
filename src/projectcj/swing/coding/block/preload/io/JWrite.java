package projectcj.swing.coding.block.preload.io;

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
        super(display, "Write", 1, 500, 0);
    }

    @Override
    public void changeParameter(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeParameter'");
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        throw new UnsupportedOperationException("Unimplemented method 'getCoreClassObj'");
    }

}
