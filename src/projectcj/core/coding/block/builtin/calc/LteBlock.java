package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class LteBlock extends ParameterBlockBase {
    public LteBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @SuppressWarnings({"rawtypes", "unchecked"}) // Is this ok...?
    @Override
    public Object run() {
        return ((Comparable) parameters.get(0).run()).compareTo(parameters.get(1).run()) <= 0;
    }
}
