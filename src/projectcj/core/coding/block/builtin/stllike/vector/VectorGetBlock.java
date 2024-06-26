package projectcj.core.coding.block.builtin.stllike.vector;

import java.util.Vector;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class VectorGetBlock extends ParameterBlockBase {
    public VectorGetBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object run() {
        return ((Vector<Object>) parameters.get(0).run()).get(((int) parameters.get(1).run()));
    }
}
