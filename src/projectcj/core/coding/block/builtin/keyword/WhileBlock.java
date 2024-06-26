package projectcj.core.coding.block.builtin.keyword;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ParameterScopeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class WhileBlock extends ParameterScopeBlock {
    CodeExecutor global;

    public WhileBlock(ScopableBlock scope) {
        super(scope);
    }

    @Override
    public Object run() {
        while (true) {
            NormalBlockBase param = parameters.get(0);
            if (param == null) {
                return null;
            }

            Object tar = param.run();
            if (tar == null) {
                return null;
            }

            if (trueCheck(tar)) {
                // Break
                if (runInnerBlock() instanceof BreakSignal) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }
}
