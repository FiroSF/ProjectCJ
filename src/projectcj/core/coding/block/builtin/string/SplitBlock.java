package projectcj.core.coding.block.builtin.string;

import java.util.Arrays;
import java.util.Vector;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class SplitBlock extends ParameterBlockBase {
    public SplitBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        String[] splitRes = ((String) parameters.get(0).run()).split((String) parameters.get(1).run());
        return new Vector<String>(Arrays.asList(splitRes));
    }
}
