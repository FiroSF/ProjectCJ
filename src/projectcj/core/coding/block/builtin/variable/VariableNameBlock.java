package projectcj.core.coding.block.builtin.variable;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.variable.LValue;

public class VariableNameBlock extends ParameterBlockBase implements LValue {
    public String text;

    public VariableNameBlock(ScopableBlock scope, String text) {
        this.upperScope = scope;
        this.text = text;
    }

    @Override
    public Object run() {
        return this.upperScope.getGlobal().variables.get(text);
    }

    @Override
    public void assign(Object value) {
        this.upperScope.getGlobal().variables.put(text, value);
    }
}
