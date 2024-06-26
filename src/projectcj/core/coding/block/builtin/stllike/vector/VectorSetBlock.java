package projectcj.core.coding.block.builtin.stllike.vector;

import java.util.Vector;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class VectorSetBlock extends ParameterBlockBase {
    public VectorSetBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object run() {
        Vector<Object> v = ((Vector<Object>) parameters.get(0).run());
        return v.set((int) parameters.get(1).run(), parameters.get(2).run());
    }
}
