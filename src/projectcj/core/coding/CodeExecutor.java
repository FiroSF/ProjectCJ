package projectcj.core.coding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    public boolean isInterrupted;

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
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        outs.write("=== Program crashed! ===\n");
                        // https://www.baeldung.com/java-stacktrace-to-string
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        outs.write(sw.toString());
                        outs.write("=== See details at java console ===\n");
                        outs.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        process = new Thread(codeRunnable);
        process.start();
    }

    public void stop() {
        process.interrupt();
        try {
            System.out.println("Program stopped");
            outs.write("=== Program stopped by user ===\n");
            outs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FunctionBlock getFunction(String fname) {
        return functions.get(fname);
    }
}
