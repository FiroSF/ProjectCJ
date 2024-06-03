package projectcj.core.coding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Stack;

import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.StartBlock;

public class CodeExecutor {
    public Stack<ScopableBlock> functionCallStack = new Stack<>();
    public BufferedReader ins;
    public BufferedWriter outs;
    StartBlock startBlock;

    public void setStartBlock(StartBlock startBlock) {
        this.startBlock = startBlock;
    }

    public CodeExecutor(BufferedReader inputReader, BufferedWriter outputReader) {
        ins = inputReader;
        outs = outputReader;
    }

    public void run() throws Exception {
        if (startBlock == null) {
            throw new Exception("There is no start block!");
        }

        System.out.println("Run start");
        startBlock.run();
        System.out.println("Run end");
    }
}
