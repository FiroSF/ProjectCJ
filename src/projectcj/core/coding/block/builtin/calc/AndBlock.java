package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class AndBlock extends ParameterBlockBase {
    public AndBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return trueCheck(parameters.get(0).run()) && trueCheck(parameters.get(1).run());
    }
}
