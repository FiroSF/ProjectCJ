package projectcj.core.coding.block;

public abstract class NormalBlockBase extends BlockBase {
    // Block connected to lower part.
    // This block object will be JNormalBlockBase object or null.
    public NormalBlockBase lowerBlock = null;

    // Block connected to upper part.
    // This block object will be BlockBase object or null.
    public NormalBlockBase upperBlock = null;

}
