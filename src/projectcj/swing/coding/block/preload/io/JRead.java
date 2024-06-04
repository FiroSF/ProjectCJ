package projectcj.swing.coding.block.preload.io;

import java.util.Vector;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.variable.JRValue;

public class JRead extends JNormalBlockBase implements JRValue {

    public JRead(Display display) {
        super(display);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Vector<BlockPolygon> makePolygon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makePolygon'");
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoreClassObj'");
    }

}
