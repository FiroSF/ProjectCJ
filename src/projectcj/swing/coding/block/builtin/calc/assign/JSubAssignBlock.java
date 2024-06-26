package projectcj.swing.coding.block.builtin.calc.assign;

import java.awt.Color;
import java.util.Vector;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.builtin.calc.assign.SubAssignBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;

public class JSubAssignBlock extends JFunctionRunnerBlockBase {
    private static Vector<Integer> paramVGen() {
        Vector<Integer> paramV = new Vector<>();
        paramV.add(3);
        paramV.add(1);
        return paramV;
    }

    public JSubAssignBlock(Display display) {
        super(display, new Color(0x3FA9F5), "Sub assign", paramVGen());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new SubAssignBlock(scope);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JSubAssignBlock newv = new JSubAssignBlock(display);
        return newv;
    }
}
