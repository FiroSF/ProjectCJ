package projectcj.swing.coding.otherui;

import java.util.Vector;

import projectcj.swing.coding.block.scope.function.JFunctionBlock;
import projectcj.swing.coding.block.scope.function.JFunctionRunnerBlock;

public class FunctionData {
    JFunctionBlock functionBlock;
    JFunctionRunnerBlock originalRunner;
    Vector<JFunctionRunnerBlock> runnerBlocks = new Vector<>();

    public FunctionData(JFunctionBlock functionBlock) {
        this.functionBlock = functionBlock;
        originalRunner = new JFunctionRunnerBlock(functionBlock, this);
    }

    public void addRunner(JFunctionRunnerBlock runnerBlock) {
        runnerBlocks.add(runnerBlock);
    }

    public void changeParameterCount(int count) {
        if (count < 0) {
            originalRunner.removeParameter();
        } else {
            originalRunner.addParameter(1);
        }

        for (JFunctionRunnerBlock blk : runnerBlocks) {
            if (count < 0) {
                blk.removeParameter();
            } else {
                blk.addParameter(1);
            }
        }
    }
}
