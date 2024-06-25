package projectcj.core.coding.block.builtin.variable;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.FunctionStackObj;
import projectcj.core.coding.block.variable.LValue;

public class VariableNameBlock extends ParameterBlockBase implements LValue {
    public String text;

    public VariableNameBlock(ScopableBlock scope, String text) {
        this.upperScope = scope;
        this.text = text;
    }

    @Override
    public Object run() {
        if (upperScope.getGlobal().functionCallStack.empty()) {
            // In global
            return this.upperScope.getGlobal().variables.get(text);
        } else {
            // In function
            FunctionStackObj functionStack = this.upperScope.getGlobal().functionCallStack.peek();
            return functionStack.variables.get(text);
        }
    }

    @Override
    public void assign(Object value) {
        if (upperScope.getGlobal().functionCallStack.empty()) {
            // In global
            this.upperScope.getGlobal().variables.put(text, value);
        } else {
            // In function
            FunctionStackObj functionStack = this.upperScope.getGlobal().functionCallStack.peek();
            functionStack.variables.put(text, value);
        }
    }
}
