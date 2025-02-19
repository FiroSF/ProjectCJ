package projectcj.core.coding.block.testblocks;

import java.io.IOException;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class HelloWorldBlock extends NormalBlockBase {
    String hw;

    public HelloWorldBlock(ScopableBlock scope, String hw) {
        this.upperScope = scope;
        this.hw = hw;
    }

    @Override
    public Object run() {
        System.out.println("Hello world!");
        try {
            upperScope.getGlobal().outs.write(hw + "\n");
            upperScope.getGlobal().outs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
