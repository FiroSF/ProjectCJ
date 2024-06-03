package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.scope.ScopableBlock;

public class StartBlock extends ScopeBlock {
    CodeExecutor global;

    public StartBlock(ScopableBlock scope) {
        super(scope);
    }

    @Override
    public void run() {
        // Just run inner block.
        runInnerBlock();
    }
}
