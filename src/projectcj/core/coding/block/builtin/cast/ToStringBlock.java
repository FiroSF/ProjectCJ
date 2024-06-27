package projectcj.core.coding.block.builtin.cast;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ToStringBlock extends ParameterBlockBase {
    public ToStringBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return parameters.get(0).run().toString();
    }
}
