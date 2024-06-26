package projectcj.core.coding.block.scope;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.NormalBlockBase;

public interface ScopableBlock {
    /**
     * Just run scope's inner blocks.
     * 
     * @return BreakSignal if break is called
     */
    Object runInnerBlock();

    public CodeExecutor getGlobal();

    public void setGlobal(CodeExecutor global);

    /**
     * Return inner block.
     * 
     * @return Inner block of this scope
     */
    public NormalBlockBase getInnerBlock();

    /**
     * Set inner block.
     * 
     */
    public void setInnerBlock(NormalBlockBase innerBlock);
}
