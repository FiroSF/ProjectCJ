package projectcj.core.coding.block.builtin.variable;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class VariableDeclareBlock extends ParameterBlockBase {
    public VariableDeclareBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        this.upperScope.getGlobal().variables.put((String) parameters.get(0).run(), null);
        return null;
    }
}
