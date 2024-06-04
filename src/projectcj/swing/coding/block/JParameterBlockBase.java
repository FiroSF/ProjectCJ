package projectcj.swing.coding.block;

import java.util.Vector;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.swing.coding.Display;
import projectcj.swing.coding.block.special.BlockPolygon;
import projectcj.swing.coding.block.special.GluePoint;
import projectcj.swing.coding.block.special.JParameter;

public abstract class JParameterBlockBase extends JNormalBlockBase {
    public Vector<JParameter> parameters = new Vector<>();

    // When parameter is updated, other parameters' pos may should be modified.
    // So parameter glue points are seperated from others.
    public Vector<GluePoint> parameterGluePoints;

    public JParameterBlockBase(Display display) {
        super(display);
    }

    public abstract void changeParameter(int index);

}
