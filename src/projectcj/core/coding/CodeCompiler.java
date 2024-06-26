package projectcj.core.coding;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.builtin.keyword.ElseBlock;
import projectcj.core.coding.block.builtin.keyword.IfBlock;
import projectcj.core.coding.block.scope.ScopableBlock;
import projectcj.core.coding.block.scope.function.FunctionBlock;
import projectcj.core.coding.block.scope.function.StartBlock;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.JNormalBlockBase;
import projectcj.swing.coding.block.JParameterBlockBase;
import projectcj.swing.coding.block.scope.JScopableBlock;
import projectcj.swing.coding.block.scope.function.JFunctionBlock;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.block.special.JParameter;

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
     * @param blocks
     *            List of swing blocks.
     * @return Executor object
     */
    public CodeExecutor compile(Component[] blocks) {
        CodeExecutor executor = new CodeExecutor(ins, outs);
        StartBlock startBlock = null;

        // Find only start block
        for (Component obj : blocks) {
            if (!(obj instanceof JBlockBase))
                continue;
            JBlockBase block = (JBlockBase) obj;

            if (block instanceof JStartBlock) {
                ScopableBlock scopeBlock = compileFunction(executor, (JStartBlock) block, null);

                // Set startBlock
                startBlock = (StartBlock) scopeBlock;
                executor.setStartBlock(startBlock);
            }
        }

        // For functions
        for (Component obj : blocks) {
            if (!(obj instanceof JBlockBase))
                continue;
            JBlockBase block = (JBlockBase) obj;

            // It's really bad way to implement this.
            // But I can't change all of weird code...
            if (block instanceof JFunctionBlock) {
                ScopableBlock scopeBlock = compileFunction(executor, (JFunctionBlock) block, startBlock);

                // Set functions
                executor.functions.put(((JFunctionBlock) block).blockName, (FunctionBlock) scopeBlock);
            }
        }
        return executor;
    }

    /**
     * Completely compile single normalBlock.
     * 
     * @param jNow
     * @param upperScopeBlock
     * @return
     */
    public NormalBlockBase compileBlock(JNormalBlockBase jNormalBlockBase,
            ScopableBlock upperScopeBlock) {
        NormalBlockBase normalBlockBase = jNormalBlockBase.getCoreClassObj(upperScopeBlock);

        // Compile inners
        if (normalBlockBase instanceof ScopableBlock) {
            compileInners((JScopableBlock) jNormalBlockBase, (ScopableBlock) normalBlockBase);
        }

        // Compile params
        if (normalBlockBase instanceof ParameterBlockBase) {
            compileParams((JParameterBlockBase) jNormalBlockBase,
                    (ParameterBlockBase) normalBlockBase);
        }

        // Compile lowers
        JNormalBlockBase jNow = jNormalBlockBase.lowerBlock;
        NormalBlockBase before = normalBlockBase;

        while (jNow != null) {
            NormalBlockBase now = compileBlock(jNow, upperScopeBlock);

            if (before != null) {
                before.lowerBlock = now;
                now.upperBlock = before;
            }

            before = now;
            jNow = jNow.lowerBlock;
        }

        return normalBlockBase;
    }

    /**
     * Compile parameters.
     * 
     * @param jParamBlockBase
     * @param paramBlockBase
     */
    public void compileParams(JParameterBlockBase jParamBlockBase,
            ParameterBlockBase paramBlockBase) {
        for (JParameter param : jParamBlockBase.parameters) {
            if (param.innerBlock == null) {
                paramBlockBase.parameters.add(null);
            } else {
                paramBlockBase.parameters.add(compileBlock((JNormalBlockBase) param.innerBlock,
                        paramBlockBase.upperScope));
            }
        }
    }

    /**
     * Compile only scopableBlock's inners.
     * 
     * @param jScopeableBlcok
     *            Original JBlock
     * @param scopableBlock
     *            New block
     */
    public void compileInners(JScopableBlock jScopeableBlcok, ScopableBlock scopableBlock) {
        // Compile inner blocks
        JNormalBlockBase jNow = jScopeableBlcok.getInnerBlock();
        NormalBlockBase before = null;

        while (jNow != null) {
            NormalBlockBase now = compileBlock(jNow, scopableBlock);

            if (before != null) {
                before.lowerBlock = now;
                now.upperBlock = before;
            } else {
                scopableBlock.setInnerBlock(now);
            }

            // Bind if-else
            if (now instanceof ElseBlock && before instanceof IfBlock) {
                ((ElseBlock) now).ifBlock = (IfBlock) before;
            }

            before = now;
            jNow = jNow.lowerBlock;
        }
    }

    /**
     * Compiles single function.
     * 
     * @param jScopeBlock
     *            Function swing object
     * @return Function core object
     */
    public ScopableBlock compileFunction(CodeExecutor executor, JScopableBlock jScopeBlock, ScopableBlock upperScope) {
        // Make function core object
        ScopableBlock scopeBlock = ((JBlockBase) jScopeBlock).getCoreClassObj(upperScope);
        scopeBlock.setGlobal(executor);

        // Compile inner blocks
        compileInners(jScopeBlock, scopeBlock);

        // Compile params
        if (jScopeBlock instanceof JParameterBlockBase) {
            compileParams((JParameterBlockBase) jScopeBlock, (ParameterBlockBase) scopeBlock);
        }

        return scopeBlock;
    }
}
