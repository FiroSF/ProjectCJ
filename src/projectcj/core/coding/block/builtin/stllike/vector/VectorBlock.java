package projectcj.core.coding.block.builtin.stllike.vector;

import java.util.Vector;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class VectorBlock extends ParameterBlockBase {
    public VectorBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        Vector<Object> v = new Vector<Object>();

        if (parameters.get(0) != null) {
            int siz = (int) parameters.get(0).run();
            while (v.size() < siz)
                v.add(null);
        }

        return v;
    }
}
