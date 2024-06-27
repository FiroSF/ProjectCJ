package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.builtin.keyword.BreakSignal;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ReturnBlock extends ParameterBlockBase {
    public ReturnBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        // At start function
        if (upperScope.getGlobal().functionCallStack.empty()) {
            return new BreakSignal();
        }

        // Save return value to functionCallStack
        if (parameters.get(0) == null) {
            upperScope.getGlobal().functionCallStack.peek().returnValue = 0;
        } else {
            upperScope.getGlobal().functionCallStack.peek().returnValue = parameters.get(0).run();

        }
        return null;
    }
}
