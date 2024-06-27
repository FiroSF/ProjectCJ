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
    public StartBlock startBlock;
    Thread process;
    public boolean isInterrupted;
    public boolean isRunning = false;

    public void setStartBlock(StartBlock startBlock) {
        this.startBlock = startBlock;
    }

    public CodeExecutor(BufferedReader inputReader, BufferedWriter outputReader) {
        ins = inputReader;
        outs = outputReader;
    }

    public void run() throws Exception {

        Runnable codeRunnable = new Runnable() {
            void resolveError(String traces) {
                isRunning = false;
                System.out.println(traces);
                try {
                    outs.write("=== Program crashed! ===\n");
                    outs.write(traces);
                    outs.write("=== See details at java console ===\n");
                    outs.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void run() {
                try {
                    System.out.println("Run start");
                    outs.write("=== Program start ===\n");
                    outs.flush();
                    isRunning = true;

                    startBlock.run();

                    isRunning = false;
                    System.out.println("Run end");
                    outs.write("=== Program end ===\n");
                    outs.flush();
                } catch (Exception e) {
                    // https://www.baeldung.com/java-stacktrace-to-string
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    resolveError(sw.toString());
                } catch (StackOverflowError e) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    resolveError(sw.toString());
                }
            }
        };

        process = new Thread(codeRunnable);
        process.start();
    }

    public void stop() {
        // https://ict-nroo.tistory.com/22
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
