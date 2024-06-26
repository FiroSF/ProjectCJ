package projectcj.core.coding.block.scope.function;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.builtin.variable.VariableNameBlock;
import projectcj.core.coding.block.scope.ScopableBlock;

public class FunctionRunnerBlock extends ParameterBlockBase {
    public String fname;

    public FunctionRunnerBlock(ScopableBlock scope, String functionName) {
        this.upperScope = scope;
        this.fname = functionName;
    }

    @Override
    public Object run() {
        // Get function body
        FunctionBlock function = upperScope.getGlobal().getFunction(fname);
        FunctionStackObj stackObj = new FunctionStackObj();

        // Parameters
        for (int i = 0; i < parameters.size(); i++) {
            VariableNameBlock bodyParam = (VariableNameBlock) function.parameters.get(i);
            NormalBlockBase runnerParam = parameters.get(i);

            stackObj.variables.put((String) bodyParam.text, runnerParam.run());
        }

        // Stack function
        upperScope.getGlobal().functionCallStack.add(stackObj);

        // Run
        Object res = function.run();

        // Pop
        upperScope.getGlobal().functionCallStack.pop();

        return res;
    }
}
