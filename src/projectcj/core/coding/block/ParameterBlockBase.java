package projectcj.core.coding.block;

import java.util.Vector;

public abstract class ParameterBlockBase extends NormalBlockBase {
    // Parameters
    public Vector<NormalBlockBase> parameters = new Vector<>();

    public boolean trueCheck(Object tar) {
        return !(tar == null || tar.equals(0) || tar.equals(false) || tar.equals(""));
    }

    public boolean trueCheckBeforeRun(NormalBlockBase tar) {
        if (tar == null)
            return false;

        return trueCheck(tar.run());
    }

}
