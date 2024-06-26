package projectcj.core.coding.block.builtin.keyword;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class BreakBlock extends NormalBlockBase {
    public BreakBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        return new BreakSignal();
    }
}
