package projectcj.core.coding.block.builtin.cast;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ToDoubleBlock extends ParameterBlockBase {
    public ToDoubleBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return Double.parseDouble((String) parameters.get(0).run());
    }
}
