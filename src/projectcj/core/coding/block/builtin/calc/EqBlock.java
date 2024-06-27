package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class EqBlock extends ParameterBlockBase {
    public EqBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return parameters.get(0).run().equals(parameters.get(1).run());
    }
}
