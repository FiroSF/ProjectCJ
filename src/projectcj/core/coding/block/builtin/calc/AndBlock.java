package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class AndBlock extends ParameterBlockBase {
    public AndBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return trueCheckBeforeRun(parameters.get(0)) && trueCheckBeforeRun(parameters.get(1));
    }
}
