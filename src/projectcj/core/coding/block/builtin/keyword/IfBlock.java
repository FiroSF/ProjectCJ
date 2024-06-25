package projectcj.core.coding.block.builtin.keyword;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ParameterScopeBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class IfBlock extends ParameterScopeBlock {
    public IfBlock(ScopableBlock scope) {
        super(scope);
    }

    @Override
    public Object run() {
        NormalBlockBase param = parameters.get(0);
        if (param == null) {
            return null;
        }

        Object tar = param.run();
        if (tar == null) {
            return null;
        }

        // Falsy values
        if (!(tar.equals(0) || tar.equals(false) || tar.equals(null) || tar.equals(""))) {
            runInnerBlock();
        }
        return null;
    }
}
