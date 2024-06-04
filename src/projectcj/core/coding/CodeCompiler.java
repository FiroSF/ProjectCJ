package projectcj.core.coding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.function.ScopeBlock;
import projectcj.core.coding.block.scope.function.StartBlock;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.scope.function.JScopeBlock;

/**
 * Singleton compiler class.
 */
public class CodeCompiler {
    // Console input outputs
    public BufferedReader ins;
    public BufferedWriter outs;

    public CodeCompiler(BufferedReader inputStream, BufferedWriter outputStream) {
        ins = inputStream;
        outs = outputStream;
    }

    /**
     * Compile all code.
     * 
     * @param blocks List of swing blocks.
     * @return Executor object
     */
    public CodeExecutor compile(ArrayList<JBlockBase> blocks) {
        CodeExecutor executor = new CodeExecutor(ins, outs);
        StartBlock startBlock;

        // Find only functions
        for (JBlockBase block : blocks) {
            if (block instanceof JScopeBlock) {
                ScopeBlock scopeBlock = compileFunction(executor, (JScopeBlock) block);

                // Set startBlock
                if (scopeBlock instanceof StartBlock) {
                    startBlock = (StartBlock) scopeBlock;
                    executor.setStartBlock(startBlock);
                }
            }
        }

        return executor;
    }

    /**
     * Compiles single function.
     * 
     * @param jScopeBlock Function swing object
     * @return Function core object
     */
    public ScopeBlock compileFunction(CodeExecutor executor, JScopeBlock jScopeBlock) {
        // Make function core object
        ScopeBlock scopeBlock = jScopeBlock.getCoreClassObj(null);
        scopeBlock.setGlobal(executor);

        // Compile inner blocks
        JNormalBlockBase jNow = ((JScopeBlock) jScopeBlock).getInnerBlock();
        NormalBlockBase before = null;

        while (jNow != null) {
            NormalBlockBase now = jNow.getCoreClassObj(scopeBlock);

            if (before != null) {
                before.lowerBlock = now;
                now.upperBlock = before;
            } else {
                scopeBlock.innerBlock = now;
            }

            before = now;
            jNow = jNow.lowerBlock;
        }

        return scopeBlock;
    }
}
