package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.Box;

import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.calc.*;
import projectcj.swing.coding.block.builtin.calc.arithmetic.*;
import projectcj.swing.coding.block.builtin.calc.assign.*;
import projectcj.swing.coding.otherui.JBlockSelection;

public class JT2BlockSelection extends JSingleBlockSelection {

    public JT2BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Calcs");
    }

    @Override
    public void init() {
        blks.add(new JAddBlock(display));
        blks.add(new JSubBlock(display));
        blks.add(new JMulBlock(display));
        blks.add(new JDivBlock(display));
        blks.add(new JAddAssignBlock(display));
        blks.add(new JSubAssignBlock(display));
        blks.add(new JMulAssignBlock(display));
        blks.add(new JDivAssignBlock(display));
        blks.add(new JLtBlock(display));
        blks.add(new JGtBlock(display));
        blks.add(new JLteBlock(display));
        blks.add(new JGteBlock(display));
        blks.add(new JEqBlock(display));
        blks.add(new JAndBlock(display));
        blks.add(new JOrBlock(display));
        blks.add(new JNotBlock(display));

        for (JBlockBase block : blks) {
            blocksBoxPane.add(block);
            // https://stackoverflow.com/questions/10744809/boxlayout-misunderstanding-strut
            blocksBoxPane.add(Box.createVerticalStrut(10));
            blockSelection.blks.add(block);
        }

        super.init();
    }

}
