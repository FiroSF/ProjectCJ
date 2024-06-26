package projectcj.core.coding.block.builtin.stllike.vector;

import java.util.Vector;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class VectorInsertBlock extends ParameterBlockBase {
    public VectorInsertBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object run() {
        Vector<Object> v = ((Vector<Object>) parameters.get(0).run());
        v.insertElementAt(parameters.get(2).run(), (int) parameters.get(1).run());
        return null;
    }
}
