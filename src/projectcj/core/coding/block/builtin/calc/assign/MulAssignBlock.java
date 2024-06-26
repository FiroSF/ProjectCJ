package projectcj.core.coding.block.builtin.calc.assign;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.variable.LValue;

public class MulAssignBlock extends ParameterBlockBase {

    public MulAssignBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        NormalBlockBase param1 = parameters.get(0);
        NormalBlockBase param2 = parameters.get(1);
        Object tar1 = param1.run();
        Object tar2 = param2.run();

        Object res = null;

        if (tar1 instanceof Integer && tar2 instanceof Integer) {
            res = (int) tar1 * (int) tar2;
        }

        if (tar1 instanceof Double && tar2 instanceof Integer) {
            res = (double) tar1 * (int) tar2;
        }

        if (tar1 instanceof Integer && tar2 instanceof Double) {
            res = (int) tar1 * (double) tar2;
        }

        if (tar1 instanceof Double && tar2 instanceof Double) {
            res = (double) tar1 * (double) tar2;
        }

        ((LValue) parameters.get(0)).assign(res);
        return null;
    }
}
