package projectcj.core.coding.block.builtin.io;

import java.io.IOException;
import projectcj.core.coding.block.ParameterBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class WriteBlock extends ParameterBlockBase {
    public WriteBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        try {
            // Print parameter's return value
            Object paramReturnValue = parameters.get(0).run();
            upperScope.getGlobal().outs.write(paramReturnValue.toString() + "\n");
            upperScope.getGlobal().outs.flush();
            // System.out.println(paramReturnValue.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
