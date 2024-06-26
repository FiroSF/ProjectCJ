package projectcj.core.coding.block.builtin.keyword;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ParameterScopeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class IfBlock extends ParameterScopeBlock {
    // Should else be run?
    public boolean success = true;

    public IfBlock(ScopableBlock scope) {
        super(scope);
    }

    @Override
    public Object run() {
        success = true;
        NormalBlockBase param = parameters.get(0);

        if (param == null) {
            success = false;
            return null;
        }

        Object tar = param.run();
        if (tar == null) {
            success = false;
            return null;
        }

        // Falsy values
        if (trueCheck(tar)) {
            return runInnerBlock();
        } else {
            success = false;
        }

        return null;
    }
}
