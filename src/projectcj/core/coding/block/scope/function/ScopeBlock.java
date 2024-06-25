package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public abstract class ScopeBlock extends BlockBase implements ScopableBlock {
    // Global scope
    CodeExecutor global;

    public NormalBlockBase innerBlock = null;

    public ScopeBlock(ScopableBlock scope) {
        upperScope = scope;
    }

    @Override
    public CodeExecutor getGlobal() {
        return global;
    }

    @Override
    public void setGlobal(CodeExecutor global) {
        this.global = global;
    }

    @Override
    public NormalBlockBase getInnerBlock() {
        return innerBlock;
    }

    @Override
    public void setInnerBlock(NormalBlockBase innerBlock) {
        this.innerBlock = innerBlock;
    }

    @Override
    public void runInnerBlock() {
        // Run innerBlocks
        NormalBlockBase now = innerBlock;
        while (now != null) {
            now.run();
            now = now.lowerBlock;
        }
    }
}
