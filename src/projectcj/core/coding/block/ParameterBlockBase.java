package projectcj.core.coding.block;

import java.util.Vector;

public abstract class ParameterBlockBase extends NormalBlockBase {
    // Parameters
    public Vector<NormalBlockBase> parameters = new Vector<>();

    public boolean trueCheck(Object tar) {
        return !(tar.equals(0) || tar.equals(false) || tar.equals(null) || tar.equals(""));
    }

}
