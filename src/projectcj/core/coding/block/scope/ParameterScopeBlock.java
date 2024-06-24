package projectcj.core.coding.block.scope;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;

public abstract class ParameterScopeBlock extends ParameterBlockBase implements ScopableBlock {
    // Global scope
    CodeExecutor global;
    NormalBlockBase innerBlock = null;

    public ParameterScopeBlock(ScopableBlock scope) {
        upperScope = scope;
        global = scope.getGlobal();
    }

    @Override
    public void runInnerBlock() {
        // Add function scope
        global.functionCallStack.push(this);

        // Run innerBlocks
        NormalBlockBase now = innerBlock;
        while (now != null) {
            now.run();
            now = now.lowerBlock;
        }

        // Remove function scope
        global.functionCallStack.pop();
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

}
