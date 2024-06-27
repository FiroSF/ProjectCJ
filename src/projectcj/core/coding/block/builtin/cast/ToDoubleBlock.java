package projectcj.core.coding.block.builtin.cast;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ToDoubleBlock extends ParameterBlockBase {
    public ToDoubleBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        Object res = parameters.get(0).run();
        if (res instanceof Integer) {
            Double tmp = (double) ((Integer) res).intValue();
            return tmp;
        }

        return Double.parseDouble((String) res);
    }
}
