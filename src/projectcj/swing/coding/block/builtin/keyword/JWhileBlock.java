package projectcj.swing.coding.block.builtin.keyword;

import java.awt.Color;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.keyword.WhileBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JParameterScopeBlock;

public class JWhileBlock extends JParameterScopeBlock {

    public JWhileBlock(Display display) {
        super(display, new Color(0x30455D), "While", 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new WhileBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JWhileBlock(display);
    }
}
