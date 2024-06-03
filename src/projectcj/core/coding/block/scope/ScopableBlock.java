package projectcj.core.coding.block.scope;

import projectcj.core.coding.CodeExecutor;

public interface ScopableBlock {
    /**
     * Just run scope's inner blocks.
     */
    void runInnerBlock();

    public CodeExecutor getGlobal();

    public void setGlobal(CodeExecutor global);
}
