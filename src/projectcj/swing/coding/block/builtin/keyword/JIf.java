package projectcj.swing.coding.block.builtin.keyword;

import java.awt.Color;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JParameterScopeBlock;
import projectcj.swing.coding.block.scope.function.JStartBlock;

public class JIf extends JParameterScopeBlock {

    public JIf(Display display) {
        super(display, new Color(0x30455D), "If", 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopeBlock scope) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoreClassObj'");
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JIf(display);
    }
}
