package projectcj.core.coding.block.builtin;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class LiteralBlock extends NormalBlockBase {
    public String text;

    public LiteralBlock(ScopableBlock scope, String text) {
        this.upperScope = scope;
        this.text = text;
    }

    @Override
    public Object run() {
        return text;
    }
}
