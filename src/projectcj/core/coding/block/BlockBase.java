package projectcj.core.coding.block;

import projectcj.core.coding.block.scope.ScopableBlock;

public abstract class BlockBase {
    // Upper scope block.
    public ScopableBlock upperScope = null;

    public abstract void run();
}
