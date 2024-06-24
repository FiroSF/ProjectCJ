package projectcj.swing.coding.otherui.blockSelections;

import javax.swing.*;
import projectcj.swing.coding.block.JBlockBase;
import projectcj.swing.coding.block.builtin.*;
import projectcj.swing.coding.block.builtin.calc.*;
import projectcj.swing.coding.block.builtin.cast.*;
import projectcj.swing.coding.block.builtin.io.*;
import projectcj.swing.coding.block.builtin.keyword.*;
import projectcj.swing.coding.block.builtin.variable.*;
import projectcj.swing.coding.block.scope.function.JStartBlock;
import projectcj.swing.coding.otherui.JBlockSelection;
import java.awt.event.*;

public class JT1BlockSelection extends JSingleBlockSelection {

    public JT1BlockSelection(JBlockSelection blockSelection) {
        super(blockSelection, "Basics");
    }

    @Override
    public void init() {
        blks.add(new JStartBlock(display));
        blks.add(new JIfBlock(display));
        blks.add(new JWhileBlock(display));
        blks.add(new JReadBlock(display));
        blks.add(new JWriteBlock(display));
        blks.add(new JToIntBlock(display));
        blks.add(new JAssignBlock(display));

        for (JBlockBase block : blks) {
            blocks.add(block);
        }

        JLiteralBlock literalBlock = new JLiteralBlock(display);
        JTextField blockNameTextField = new JTextField();
        blockNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                literalBlock.setInnerText("\"" + blockNameTextField.getText() + "\"");
            }
        });
        blocks.add(blockNameTextField);
        blocks.add(literalBlock);
        blks.add(literalBlock);

        JVariableDeclareBlock variableDeclareBlock = new JVariableDeclareBlock(display);
        blocks.add(variableDeclareBlock);
        blks.add(variableDeclareBlock);

        JVariableNameBlock variableNameBlock = new JVariableNameBlock(display);
        JTextField variableNamTextField = new JTextField();
        variableNamTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                variableNameBlock.setInnerText(variableNamTextField.getText());
            }
        });
        blocks.add(variableNamTextField);
        blocks.add(variableNameBlock);
        blks.add(variableNameBlock);


        for (JBlockBase blk : blks) {
            blockSelection.blks.add(blk);
        }
    }

}
