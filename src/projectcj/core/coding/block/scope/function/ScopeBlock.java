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

    public CodeExecutor getGlobal() {
        return global;
    }

    public void setGlobal(CodeExecutor global) {
        this.global = global;
    }

    @Override
    public void runInnerBlock() {
        // Add function scope
        global.functionCallStack.push(this);

        // Run innerBlocks
        NormalBlockBase now = innerBlock;
        if (now != null) {
            now.run();
            now = now.lowerBlock;
        }

        // Remove function scope
        global.functionCallStack.pop();
    }
}
