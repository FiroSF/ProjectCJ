package projectcj.core.coding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import projectcj.core.coding.block.scope.function.FunctionBlock;
import projectcj.core.coding.block.scope.function.FunctionStackObj;
import projectcj.core.coding.block.scope.function.StartBlock;

public class CodeExecutor {
    public Stack<FunctionStackObj> functionCallStack = new Stack<>();
    public HashMap<String, Object> variables = new HashMap<>();
    public HashMap<String, FunctionBlock> functions = new HashMap<>();
    public BufferedReader ins;
    public BufferedWriter outs;
    StartBlock startBlock;
    Thread process;

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

        Runnable codeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Run start");
                    outs.write("=== Program start ===\n");
                    outs.flush();

                    startBlock.run();

                    System.out.println("Run end");
                    outs.write("=== Program end ===\n");
                    outs.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        process = new Thread(codeRunnable);
        process.start();
    }

    public void stop() {
        process.interrupt();
    }

    public FunctionBlock getFunction(String fname) {
        return functions.get(fname);
    }
}
