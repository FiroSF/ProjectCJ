package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class LtBlock extends ParameterBlockBase {
    public LtBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @SuppressWarnings({"rawtypes", "unchecked"}) // Is this ok...?
    @Override
    public Object run() {
        return ((Comparable) parameters.get(0).run()).compareTo(parameters.get(1).run()) < 0;
    }
}
