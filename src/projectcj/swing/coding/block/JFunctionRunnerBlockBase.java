package projectcj.swing.coding.block;

import java.awt.Color;
import java.util.Vector;

import projectcj.swing.coding.Display;

public abstract class JFunctionRunnerBlockBase extends JParameterBlockBase {
    public JFunctionRunnerBlockBase(Display display, Color color, String fname, int paramCount) {
        super(display, color, fname, paramCount);
    }

    public JFunctionRunnerBlockBase(Display display, Color color, String fname, Vector<Integer> paramV) {
        super(display, color, fname, paramV);
    }

}
