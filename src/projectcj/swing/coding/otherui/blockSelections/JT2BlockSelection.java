package projectcj.swing.coding.otherui.blockSelections;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.calc.*;
import projectcj.swing.coding.otherui.JBlockSelection;

public class JT2BlockSelection extends JSingleBlockSelection {

    public JT2BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Calcs");
    }

    @Override
    public void init() {
        blks.add(new JAddBlock(display));
        blks.add(new JLtBlock(display));
        blks.add(new JGtBlock(display));
        blks.add(new JLteBlock(display));
        blks.add(new JGteBlock(display));

        for (JBlockBase block : blks) {
            blocks.add(block);
        }

        for (JBlockBase blk : blks) {
            blockSelection.blks.add(blk);
        }
    }

}
