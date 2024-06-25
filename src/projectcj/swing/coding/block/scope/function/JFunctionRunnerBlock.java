package projectcj.swing.coding.block.scope.function;

import java.awt.Color;

import projectcj.core.coding.block.BlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.FunctionRunnerBlock;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JFunctionRunnerBlockBase;
import projectcj.swing.coding.block.variable.JRValue;
import projectcj.swing.coding.otherui.FunctionData;

public class JFunctionRunnerBlock extends JFunctionRunnerBlockBase implements JRValue {
    JFunctionBlock functionBlock;
    FunctionData functionData;

    public JFunctionRunnerBlock(JFunctionBlock f, FunctionData fd) {
        super(f.display, new Color(0xFA924C), f.blockName, f.parameters.size() - 1);
        functionBlock = f;
        functionData = fd;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockBase> T getCoreClassObj(ScopableBlock scope) {
        return (T) new FunctionRunnerBlock(scope, blockName);
    }

    @Override
    protected JBlockBase instantiateMe() {
        JFunctionRunnerBlock runnerBlock = new JFunctionRunnerBlock(functionBlock, functionData);
        functionData.addRunner(runnerBlock);
        return runnerBlock;
    }

}
