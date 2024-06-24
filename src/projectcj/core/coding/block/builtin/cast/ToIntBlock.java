package projectcj.core.coding.block.builtin.cast;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ToIntBlock extends ParameterBlockBase {
    public ToIntBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return Integer.parseInt((String) parameters.get(0).run());
    }
}
