package projectcj.core.coding.block.testblocks;

import projectcj.core.coding.block.BlockBase;

public class HelloWorldBlock extends BlockBase {
    public HelloWorldBlock() {
    }

    @Override
    public void run() {
        System.out.println("Hello world!");
    }
}
