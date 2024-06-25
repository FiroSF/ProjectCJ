package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ReturnBlock extends ParameterBlockBase {
    public ReturnBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        // Save return value to functionCallStack
        upperScope.getGlobal().functionCallStack.peek().returnValue = parameters.get(0).run();
        return null;
    }
}
