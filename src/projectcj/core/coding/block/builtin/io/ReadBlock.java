package projectcj.core.coding.block.builtin.io;

import java.io.IOException;

import projectcj.core.coding.block.NormalBlockBase;
import projectcj.core.coding.block.scope.ScopableBlock;

public class ReadBlock extends NormalBlockBase {
    public ReadBlock(ScopableBlock scope) {
        this.upperScope = scope;
    }

    @Override
    public Object run() {
        try {
            return upperScope.getGlobal().ins.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Read failed...";
        }
    }
}
