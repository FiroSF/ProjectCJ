package projectcj.core.coding.block.testblocks;

import java.io.IOException;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class HelloWorldBlock extends NormalBlockBase {
    public HelloWorldBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public void run() {
        System.out.println("Hello world!");
        try {
            upperScope.getGlobal().outs.write("Hello world!\n");
            upperScope.getGlobal().outs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
