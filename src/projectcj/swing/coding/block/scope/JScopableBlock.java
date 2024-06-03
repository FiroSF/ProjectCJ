package projectcj.swing.coding.block.scope;

import projectcj.swing.coding.block.JNormalBlockBase;

public interface JScopableBlock {
    /**
     * Return inner block.
     * 
     * @return Inner block of this scope
     */
    public JNormalBlockBase getInnerBlock();

    /**
     * Set inner block.
     * 
     */
    public void setInnerBlock(JNormalBlockBase innerBlock);

    /**
     * When innerBlock is updated, height of block should be changed
     * 
     * @param dh
     */
    public void updateScopeHeight(int dh);
}
