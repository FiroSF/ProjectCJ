package projectcj.swing.coding.block.builtin.keyword;

import java.awt.Color;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.keyword.ElseBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.scope.JParameterScopeBlock;

public class JElseBlock extends JParameterScopeBlock {

    public JElseBlock(Display display) {
        // Else block's size is weird, so bname became like this.
        super(display, new Color(0x30455D), "Else      ", 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new ElseBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        return new JElseBlock(display);
    }
}
