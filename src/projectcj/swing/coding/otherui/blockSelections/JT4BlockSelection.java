package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.Box;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.stllike.vector.*;
import projectcj.swing.coding.otherui.JBlockSelection;

public class JT4BlockSelection extends JSingleBlockSelection {

    public JT4BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Vector");
    }

    @Override
    public void init() {
        blks.add(new JVectorBlock(display));
        blks.add(new JVectorGetBlock(display));
        blks.add(new JVectorSetBlock(display));
        blks.add(new JVectorInsertBlock(display));
        blks.add(new JVectorEraseBlock(display));
        blks.add(new JVectorSizeBlock(display));
        blks.add(new JSplitBlock(display));

        for (JBlockBase block : blks) {
            blocksBoxPane.add(block);
            blocksBoxPane.add(Box.createVerticalStrut(10));
            blockSelection.blks.add(block);
        }

        super.init();
    }
}
