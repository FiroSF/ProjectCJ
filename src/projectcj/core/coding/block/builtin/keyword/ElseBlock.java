package projectcj.core.coding.block.builtin.keyword;

import projectcj.core.coding.block.scope.ParameterScopeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ElseBlock extends ParameterScopeBlock {
    // Will be set at compiler
    public IfBlock ifBlock;

    public ElseBlock(ScopableBlock scope) {
        super(scope);
    }

    @Override
    public Object run() {
        if (ifBlock != null && !ifBlock.success) {
            runInnerBlock();
            ifBlock.success = true;
        }
        return null;
    }
}
