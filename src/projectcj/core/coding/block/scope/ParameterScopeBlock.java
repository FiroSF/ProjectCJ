package projectcj.core.coding.block.scope;

import java.util.Stack;

import projectcj.core.coding.CodeExecutor;
import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.builtin.keyword.BreakSignal;
import projectcj.core.coding.block.scope.function.FunctionStackObj;

public abstract class ParameterScopeBlock extends ParameterBlockBase implements ScopableBlock {
    // Global scope
    public CodeExecutor global;
    NormalBlockBase innerBlock = null;

    public ParameterScopeBlock(ScopableBlock scope) {
        upperScope = scope;
        global = scope.getGlobal();
    }

    @Override
    public Object runInnerBlock() {
        // Run innerBlocks
        NormalBlockBase now = innerBlock;
        while (now != null) {
            Object res = now.run();

            // Function return
            Stack<FunctionStackObj> stk = getGlobal().functionCallStack;
            if (!stk.empty() && stk.peek().returnValue != null) {
                return new BreakSignal();
            }

            // Break
            if (res instanceof BreakSignal) {
                return res;
            }

            // Interrupted
            // https://ict-nroo.tistory.com/22
            if (Thread.interrupted()) {
                global.isInterrupted = true;
                return new BreakSignal();
            }

            if (global.isInterrupted) {
                return new BreakSignal();
            }

            now = now.lowerBlock;
        }

        return null;
    }

    @Override
    public CodeExecutor getGlobal() {
        return global;
    }

    @Override
    public void setGlobal(CodeExecutor global) {
        this.global = global;
    }

    @Override
    public NormalBlockBase getInnerBlock() {
        return innerBlock;
    }

    @Override
    public void setInnerBlock(NormalBlockBase innerBlock) {
        this.innerBlock = innerBlock;
    }

}
