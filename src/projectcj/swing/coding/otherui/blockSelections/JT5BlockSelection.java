package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.Box;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.cast.JToIntBlock;
import projectcj.swing.coding.block.builtin.io.JReadBlock;
import projectcj.swing.coding.block.builtin.io.JWriteBlock;
import projectcj.swing.coding.block.builtin.string.JToStringBlock;
import projectcj.swing.coding.otherui.JBlockSelection;

public class JT5BlockSelection extends JSingleBlockSelection {

    public JT5BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Utils");
    }

    @Override
    public void init() {
        blks.add(new JToIntBlock(display));
        blks.add(new JToStringBlock(display));
        blks.add(new JReadBlock(display));
        blks.add(new JWriteBlock(display));

        for (JBlockBase block : blks) {
            blocksBoxPane.add(block);
            blocksBoxPane.add(Box.createVerticalStrut(10));
            blockSelection.blks.add(block);
        }

        super.init();
    }
}
