package projectcj.swing.coding.block.builtin.io;

import java.awt.Color;
import java.util.Vector;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.variable.JRValue;

public class JRead extends JFunctionRunnerBlockBase implements JRValue {

    public JRead(Display display) {
        super(display, new Color(0xD6546A), "Read", 0);
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoreClassObj'");
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JRead(display);
    }
}
