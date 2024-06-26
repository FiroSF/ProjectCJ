package projectcj.core.coding.block.builtin.calc.assign;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.variable.LValue;

public class AssignBlock extends ParameterBlockBase {

    public AssignBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        ((LValue) parameters.get(0)).assign(parameters.get(1).run());
        return null;
    }
}
