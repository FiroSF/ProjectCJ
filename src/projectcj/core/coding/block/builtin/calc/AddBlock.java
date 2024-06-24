package projectcj.core.coding.block.builtin.calc;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class AddBlock extends ParameterBlockBase {
    public AddBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        NormalBlockBase param1 = parameters.get(0);
        NormalBlockBase param2 = parameters.get(1);
        Object tar1 = param1.run();
        Object tar2 = param2.run();


        if (tar1 instanceof String) {
            return (String) tar1 + (String) tar2;
        }

        if (tar1 instanceof Integer) {
            return (int) tar1 + (int) tar2;
        }

        if (tar1 instanceof Double) {
            return (double) tar1 + (double) tar2;
        }

        return null;
    }
}
