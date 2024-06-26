package projectcj.core.coding.block.builtin.variable;

import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.FunctionStackObj;
import projectcj.core.coding.block.variable.LValue;

public class VariableNameBlock extends ParameterBlockBase implements LValue {
    public Object text;
    public boolean isConstNumber = false;

    public VariableNameBlock(ScopableBlock scope, String text) {
        this.upperScope = scope;
        this.text = text;

        try {
            // When it's const integer
            this.text = (Integer) Integer.parseInt(text);
            isConstNumber = true;
            return;
        } catch (NumberFormatException e) {
        }

        try {
            // When it's const double
            this.text = (Double) Double.parseDouble(text);
            isConstNumber = true;
            return;
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public Object run() {
        if (isConstNumber) {
            return text;
        }

        if (upperScope.getGlobal().functionCallStack.empty()
                || this.upperScope.getGlobal().functionCallStack.peek() == null) {
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
            this.upperScope.getGlobal().variables.put((String) text, value);
        } else {
            // In function
            FunctionStackObj functionStack = this.upperScope.getGlobal().functionCallStack.peek();
            functionStack.variables.put((String) text, value);
        }
    }
}
