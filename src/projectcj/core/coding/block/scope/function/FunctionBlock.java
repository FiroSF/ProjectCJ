package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.block.scope.ParameterScopeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class FunctionBlock extends ParameterScopeBlock {
    public String fname;

    public FunctionBlock(ScopableBlock scope, String functionName) {
        super(scope);
        this.fname = functionName;
    }

    @Override
    public Object run() {
        // Just run inner block.
        runInnerBlock();

        return global.functionCallStack.peek().returnValue;
    }
}
